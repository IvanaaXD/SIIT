#!/usr/bin/python

import struct

IN_FILE = "username.csv"
OUT_FILE = "username.bin"
DELIMITER = ";"
FORMAT = "12si10s10s"
ENCODING = "ascii"

with open(IN_FILE) as fin, open(OUT_FILE, "wb") as fout:
    # Skip the header
    fin.readline()

    while True:
        line = fin.readline()

        if not line:
            break

        fields = line.strip().split(DELIMITER)

        # Convert fields to record
        record = (
            fields[0],
            int(fields[1]),
            fields[2],
            fields[3]
        )

        # Encode strings and print the record
        record = [
            v.encode(ENCODING) if isinstance(v, str) else v
            for v in record
        ]
        print(record)

        # Pack and write the record to the binary file
        record_bytes = struct.pack(FORMAT, *record)
        fout.write(record_bytes)

if __name__ == '__main__':
    pass
