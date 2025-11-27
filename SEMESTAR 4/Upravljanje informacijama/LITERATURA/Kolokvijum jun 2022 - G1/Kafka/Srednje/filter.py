from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8001


def subscribe(queue_name, queue_group, consumer_name):
    req = {
        "ime_niza": queue_name,
        "grupa": queue_group,
        "port": port,
        "ime_consumera": consumer_name
    }

    requests.post("http://localhost:9000/subscribe", data=json.dumps(req))


def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))


@app.route("/filter", methods=['POST'])
def filter():
    message = json.loads(request.data)
    print("Provera velicine majice:")
    print(json.dumps(message, indent=2))
    if message['tip_majice'] == 'XXL':
        send('niz2', message)
    else:
        send('niz3', message)
    return "", 200


if __name__ == "__main__":
    subscribe("niz1", "grupa", "filter")
    app.run(host="localhost", port=port, debug=True)
