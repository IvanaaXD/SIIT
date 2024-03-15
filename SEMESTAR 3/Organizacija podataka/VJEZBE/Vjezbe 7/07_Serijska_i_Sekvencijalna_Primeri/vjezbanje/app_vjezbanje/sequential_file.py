import os

from binary_file import *
import struct


class SequentialFile(BinaryFile):

    def __init__(self, filename, record, blocking_factor, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)

    def init_file(self):

        with open(self.filename, 'wb') as f:
            block = self.blocking_factor * [self.get_empty_rec()]
            self.write_block(f, block)

    def print_file(self):

        i = 0

        with open(self.filename, 'rb') as f:

            while True:
                block = self.read_block(f)

                if not block:
                    break

                print("Blok: ", format(i))
                self.print_block(block)

                i += 1

    def find_by_id(self, id):

        i = 0

        with open(self.filename, 'rb') as f:

            while True:
                block = self.read_block(f)

                if not block:
                    return None

                for j in range(self.blocking_factor):

                    if block[j].get("id") == id:
                        return i, j

                    if block[j].get("id") > id:
                        return None

                i += 1

    def find_in_block(self, block, record):

        for i in range(self.blocking_factor):

            if block[i].get("id") == self.empty_key or block[i].get("id") > record.get("id"):
                return True, i

        return False, None

    def is_last(self, block):

        for i in range(self.blocking_factor):
            if block[i].get("id") == self.empty_key:
                return True
        return False

    def insert_record(self, record):

        if self.find_by_id(record.get("id")):
            print("Already inserted")
            return

        with open(self.filename, 'rb+') as f:

            while True:

                block = self.read_block(f)

                if not block:
                    break

                last = self.is_last(block)
                here, j = self.find_in_block(block, record)

                if not here:
                    continue

                temp_record = block[self.blocking_factor - 1]

                for k in range(self.blocking_factor-1, j, -1):
                    block[k] = block[k-1]

                block[j] = record
                record = temp_record

                f.seek(-self.block_size, 1)
                self.write_block(f, block)

                if last and block[self.blocking_factor-1].get("id") != self.empty_key:
                    block = self.blocking_factor * [self.get_empty_rec()]
                    self.write_block(f, block)

    # physical delete
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

                while record_id < self.blocking_factor - 1:
                    block[record_id] = block[record_id + 1]
                    record_id += 1

                if self.is_last(block):
                    f.seek(-self.block_size, 1)
                    self.write_block(f, block)
                    break

                next_block = self.read_block(f)
                block[self.blocking_factor - 1] = next_block[0]
                f.seek(-2*self.block_size, 1)
                self.write_block(f, block)

                block_id += 1
                record_id = 0

        if next_block and next_block[0].get("id") == self.get_empty_rec():
            os.ftruncate(os.open(self.filename, os.O_RDWR), self.block_size * block_id)
