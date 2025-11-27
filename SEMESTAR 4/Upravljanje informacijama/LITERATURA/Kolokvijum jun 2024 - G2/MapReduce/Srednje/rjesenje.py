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
        key = d[0] + '_' + d[1]
        value = d[2]
        list.append((key, value))
    return list


def reduce(data):
    dict = {}
    for key, value in data:
        if value.isnumeric() and 1 <= int(value) <= 5:
            if key in dict:
                list = dict[key]
                list[0] += float(value)
                list[1] += 1
                dict[key] = list
            else:
                list = []
                list.append(float(value))
                list.append(1)
                dict[key] = list

    dict2 = {}
    for key, value in dict.items():
        dict2[key] = str(value[0] / value[1])

    return dict_to_list_of_tuples(dict2)


def parse_single_line(line):
    segments = line.split(',')
    # ucenik, predmet, ocjena
    return segments[0], segments[1], segments[2][:1]


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
        ret_segment = map(chunk)
        data_after_map.extend(ret_segment)

    data_after_reduce = reduce(data_after_map)

    print(data_after_reduce)
