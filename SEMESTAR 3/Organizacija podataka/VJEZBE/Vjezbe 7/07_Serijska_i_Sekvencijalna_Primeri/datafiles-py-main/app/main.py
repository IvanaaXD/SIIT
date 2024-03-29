
import os
import struct

from constants import *
from record import Record
from sequential_file import SequentialFile
from serial_file import SerialFile
from binary_file import BinaryFile
from hash_file import HashFile


def read_txt(fn):
    rows = []
    with open(fn, "r") as f:
        for line in f.readlines():
            cols = line.split()
            rows.append({
                "id": int(cols[0]),
                "name": cols[1],
                "q": float(cols[2]),
                "status": 1
            })
    return rows


def main():
    rec = Record(ATTRIBUTES, FMT, CODING)
    fn = "sample.dat"
    # binary_file = SerialFile(fn, rec, F)
    # binary_file = SequentialFile(fn, rec, F)
    binary_file = HashFile(fn, rec, F, B)
    binary_file.init_file()

    rows = read_txt("in.txt")

    for i in range(len(rows)):
        binary_file.insert_record(rows[i])

    binary_file.print_file()

    print(binary_file.find_by_id(49))
    print(binary_file.find_by_id(35))

    binary_file.delete_by_id(35)
    binary_file.delete_by_id(8)
    binary_file.delete_by_id(28)

    binary_file.print_file()


if __name__ == "__main__":
    main()
