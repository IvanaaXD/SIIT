import random

from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8004


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


@app.route("/handball", methods=['POST'])
def handball():
    message = json.loads(request.data)
    result = random.choice([True, False])
    message['result'] = result
    send("teski_zadatak_offside_handball_response", message)
    return "", 200


if __name__ == "__main__":
    subscribe("teski_zadatak_event_offside_handball", "handball", "handball")
    app.run(host="localhost", port=port, debug=True)
