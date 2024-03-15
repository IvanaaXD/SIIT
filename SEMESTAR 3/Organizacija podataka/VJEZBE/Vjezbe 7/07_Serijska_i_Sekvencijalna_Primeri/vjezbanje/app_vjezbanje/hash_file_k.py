from binary_file import BinaryFile


class HashFileK(BinaryFile):

    def __init__(self, k, b, filename, record, blocking_factor, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.b = b
        self.k = k

    def init_file(self):
        with open(self.filename, 'wb') as f:
            for _ in range(self.b):
                block = self.blocking_factor * self.get_empty_rec()
                self.write_block(f, block)

    def print_file(self, b):
        with open(self.filename, 'rb') as f:
            for i in range(self.b):
                block = self.read_block(f)
                print("Block ", i+1)
                self.print_block(block)

    def hash(self, id):
        return id % self.b

    def find_by_id(self, id):

        block_id = self.hash(id)

        with open(self.filename, 'rb') as f:

            f.seek(block_id * self.block_size)
            block = self.read_block(self.block_size)

            for i in range(self.blocking_factor):
                if block[i]['status'] == 0:
                    return None
                if block[i]['status'] == 1 and block[i]['id'] == id:
                    return block_id, i

        self.find_in_overflow(id)

    def find_in_overflow(self, id):

        block_id = self.hash(id)
        current_block_id = (block_id + self.k) % self.b

        with open(self.filename, 'rb') as f:

            while current_block_id != block_id:

                f.seek(current_block_id * self.block_size)
                current_block = self.read_block(self.block_size)

                for i in range(self.blocking_factor):
                    if current_block[i]['status'] == 0:
                        return None
                    if current_block[i]['status'] == 1 and current_block[i]['id'] == id:
                        return block_id, i

                current_block_id = (current_block_id + self.k) % self.b

            return None

    def insert_record(self, record):

        id = record['id']
        block_id = self.hash(id)

        with open(self.filename, 'rb+') as f:

            f.seek(block_id * self.block_size)
            block = self.read_block(f)

            i = 0
            while i < self.blocking_factor:

                if block[i]['status'] == 0:
                    block[i] = record
                    f.seek(block_id * self.block_size)
                    self.write_block(f, block)
                    return

                if block[i]['id'] == id:
                    if block['status'] == 1:
                        print("Record already exists")
                    else:
                        block[i] = record
                        f.seek(block_id * self.block_size)
                        self.write_block(f, block)
                    return

                i += 1

        self.insert_in_overflow(record)

    def insert_in_overflow(self, record):

        id = record['id']
        block_id = self.hash(id)
        current_block_id = (block_id + self.k) % self.b

        with open(self.filename, 'rb+') as f:

            while current_block_id != block_id:

                f.seek(current_block_id * self.block_size)
                block = self.read_block(f)

                i = 0
                while i < self.blocking_factor:

                    if block[i]['status'] == 0:
                        block[i] = record
                        f.seek(current_block_id * self.block_size)
                        self.write_block(f, block)
                        return

                    if block[i]['id'] == id:
                        if block['status'] == 1:
                            print("Record already exists")
                        else:
                            block[i] = record
                            f.seek(current_block_id * self.block_size)
                            self.write_block(f, block)
                        return

                    i += 1

                current_block_id = (current_block_id + self.k) % self.b

            print("Record cannot be inserted")

    def delete_by_id(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id, record_id = found

        with open(self.filename, 'rb+') as f:

            f.seek(block_id * self.block_size)
            block = self.read_block(f)
            block[record_id]['status'] = 2
            f.seek(block_id * self.block_size)
            self.write_block(f, block)

    def delete_record(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id, record_id = found

        with open(self.filename, 'rb+') as f:

            f.seek(block_id * self.block_size)
            block = self.read_block(f)

            return_record = block[record_id]

            while record_id < self.blocking_factor - 1:
                block[record_id] = block[record_id + 1]
                record_id += 1

            next_insertion_record = self.delete_in_overflow(f, block_id, id)

            block[self.blocking_factor - 1] = next_insertion_record
            f.seek(block_id * self.block_size)
            self.write_block(f, block)

            return return_record

    def delete_in_overflow(self, f, block_id, id):

        current_block_id = (block_id + self.k) % self.b

        if current_block_id == block_id:
            return self.get_empty_rec()

        f.seek(current_block_id * self.block_size)
        block = self.read_block(f)

        next_removed_record = None
        i = 0
        for i in range(self.blocking_factor):
            if self.hash(block[i]['id']) == block_id:
                next_removed_record = block[i]
                break

        if not next_removed_record:
            return self.delete_in_overflow(f, current_block_id, id)

        for j in range(i, self.blocking_factor - 1):
            block[j] = block[j + 1]

        next_insertion_record = self.delete_in_overflow(f, current_block_id, id)

        block[self.blocking_factor - 1] = next_insertion_record
        f.seek(current_block_id * self.block_size)
        self.write_block(f, block)

        return next_removed_record
