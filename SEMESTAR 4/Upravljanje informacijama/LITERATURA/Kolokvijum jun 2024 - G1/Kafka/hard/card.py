from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8005
cards = {}


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


@app.route("/suspend", methods=['POST'])
def suspend():
    message = json.loads(request.data)
    global cards

    if message['event'] == 'Yellow card':
        cards[message['team']]['yellow'] += 1
    if message['event'] == 'Red card':
        cards[message['team']]['red'] += 1

    if cards[message['team']]['red'] >= 2 or cards[message['team']]['yellow'] >= 4:
        send("teski_zadatak_card_event", message)

    return "", 200


if __name__ == "__main__":
    subscribe("teski_zadatak_event_card", "grupa", "suspend")
    app.run(host="localhost", port=port, debug=True)
