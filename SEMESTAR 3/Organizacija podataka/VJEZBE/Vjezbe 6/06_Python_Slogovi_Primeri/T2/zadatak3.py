#!/usr/bin/python

import struct

IN_FILE = "username.csv"
OUT_FILE = "username.bin"
DELIMITER = ";"
FORMAT = "12si10s10s"
ENCODING = "ascii"
RECORD_SIZE = struct.calcsize(FORMAT)
f = 3

with open(OUT_FILE, "rb") as fin:
    fin.seek(RECORD_SIZE * f)

    for i in range(f):
        data_bytes = fin.read(RECORD_SIZE)

        if not data_bytes:
            break

        record = struct.unpack(FORMAT, data_bytes)

        decoded_record = []
        for item in record:
            if isinstance(item, bytes):
                item = item.decode("ascii").rstrip('\x00')

            decoded_record.append(item)

        print(decoded_record)
