import requests
import random
import json


# Poruka treba da bude dictionary 
def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))


if __name__ == "__main__":
    moguci_tipovi_majica = ['XXS', 'XS', 'S', 'M', 'L', 'XL', 'XXL']

    while True:
        n = int(input("Koliko transakcija zelite da generisete: "))
        for _ in range(n):
            send('niz1', {
                'tip_majice': random.choice(moguci_tipovi_majica),
            })
