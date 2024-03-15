import re

file = "in.txt"

with open(file, 'r') as f:

    pattern = r'[.,:;!+_=]'

    text = f.read()
    sentence = text.split(".")
    sentences = []
    for s in sentence:
        if s == "":
            continue
        sentences.append(s)

    words = []
    for s in range(len(sentences)):
        ss = sentences[s].split(" ")
        for sss in ss:
            if sss == "":
                continue
            elif "\n" in sss:
                f = sss.split("\n")
                for ff in f:
                    if ff == "":
                        continue
                    words.append(ff)
            else:
                words.append(sss)

    chars = list(text)

    numbers = 0
    alpha = 0
    for char in chars:
        if char.isdigit():
            numbers += 1
        elif char.isalpha():
            alpha += 1

    matches = re.findall(pattern, text)

    print("Num of alphas is: ", alpha)
    print("Num of numbers is: ", numbers)
    print("Num of characters is: ", len(matches))
    print("Num of words is: ", len(words))
    print("Num of sentences is: ", len(sentences))

