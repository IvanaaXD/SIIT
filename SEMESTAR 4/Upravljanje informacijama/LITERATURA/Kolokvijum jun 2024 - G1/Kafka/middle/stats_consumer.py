from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8003


def subscribe(queue_name, queue_group, consumer_name):
    req = {
        "ime_niza": queue_name,
        "grupa": queue_group,
        "port": port,
        "ime_consumera": consumer_name
    }

    requests.post("http://localhost:9000/subscribe", data=json.dumps(req))


@app.route("/stats_consumer", methods=['POST'])
def stats_consumer():
    message = json.loads(request.data)
    print("Num of goals: ", message[0])
    print("Num of red cards: ", message[3])
    print("Num of yellow cards: ", message[2])
    print("Num of throw ins: ", message[1])
    return "", 200


if __name__ == "__main__":
    subscribe("srednji_zadatak_filter_stats_consumer", "grupa", "stats_consumer")
    app.run(host="localhost", port=port, debug=True)
