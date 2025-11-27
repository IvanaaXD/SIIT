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
        key = ''
        if d[0] < d[1]:
            key = d[0] + ':' + d[1]
            value = d[2] + ':0'
        else:
            key = d[1] + ':' + d[0]
            value = "0:" + d[2]
        list.append((key, value))
    return list


# Uzima listu key-value parova, treva ba vrati listu key-value parova
def reduce(data):
    result_dict = {}
    for key, value in data:
        val = value.split(':')
        if val[1] == 'G' or val[0] == 'G':
            if key in result_dict:
                old_goals = result_dict[key].split(':')
                new_goals = value.split(':')
                if new_goals[0] == 'G':
                    goals = str(int(old_goals[0]) + 1) + ':' + str(int(old_goals[1]) + int(new_goals[1]))
                else:
                    goals = str(int(old_goals[0]) + int(new_goals[0])) + ':' + str(int(old_goals[1]) + 1)
                result_dict[key] = goals
            else:
                if val[1] == 'G':
                    value = '0:1'
                else:
                    value = '1:0'
                result_dict[key] = value
        else:
            continue
    return dict_to_list_of_tuples(result_dict)


def parse_single_line(data):
    segments = data.split(',')
    # tim, dogadjaj, tim protiv koga igraju
    return segments[0], segments[2][:6], segments[1]


def split_list(data, n):
    step = ceil(len(data) / n)
    return [data[i:i + step] for i in range(0, len(data), step)]


if __name__ == "__main__":
    with open("./data/message.txt") as f:
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

    data_after_reduce1 = reduce(data_after_map)
    print(data_after_reduce1)
