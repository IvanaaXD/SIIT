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


def map(data):
    list = []
    for d in data:
        key = d[0]
        value = float(d[1])
        list.append((key, value, 0, 0))
        key = d[2]
        value = float(d[3])
        list.append((key, value, 0, 0))
        key = d[4]
        value = float(d[5])
        list.append((key, value, 0, 0))
    return list


# list = [posljednja vrijednost, skok, pad]
def reduce(data):
    dict = {}
    for d in data:

        if d[0] in dict:
            list = dict[d[0]]

            if d[1] - list[0] > list[1]:
                list[1] = d[1] - list[0]

            if d[1] - list[0] < list[2]:
                list[2] = d[1] - list[0]

            list[0] = d[1]
            dict[d[0]] = list

        else:
            list = []
            list.append(d[1])
            list.append(d[2])
            list.append(d[3])
            dict[d[0]] = list

    return dict_to_list_of_tuples(dict)


def parse_single_line(line):
    segments = line.split(',')
    if not segments[2].isnumeric() and not segments[4].isnumeric() and not segments[6].strip().isnumeric():
        print(line)
    return segments[1], segments[2], segments[3], segments[4], segments[5], segments[6][:-1]


if __name__ == "__main__":
    with open("data") as file:
        all_lines = []
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
        ret_segment = map(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce = reduce(data_after_map)
    print(data_after_reduce)
