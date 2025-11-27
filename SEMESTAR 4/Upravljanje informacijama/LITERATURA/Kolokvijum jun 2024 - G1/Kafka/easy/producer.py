import requests
import json
import random


def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))


if __name__ == "__main__":
    gol = ['G', 'NG']
    ekipe = ['ITA', 'FRA', 'SRB', 'MNE', 'NET']

    while True:
        n = int(input("Koliko transakcija zelite da generisete: "))
        for _ in range(n):
            send('lak_zadatak1', {
                "ekipa": random.choice(ekipe),
                "gol": random.choice(gol)
            })
