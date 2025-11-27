from math import ceil


# Pomocna funkcija za pretvaranje hash mape (dict) u listu parova. Trebace vam u map_implementatipon funkciji
def dict_to_list_of_tuples(dict):
    list_of_tuples = []

    for key, value in dict.items():
        list_of_tuples.append((key, value))

    return list_of_tuples


# Pomocna funkcija koja list "list" deli na "n" podlista
def split_list(list, n):
    step = ceil(len(list) / n)
    return [list[i:i + step] for i in range(0, len(list), step)]


def shuffle_implementation(data):
    data.sort(key=lambda x: x[0])
    return data


def map1(data):
    list = []
    for d in data:
        key = d[0] + '_' + d[1]
        value = int(d[2])
        list.append((key, value))
    return list


def reduce1(data):
    dict = {}
    for key, value in data:
        if key in dict:
            list = dict[key]
            list[0] += value
            list[1] += 1
            dict[key] = list
        else:
            dict[key] = [value, 1]

    dict2 = {}
    for key, value in dict.items():
        dict2[key] = int(value[0] / value[1])

    return dict_to_list_of_tuples(dict2)


def map2(data):
    list = []
    for d in data:
        key = d[0].split('_')[0]
        value = d[1]
        list.append((key, value))
    return list


def reduce2(data):
    dict = {}
    for key, value in data:
        if key in dict:
            list = dict[key]
            list[0] += value
            list[1] += 1
            dict[key] = list
        else:
            dict[key] = [value, 1]

    dict2 = {}
    for key, value in dict.items():
        dict2[key] = int(value[0] / value[1])

    return dict_to_list_of_tuples(dict2)


def parse_single_line(line):
    segments = line.split(',')
    return segments[0], segments[1], segments[2]


if __name__ == "__main__":
    with open("grades.csv") as file:
        all_lines = []
        file.readline()
        for line in file:
            all_lines.append(line)

        processed_data = []
        for line in all_lines:
            processed_line = parse_single_line(line)
            processed_data.append(processed_line)

    # Podela fajla na 4 manje delove koji ce se 'paralelno' obradjivati
    data_chunks = split_list(processed_data, 4)

    data_after_map = []
    for chunk in data_chunks:
        ret_segment = map1(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce = reduce1(data_after_map)

    data_chunks = split_list(data_after_reduce, 4)

    data_after_map = []
    for chunk in data_chunks:
        ret_segment = map2(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce = reduce2(data_after_map)

    print(data_after_reduce)
