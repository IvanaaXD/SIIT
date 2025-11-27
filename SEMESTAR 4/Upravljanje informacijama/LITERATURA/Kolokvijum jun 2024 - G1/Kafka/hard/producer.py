import requests
import json
import random


# Poruka treba da bude dictionary
def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))


if __name__ == "__main__":
    dogadjaj = ['G', 'YC', 'RC']
    ekipe = ['ITA', 'FRA', 'SRB', 'MNE', 'NET']

    while True:
        n = int(input("Koliko transakcija zelite da generisete: "))
        for _ in range(n):
            send('srednji_zadatak_producer_filter', {
                "ekipa": random.choice(ekipe),
                "dogadjaj": random.choice(dogadjaj)
            })
