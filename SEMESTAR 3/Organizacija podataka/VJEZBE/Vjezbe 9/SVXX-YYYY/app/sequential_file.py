from binary_file import BinaryFile
import os


class SequentialFile(BinaryFile):

    def __init__(self, filename, record, blocking_factor, empty_key=-1):
        empty_record = {"id": empty_key, "name": "", "q": 0.0, "status": 0}
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_record, empty_key)

    def init_file(self):

        with open(self.filename, 'wb') as f:
            block = self.blocking_factor * [self.get_empty_rec()]
            self.write_block(f, block)

    def is_last(self, block):

        for i in range(self.blocking_factor):
            if block[i].get("id") == self.empty_key:
                return True

        return False

    def find_by_id(self, id):
        i = 0
        with open(self.filename, "rb") as f:
            while True:
                block = self.read_block(f)

                if not block:
                    return None

                for j in range(self.blocking_factor):
                    if block[j].get("id") == id:
                        return (i, j)
                    if block[j].get("id") > id:
                        return None

                i += 1

    def find_in_block(self, block, record):

        for j in range(self.blocking_factor):
            if block[j].get("id") == self.empty_key or block[j].get("id") > record.get("id"):
                return True, j

        return False, None

    def insert_record(self, record):

        if self.find_by_id(id):
            print("Already exists")
            return

        with open(self.filename, 'rb+') as f:

            while True:

                block = self.read_block(f)

                if not block:
                    return

                last = self.is_last(block)
                here, j = self.find_in_block(block, record)

                if not here:
                    continue

                temp_record = block[self.blocking_factor - 1]
                for i in range(self.blocking_factor - 1, j, -1):
                    block[i] = block[i - 1]

                block[j] = record
                record = temp_record

                f.seek(-self.block_size, 1)
                self.write_block(f, block)

                if last and block[self.blocking_factor - 1].get("id") != self.empty_key:
                    block = self.blocking_factor * [self.get_empty_rec()]
                    self.write_block(f, block)

    def logical_delete(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id = found[0]
        record_id = found[1]

        with open(self.filename, 'rb+') as f:

            f.seek(self.block_size * block_id)
            block = self.read_block(f)

            record = block[record_id]
            record['status'] = 2
            block[record_id] = record

            self.write_block(f, block)

    def check_num_of_logically_deleted(self, max_deleted):

        records_to_delete = []

        with open(self.filename, 'rb+') as f:

            while True:
                
                block = self.read_block(f)

                if not block:
                    break

                for i in range(self.blocking_factor):
                    if block[i]['status'] == 2:
                        records_to_delete.append(block[i].get("id"))

        if len(records_to_delete) >= max_deleted:
            for id in records_to_delete:
                self.delete_record(id)

    def delete_record(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id = found[0]
        record_id = found[1]
        next_block = None

        with open(self.filename, 'rb+') as f:

            while True:

                f.seek(block_id * self.block_size)
                block = self.read_block(f)

                if not block:
                    break

                for i in range(record_id, self.blocking_factor-1):
                    block[i] = block[i + 1]

                if self.is_last(block):
                    f.seek(-self.block_size, 1)
                    self.write_block(f, block)
                    break

                next_block = self.read_block(f)
                block[self.blocking_factor-1] = next_block[0]
                f.seek(-2*self.block_size, 1)
                self.write_block(f, block)

                block_id += 1
                record_id = 0

        if next_block and next_block[0].get("id") == self.empty_key:
            os.ftruncate(os.open(self.filename, os.O_RDWR), block_id * self.block_size)

    def print_file(self):
        i = 0
        with open(self.filename, "rb") as f:
            while True:
                block = self.read_block(f)

                if not block:
                    break

                i += 1
                print("Block {}".format(i))
                self.print_block(block)
