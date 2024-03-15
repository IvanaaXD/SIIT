#!/usr/bin/python

import os

from binary_file import BinaryFile


class HashFile(BinaryFile):
    def __init__(self, filename, record, blocking_factor, b, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.b = b

    def hash(self, id):
        return id % self.b

    def init_file(self):
        with open(self.filename, "wb") as f:
            for _ in range(self.b):
                block = self.blocking_factor*[self.get_empty_rec()]
                self.write_block(f, block)

    def __insert_overflow(self, f, rec):
        f.seek(self.b * self.block_size)

        while True:
            record = self.read_record(f)
            if not record:
                break
            if record.get("id") == rec.get("id"):
                if record.get("status") == 1:
                    print("Already exists with ID {}".format(rec.get("id")))
                else:
                    f.seek(-self.record_size, 1)
                    self.write_record(f, rec)
                return

        self.write_record(f, rec)

    def insert_record(self, rec):
        id = rec.get("id")
        block_idx = self.hash(id)

        with open(self.filename, "rb+") as f:
            f.seek(block_idx * self.block_size)
            block = self.read_block(f)

            i = 0
            while i < self.blocking_factor and block[i].get("status"):
                if block[i].get("id") == id:
                    if block[i].get("status") == 1:
                        print("Already exists with ID {}".format(id))
                    else:
                        block[i] = rec
                        f.seek(block_idx * self.block_size)
                        self.write_block(f, block)
                    return
                i += 1

            if i == self.blocking_factor:
                self.__insert_overflow(f, rec)
                return

            block[i] = rec
            f.seek(block_idx * self.block_size)
            self.write_block(f, block)

    def print_file(self):
        with open(self.filename, "rb") as f:
            for i in range(self.b):
                block = self.read_block(f)
                print("Bucket {}".format(i+1))
                self.print_block(block)

            print("Overflow zone:")
            while True:
                rec = self.read_record(f)
                if not rec:
                    break
                print(rec)

    def __find_in_overflow(self, f, id):
        f.seek(self.b * self.block_size)

        i = 0
        while True:
            rec = self.read_record(f)
            if not rec:
                return None
            if rec.get("id") == id:
                return (self.b, i)
            i += 1

    def find_by_id(self, id):
        block_idx = self.hash(id)

        with open(self.filename, "rb+") as f:
            f.seek(block_idx * self.block_size)
            block = self.read_block(f)

            for i in range(self.blocking_factor):
                if block[i].get("status") == 0:
                    return None
                if block[i].get("status") == 1 and block[i].get("id") == id:
                    return (block_idx, i)

            return self.__find_in_overflow(f, id)

    def delete_by_id(self, id):
        found = self.find_by_id(id)

        if not found:
            return None

        block_idx = found[0]
        rec_idx = found[1]

        with open(self.filename, "rb+") as f:
            if block_idx < self.b:
                f.seek(block_idx * self.block_size)
                block = self.read_block(f)
                block[rec_idx]["status"] = 2
                f.seek(block_idx * self.block_size)
                self.write_block(f, block)
            else:
                f.seek(self.b * self.block_size + rec_idx * self.record_size)
                rec = self.read_record(f)
                rec["status"] = 2
                f.seek(-self.record_size, 1)
                self.write_record(f, rec)
            return found
    
    def edit_by_id(self, record):
        found = self.find_by_id(record['id'])

        if not found:
            return None
        
        block_idx, rec_idx = found

        with open(self.filename, "rb+") as f:
            if block_idx < self.b:
                f.seek(self.block_size*block_idx)
                block = self.read_block(f)
                block[rec_idx] = record
                f.seek(self.block_size, -1)
                self.write_block(f, block)
            else:
                f.seek(self.b * self.block_size + rec_idx * self.record_size)
                self.write_record(f, record)
            return found
    
    def real_delete(self, id):
        found = self.find_by_id(id)

        if not found:
            return

        block_idx, rec_idx = found

        with open(self.filename, 'rb+') as f:

            if block_idx < self.b:

                f.seek(block_idx * self.block_size)
                block = self.read_block(f)

                while rec_idx < self.blocking_factor - 1:
                    block[rec_idx] = block[rec_idx + 1]
                    rec_idx += 1

                f.seek(self.block_size * self.b)
                overflow_counter = 0

                exist = False

                while True:
                    record = self.read_record(f)
                    if record is None:
                        block[rec_idx] = self.get_empty_rec()
                        f.seek(self.block_size*block_idx)
                        self.write_block(f, block)
                        return
                    if self.hash(record['id']) == block_idx:
                        block[self.blocking_factor-1] = record
                        exist = True
                        break
                    overflow_counter += 1
                
                f.seek(self.block_size*block_idx)
                self.write_block(f, block)
                if exist:
                    f.seek(self.b * self.block_size + self.record_size*(overflow_counter+1))
                    while True:
                        record = self.read_record(f)
                        if record is None:
                            break
                        f.seek(-2*self.record_size, 1)
                        self.write_record(f, record)
                        f.seek(self.record_size, 1)
            else:
                f.seek(self.b * self.block_size + self.record_size * (rec_idx+1))
                while True:
                    record = self.read_record(f)
                    if record is None:
                        break
                    f.seek(-2*self.record_size, 1)
                    self.write_record(f, record)
                    f.seek(self.record_size, 1)
        
        num_in_overflow = 0
        with open(self.filename, 'rb+') as f:
            f.seek(self.b*self.block_size)
            while True:
                if self.read_record(f) is None:
                    break
                num_in_overflow += 1
        if num_in_overflow > 0:
            os.ftruncate(os.open(self.filename, os.O_RDWR),
                        self.b*self.block_size + self.record_size*(num_in_overflow-1))