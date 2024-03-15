import pickle

file = "data.csv"
fileb = "weather_data.bin"

weather_data = []

with open(file, 'r') as f:

    next(f)

    for line in f:
        values = line.strip().split(', ')

        min_temp = float(values[2])
        max_temp = float(values[3])

        data_tuple = (values[0], values[1], min_temp, max_temp)
        weather_data.append(data_tuple)

with open(fileb, 'wb') as binary_file:
    pickle.dump(weather_data, binary_file)

with open(fileb, 'rb') as binary_file:
    loaded_data = pickle.load(binary_file)

print("Data loaded from", fileb)
print(loaded_data)
