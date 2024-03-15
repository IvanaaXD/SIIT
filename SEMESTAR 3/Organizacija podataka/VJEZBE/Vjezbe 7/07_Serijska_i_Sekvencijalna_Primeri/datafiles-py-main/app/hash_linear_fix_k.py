import os
import math
from binary_file import BinaryFile


class HashFileLinearFix(BinaryFile):
    def __init__(self, filename, record, blocking_factor, b, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.b = b

    def hash(self, id):
        return id % self.b
    
    def centralne_cifre_kvadrata(self, id):
        id_squared = id*id
        p = 2
        n = math.ceil(math.log10(self.b))
        t = math.floor(p - n/2)
        cifre = []
        for i in range(n):
            copy = id_squared
            cifre.append(copy//(10**(t+i))%10)
        cifre.reverse()
        T = int(''.join(map(str, cifre)))
        return 1 + math.floor(self.b/10**n * T)
        

    def preklapanje(self, id):
        n = math.ceil(math.log10(self.b))
        p = 7
        k = self.b / 10**n
        segment_num = math.ceil(p/n)
        str_k = str(id)
        key_len = len(str_k)
        segment_len = math.ceil(key_len/segment_num)
        T = 0
        for i in range(segment_num-1, -1, -1):
            lower = i*segment_len
            upper = segment_len + i*segment_len
            if upper >= key_len:
                upper = key_len
            if i % 2 == 0:
                T += int(str_k[lower:upper])
            else:
                addition = str_k[lower:upper]
                T += int(addition[::-1])
        T = T % 10**n
        return 1 + math.floor(self.b/10**n * T)

    def init_file(self):
        with open(self.filename, 'wb') as f:
            for i in range(self.b):
                bucket = self.blocking_factor * [self.get_empty_rec()]
                self.write_block(f, bucket)

    def insert_record(self, record):
        found = self.find_by_id(record['id'])
        if found:
            print("Slog sa datim id vec postoji")
            return
        mother_bucket_idx = self.hash(record['id'])
        with open(self.filename, "rb+") as f:
            f.seek(mother_bucket_idx*self.block_size)
            bucket = self.read_block(f)
            for i in range(self.blocking_factor):
                if bucket[i]['id'] == self.empty_key:
                    bucket[i] = record
                    f.seek(-self.block_size, 1)
                    self.write_block(f, bucket)
                    return
            for i in range(1, self.b):
                new_idx = self.find_new_bucket_idx(mother_bucket_idx, i)
                f.seek(self.block_size*new_idx)
                bucket = self.read_block(f)
                for i in range(self.blocking_factor):
                    if bucket[i]["id"] == self.empty_key:
                        bucket[i] = record
                        f.seek(-self.block_size, 1)
                        self.write_block(f, bucket)
                        return

    def print_file(self):
        with open(self.filename, "rb") as f:
            for i in range(self.b):
                block = self.read_block(f)
                print("Bucket {}".format(i+1))
                self.print_block(block)

    def find_overflow(self, mother_bucket_idx, id, f):
        for i in range(1, self.b):
            new_idx = self.find_new_bucket_idx(mother_bucket_idx, i)
            f.seek(self.block_size*new_idx)
            bucket = self.read_block(f)
            for i in range(self.blocking_factor):
                if bucket[i]['id'] == id:
                    return (new_idx, i)
                if bucket[i]['id'] == self.empty_key:
                    return None
        

    def find_by_id(self, id):
        bucket_idx = self.hash(id)
        with open(self.filename, "rb") as f:
            f.seek(bucket_idx*self.block_size)
            bucket = self.read_block(f)
            i = 0
            for i in range(self.blocking_factor):
                if bucket[i]['id'] == id:
                    return bucket_idx, i
            return self.find_overflow(bucket_idx, id, f)

    def delete_by_id(self, id):
        found = self.find_by_id(id)
        if not found:
            print("Slog ne postoji")
            return
        bucket_idx, rec_idx = found
        with open(self.filename, "rb+") as f:
            f.seek(self.block_size * bucket_idx)
            block = self.read_block(f)

            while rec_idx < self.blocking_factor - 1:
                block[rec_idx] = block[rec_idx + 1]
                rec_idx += 1

            mother_bucket_idx = bucket_idx
            bucket_to_fill = block
            step = 1
            iter = 0

            while True:
                new_bucket_idx = self.find_new_bucket_idx(mother_bucket_idx, step)
                f.seek(self.block_size * new_bucket_idx)
                new_block = self.read_block(f)
                switch = False
                for j in range(self.blocking_factor):
                    if self.hash(new_block[j]['id']) != new_bucket_idx:
                        if self.is_inbetween(mother_bucket_idx, new_bucket_idx, new_block[j]['id']):
                            continue
                        switch = True
                        bucket_to_fill[self.blocking_factor - 1] = new_block[j]
                        f.seek(self.block_size * mother_bucket_idx)
                        self.write_block(f, bucket_to_fill)
                        index = j
                        while index < self.blocking_factor - 1:
                            new_block[index] = new_block[index + 1]
                            index += 1
                        break
                if switch:
                    mother_bucket_idx = new_bucket_idx
                    bucket_to_fill = new_block
                    step = 1
                else:
                    step += 1
                iter += 1
                if iter >= self.b - 1:
                    break

            f.seek(mother_bucket_idx * self.block_size)
            bucket_to_fill[self.blocking_factor - 1] = self.get_empty_rec()
            self.write_block(f, bucket_to_fill)

    def find_new_bucket_idx(self, mother_bucket_idx, i):
        new_idx = mother_bucket_idx + i
        if new_idx >= self.b:
            new_idx = new_idx % self.b
        return new_idx

    def is_inbetween(self, old_bucket_idx, new_bucket_idx, id):
        mother_bucket_idx = self.hash(id)
        while True:
            old_bucket_idx = (old_bucket_idx + 1) % self.b
            if old_bucket_idx == mother_bucket_idx:
                return True
            if old_bucket_idx == new_bucket_idx:
                return False
