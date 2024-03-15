import struct

file = "dots.csv"
fileb = "dots.bin"

FORMAT1 = "ii"
FORMAT2 = "fff"
ENCODING = "ascii"

RECORD_SIZE1 = struct.calcsize(FORMAT1)
RECORD_SIZE2 = struct.calcsize(FORMAT2)

dots = []
num_of_dots = 0

with open(file, 'r') as fin:

    text = fin.read()
    lines = text.split("\n")

    for line in lines:

        dot = []

        dott = line.split(" ")
        dot.append(float(dott[0]))
        dot.append(float(dott[1]))
        dot.append(float(dott[2]))
        dots.append(dot)
        num_of_dots += 3

    print(dots)

with open(fileb, 'wb') as fout:

    data = [num_of_dots, 3]

    bytes_header = struct.pack(FORMAT1, *data)
    fout.write(bytes_header)

    for row in dots:
        row_bytes = struct.pack(FORMAT2, *row)
        fout.write(row_bytes)

with open(fileb, 'rb') as fin:

    header_bytes = fin.read(RECORD_SIZE1)
    num_of_dots, _ = struct.unpack(FORMAT1, header_bytes)
    print(num_of_dots, _)

    for _ in range(num_of_dots // 3):
        record_bytes = fin.read(RECORD_SIZE2)
        record = struct.unpack(FORMAT2, record_bytes)
        print(record)

