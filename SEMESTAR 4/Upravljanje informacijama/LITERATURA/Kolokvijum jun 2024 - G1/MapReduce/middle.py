import textwrap
from math import ceil


# Pomocna funkcija za pretvaranje hash mape (dict) u listu parova. Trebace vam u map_implementatipon funkciji
def dict_to_list_of_tuples(dict):
    list_of_tuples = []

    # Ako imate python 3 umesto dict.iteritems() treba da stavite dict.items()
    for key, value in dict.items():
        list_of_tuples.append((key, value))

    return list_of_tuples


# Uzima listu key-value parova, treva ba vrati listu key-value parova sortirano po key elementu
def shuffle(data):
    data.sort(key=lambda x: x[0])
    return data


# Treba da vrati list key-value parova (u pythonu to moze biti lista tuple-ova)
def map(data):
    list = []
    for d in data:
        key = d[0]
        value = d[1]
        list.append((key, value))
    return list


# Uzima listu key-value parova, treva ba vrati listu key-value parova
def reduce(data):
    dict = {}  # key: godina, value: list[sum_trans, num_trans]
    for d in data:
        if d[0] in dict:
            list = dict[d[0]]
            list[0] += d[1]
            list[1] += 1
        else:
            list = []
            list.append(d[1])
            list.append(1)
            dict[d[0]] = list

    dict2 = {}
    for d in dict:
        key = d
        value = dict[d][0] / dict[d][1]
        dict2[key] = value
    return dict_to_list_of_tuples(dict2)


def parse_single_line(data):
    segments = data.split(',')
    if segments[1] == 'NULL' or segments[2] == 'NULL':
        return
    # 12.3.2024.
    seg = segments[1].split('.')
    return seg[2], segments[2]


def split_list(data, n):
    step = ceil(len(data) / n)
    return [data[i:i + step] for i in range(0, len(data), step)]


if __name__ == "__main__":
    with open("./data/transactions.txt") as f:
        lines = []
        for line in f:
            lines.append(line)

        processed_data = []
        for line in lines:
            d = parse_single_line(line)
            if d is None:
                continue
            processed_data.append(d)

    data_chunks = split_list(processed_data, 4)

    data_after_map = []
    for chunk in data_chunks:
        ret_segment = map(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce = reduce(data_after_map)
    for d in data_after_reduce:
        print(d)
