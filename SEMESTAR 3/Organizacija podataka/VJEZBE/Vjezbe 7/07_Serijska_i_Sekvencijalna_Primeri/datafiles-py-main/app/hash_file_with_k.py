import os
import math
from binary_file import BinaryFile


class HashFileLinearFix(BinaryFile):
    def __init__(self, filename, record, blocking_factor, b, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.b = b

    def init_file(self):
        with open(self.filename, 'wb') as f:
            for i in range(self.b):
                block = self.blocking_factor * [self.get_empty_rec()]
                self.write_block(f, block)

    def print_file(self):
        with open(self.filename, "rb") as f:
            for i in range(self.b):
                block = self.read_block(f)
                print("Bucket {}".format(i+1))
                self.print_block(block)

    def hash(self, id):
        return id % self.b

    def kvadrat_centralnih_cifara(self, id):
        n = 2  # broj cifara adrese
        v = 10

        ids = str(id)
        p = len(ids)  # broj cifara kljuca
        ids = str(id ** 2)

        if len(ids) % 2 != 0:
            ids = "0" + ids

        sInd = (len(ids) - 2) // 2
        c = int(ids[sInd: sInd + n])

        return math.floor((20 / v ** n) * c)

    def __metodPreklapanja(self, id):
        n = 2  # broj cifara adrese
        v = 10

        ids = str(id)
        p = len(ids)  # broj cifara kljuca

        brojSegmenata = math.ceil(p / n)

        while p % brojSegmenata:
            ids = "0" + ids

        t = 0

        for i in range(brojSegmenata):
            if (i + 1) % 2 != 0:
                t += int(ids[p - 2 * (i + 1):p - 2 * i])
            else:
                t += int(ids[p - 2 * (i + 1):p - 2 * i][::-1])

        t = t % v ** n

        return math.floor((20 / v ** n) * t)

    def find_new_block_id(self, mother_block_id, i):
        new_id = mother_block_id + i
        if new_id >= self.b:
            new_id = new_id % self.b
        return new_id

    def is_inbetween(self, old_block_id, new_block_id, id):
        mother_block_id = self.hash(id)
        while True:
            old_block_id = (old_block_id + 1) % self.b
            if old_block_id == mother_block_id:
                return True
            if old_block_id == new_block_id:
                return False

    def find_in_overflow(self, f, id, mother_block_id):

        for i in range(1, self.b):
            new_block_id = self.find_new_block_id(mother_block_id, i)

            f.seek(new_block_id * self.block_size)
            block = self.read_block(f)

            for j in range(self.blocking_factor):
                if block[j]['id'] == id:
                    return new_block_id, j
                if block[j]['id'] == self.get_empty_rec():
                    return None

    def find_by_id(self, id):

        block_id = self.hash(id)
        with open(self.filename, "rb") as f:
            f.seek(block_id * self.block_size)
            block = self.read_block(f)

            for i in range(self.blocking_factor):
                if block[i].get("id") == id:
                    return block_id, i

            return self.find_in_overflow(f, id, block_id)

    def insert_record(self, record):

        found = self.find_by_id(record['id'])

        if found:
            return

        mother_block_id = self.hash(record['id'])

        with open(self.filename, 'rb+') as f:

            f.seek(mother_block_id * self.block_size)
            block = self.read_block(f)

            for i in range(self.blocking_factor):
                if block[i] == self.get_empty_rec():
                    block[i] = record

                    f.seek(-self.block_size, 1)
                    self.write_block(f, record)
                    return

            for j in range(1, self.b):
                new_block_id = self.find_new_block_id(mother_block_id, j)

                f.seek(new_block_id * self.block_size)
                block = self.read_block(f)

                for k in range(self.blocking_factor):
                    if block[k] == self.get_empty_rec():
                        block[k] = record

                        f.seek(-self.block_size, 1)
                        self.write_block(f, record)
                        return

            print("No place for this record")
            return

    def delete_by_id(self, id):

        found = self.find_by_id(id)

        if not found:
            return

        block_id, record_id = found

        with open(self.filename, 'rb+') as f:

            f.seek(block_id * self.block_size)
            block = self.read_block(f)

            while record_id < self.blocking_factor - 1:
                block[record_id] = block[record_id + 1]
                record_id += 1

            mother_block_id = block_id
            block_to_fill = block
            step = 1
            iter = 0

            while True:

                new_block_id = self.find_new_block_id(mother_block_id, step)

                f.seek(new_block_id * self.block_size)
                new_block = self.read_block(f)
                switch = False

                for j in range(self.blocking_factor):

                    if self.hash(new_block[j]['id']) != new_block_id:
                        if self.is_inbetween(mother_block_id, new_block_id, new_block[j]['id']):
                            continue

                        switch = True
                        block_to_fill[self.blocking_factor - 1] = new_block[j]

                        f.seek(mother_block_id * self.block_size)
                        self.write_block(f, block_to_fill)
                        index = j

                        while index < self.blocking_factor - 1:
                            new_block[index] = new_block[index + 1]
                            index += 1
                        break

                if switch:
                    mother_block_id = new_block_id
                    block_to_fill = new_block
                    step = 1
                else:
                    step += 1

                iter += 1

                if iter >= self.b - 1:
                    break

            f.seek(mother_block_id * self.block_size)
            block_to_fill[self.blocking_factor - 1] = self.get_empty_rec()
            self.write_block(f, block_to_fill)
