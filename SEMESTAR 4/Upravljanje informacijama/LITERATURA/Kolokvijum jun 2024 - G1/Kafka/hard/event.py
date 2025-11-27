from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8001
responses = []
suspended = []


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


@app.route("/event", methods=['POST'])
def event():
    message = json.loads(request.data)
    if message['team'] not in suspended:
        if message['event'] == 'Goal':
            send("teski_zadatak_event_offside_handball", message)
        if message['event'] == 'Yellow card' or message['event'] == 'Red card':
            send("teski_zadatak_event_card", message)
    return "", 200


@app.route("/consumer_response", methods=['POST'])
def consumer_response():
    message = json.loads(request.data)
    global responses
    responses.append(message['result'])
    if len(responses) == 2:
        if responses[0] == 'False' and responses[1] == 'False':
            send("teski_zadatak_event_consumer", message)
        responses.clear()
    return "", 200


@app.route("/card_response", methods=['POST'])
def card_response():
    message = json.loads(request.data)
    global suspended
    suspended.append(message['team'])
    return "", 200


if __name__ == "__main__":
    subscribe("teski_zadatak_producer_event", "grupa", "event")
    subscribe("teski_zadatak_offside_handball_response", "grupa", "consumer_response")
    subscribe("teski_zadatak_card_event", "grupa", "card_response")
    app.run(host="localhost", port=port, debug=True)
