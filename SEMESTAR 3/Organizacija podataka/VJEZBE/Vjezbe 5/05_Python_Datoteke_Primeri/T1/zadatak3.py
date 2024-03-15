file = "dat.txt"

with open(file, 'w') as f:
    char = input("Unesite zeljeni karakter: ")
    d = input("Unesite parametar d: ")

    param = 2 * int(d) + 1

    for i in range(param):
        if i % 2 == 1:
            num_of_char = i + 1
            num_of_spaces = (param - i) // 2

            line = " " * num_of_spaces + char * i + " " * num_of_spaces + "\n"
            f.write(line)

    for i in range(param, 0, -1):
        if i % 2 == 1:
            num_of_char = i + 1
            num_of_spaces = (param - i) // 2

            line = " " * num_of_spaces + char * i + " " * num_of_spaces + "\n"
            f.write(line)

with open(file, 'r') as f:
    text = f.read()
    print(text)
