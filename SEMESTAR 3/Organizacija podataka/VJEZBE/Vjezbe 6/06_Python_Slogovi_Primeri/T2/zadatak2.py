import struct

file = "dots.csv"
fileb = "dots.bin"
filebb = "centorid.bin"

FORMAT1 = "ii"
FORMAT2 = "fff"
ENCODING = "ascii"

RECORD_SIZE1 = struct.calcsize(FORMAT1)
RECORD_SIZE2 = struct.calcsize(FORMAT2)

dots = []

with open(fileb, 'rb') as fin:

    header_bytes = fin.read(RECORD_SIZE1)
    num_of_dots, k = struct.unpack(FORMAT1, header_bytes)
    print(num_of_dots, k)

    for k in range(num_of_dots // k):
        record_bytes = fin.read(RECORD_SIZE2)
        x, y, z = struct.unpack(FORMAT2, record_bytes)

        dot = [x, y, z, 0]
        dots.append(dot)

sum1 = 0
sum2 = 0
sum3 = 0

for i in range(num_of_dots // k):
    dot = dots[k]
    sum1 += dot[0]
    sum2 += dot[1]
    sum3 += dot[2]

teziste = [sum1/k, sum2/k, sum3/k]
print(dots)
print(teziste)
