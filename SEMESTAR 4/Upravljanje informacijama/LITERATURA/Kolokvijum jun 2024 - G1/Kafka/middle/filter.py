from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8001
num = 0
num_goals = 0
num_red_cards = 0
num_yellow_cards = 0
num_throw_in = 0


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
    global num
    global num_goals
    global num_red_cards
    global num_red_cards
    global num_throw_in

    num += 1

    if message['dogadjaj'] == "G":
        num_goals += 1
        send("srednji_zadatak_filter_goal_consumer", message)

    if message['dogadjaj'] == "RC":
        num_red_cards += 1

    if message['dogadjaj'] == "YC":
        num_red_cards += 1

    if message['dogadjaj'] == "TI":
        num_throw_in += 1

    if num == 10:
        list = [num_goals, num_throw_in, num_yellow_cards, num_red_cards]
        num = 0
        send("srednji_zadatak_filter_stats_consumer", list)

    return "", 200


if __name__ == "__main__":
    subscribe("srednji_zadatak_producer_filter", "grupa", "filter")
    app.run(host="localhost", port=port, debug=True)
