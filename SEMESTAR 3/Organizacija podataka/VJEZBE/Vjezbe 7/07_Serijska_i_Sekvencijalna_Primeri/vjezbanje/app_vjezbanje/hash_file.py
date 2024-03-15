from binary_file import BinaryFile
import os


class HashFile(BinaryFile):

    def __init__(self, filename, b, record, blocking_factor, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.b = b

    def init_file(self):
        with open(self.filename, 'wb') as f:
            for _ in range(self.b):
                block = self.blocking_factor * [self.get_empty_rec()]
                self.write_block(f, block)

    def print_file(self):
        with open(self.filename, 'rb') as f:
            for i in range(self.b):
                block = self.read_block(f)
                print("Block", i+1)
                self.print_block(block)

            print("Overflow zone")
            while True:
                rec = self.read_record(f)
                if not rec:
                    break
                print(rec)

    def hash(self, id):
        return id % self.b

    def find_by_id(self, id):

        block_id = self.hash(id)

        with open(self.filename, 'rb') as f:

            f.seek(self.block_size * block_id)
            block = self.read_block(f)

            for i in range(self.blocking_factor):
                if block[i]['status'] == 0:
                    return None
                if block[i]['id'] == id and block[i]['status'] == 1:
                    return block_id, i

            return self.find_in_overflow(f, id)

    def find_in_overflow(self, f, id):

        f.seek(self.b * self.block_size)

        i = 0
        while True:

            rec = self.read_record(f)

            if not rec:
                return None

            if rec['id'] == id:
                return self.b, i

            i += 1

    def insert(self, record):

        id = record['id']
        block_id = self.hash(id)

        with open(self.filename, 'rb+') as f:

            f.seek(self.block_size * block_id)
            block = self.read_block(f)

            i = 0
            while i < self.blocking_factor and block[i]['status']:
                if block[i]['id'] == id:
                    if block[i]['status'] == 1:
                        print("Record already inserted")
                    else:
                        block[i] = record
                        f.seek(self.block_size * block_id)
                        self.write_block(f, block)
                    return
                i += 1

            if i == self.blocking_factor:
                self.insert_in_overflow(f, record)
                return

            block[i] = record
            f.seek(self.block_size * block_id)
            self.write_block(f, block)

    def insert_in_overflow(self, f, record):

        f.seek(self.b * self.block_size)

        while True:

            rec = self.read_record(f)

            if not rec:
                break

            if rec['id'] == record['id']:
                if rec['status'] == 1:
                    print("Record already inserted")
                else:
                    f.seek(-self.record_size, 1)
                    self.write_record(f, record)
                return

        self.write_record(f, record)

    def delete(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id, record_id = found[0], found[1]

        with open(self.filename, 'rb+') as f:

            if block_id < self.b:

                f.seek(self.block_size * block_id)
                block = self.read_record(f)

                while record_id < self.blocking_factor - 1:
                    block[record_id] = block[record_id + 1]
                    record_id += 1

                block[self.blocking_factor - 1] = self.get_empty_rec()

                f.seek(self.b * self.block_size)

                i = 0
                while True:

                    overflow_rec = self.read_record(f)

                    if self.hash(overflow_rec['id']) == block_id:
                        block[self.blocking_factor - 1] = overflow_rec
                        break
                    i += 1

                shift =[]

                while True:

                    next_rec = self.read_record(f)

                    if not next_rec:
                        f.seek(-self.record_size, 2)
                        f.truncate()
                        break

                    shift.append(next_rec)

                f.seek(self.b * self.block_size + i * self.record_size)

                for rec in shift:
                    self.write_record(f, rec)

                f.seek(self.block_size * block_id)
                self.write_block(f, block)

            else:

                f.seek(self.b * self.block_size + record_id * self.record_size)

                shift = []

                while True:

                    next_rec = self.read_record(f)

                    if not next_rec:
                        f.seek(-self.record_size, 2)
                        f.truncate()
                        break

                    shift.append(next_rec)

                f.seek(self.b * self.block_size + record_id * self.record_size)

                for rec in shift:
                    self.write_record(f, rec)
