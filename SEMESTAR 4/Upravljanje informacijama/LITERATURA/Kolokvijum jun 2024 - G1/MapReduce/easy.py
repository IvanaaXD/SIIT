import textwrap
from math import ceil


# Pomocna funkcija za pretvaranje hash mape (dict) u listu parova. Trebace vam u map_implementatipon funkciji
def dict_to_list_of_tuples(dict):
    list_of_tuples = []

    # Ako imate python 3 umesto dict.iteritems() treba da stavite dict.items()
    for key, value in dict.items():
        list_of_tuples.append((key, value))

    return list_of_tuples


# Treba da vrati list key-value parova (u pythonu to moze biti lista tuple-ova)
def map(data):
    list = []
    for d in data:
        key = d[1]
        value = 1
        list.append((key, value))
    return list


# Uzima listu key-value parova, treva ba vrati listu key-value parova sortirano po key elementu
def shuffle(data):
    data.sort(key=lambda x: x[0])
    return data


# Uzima listu key-value parova, treva ba vrati listu key-value parova
def reduce(data):
    dict = {}
    for d in data:
        if d[0] in dict:
            dict[d[0]] += d[1]
        else:
            dict[d[0]] = 1
    return dict_to_list_of_tuples(dict)


def parse_single_line(data):
    segments = data.split(',')
    return segments[0], segments[3]


def split_list(data, n):
    step = ceil(len(data) / n)
    return [data[i:i + step] for i in range(0, len(data), step)]


if __name__ == "__main__":
    with open("./data/cars.txt") as f:
        f.readline()
        lines = []
        for line in f:
            lines.append(line)

        processed_data = []
        for line in lines:
            processed_data.append(parse_single_line(line))

    data_chunks = split_list(processed_data, 4)

    data_after_map = []
    for chunk in data_chunks:
        ret_segment = map(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce1 = reduce(data_after_map)
