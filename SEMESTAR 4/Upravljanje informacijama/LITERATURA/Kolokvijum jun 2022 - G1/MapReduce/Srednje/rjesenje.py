from math import ceil


# Pogledajte sadrza csv fajla SalesJan2009. Treba da se za svaku drzavu nadje najskuplja transakcija za svaku od
# kreditnih kartica: Primer resenja: Amerika - [visa-1200,Mastercard-3420,dina-1234,amex-3452] Srbija - [visa-1111,
# mastercard-2341,dina-1167]

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
    for x in data:
        key = x[1]
        value = x[0]
        list.append((key, value))
    return list


# list[0] - br stvari
# list[1] - najduza rijec
# list[2] - najkraca rijec
def reduce(data):
    max_values = {}
    for pair in data:
        if pair[0] == 'NULL' or pair[0] == 'NULL\n' or pair[1] == 'NULL' or pair[1] == 'NULL\n':
            continue
        if pair[0] in max_values:
            list = max_values[pair[0]]
            list[0] += 1

            if len(list[1]) < len(pair[1]):
                list[1] = pair[1]

            if len(list[2]) > len(pair[1]):
                list[2] = pair[1]

            max_values[pair[0]] = list

        else:
            list = []
            list.append(1)
            list.append(pair[1])
            list.append(pair[1])
            max_values[pair[0]] = list
    return dict_to_list_of_tuples(max_values)


def parse_single_line(line):
    segments = line.split(',')
    return segments[0], segments[1][:-1]


if __name__ == "__main__":
    with open("data") as file:
        all_lines = []
        for line in file:
            all_lines.append(line)

        processed_data = []
        for line in all_lines:
            processed_line = parse_single_line(line)
            if processed_line is not None:  # Only append if the line is not None
                processed_data.append(processed_line)

    # Podela fajla na 4 manje delove koji ce se 'paralelno' obradjivati
    data_chunks = split_list(processed_data, 4)

    data_after_map1 = []
    for chunk in data_chunks:
        ret_segment = map(chunk)
        data_after_map1.extend(ret_segment)

    data_after_reduce = reduce(data_after_map1)
    print(data_after_reduce)
