from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8002


def send(queue_name, message):
    req = {
        "ime_niza": queue_name,
        "poruka": message
    }
    requests.post("http://localhost:9000/send", data=json.dumps(req))


def subscribe(queue_name, queue_group, consumer_name):
    req = {
        "ime_niza": queue_name,
        "grupa": queue_group,
        "port": port,
        "ime_consumera": consumer_name
    }

    requests.post("http://localhost:9000/subscribe", data=json.dumps(req))


@app.route("/printmessage", methods=['POST'])
def printmessage():
    message = json.loads(request.data)
    print(json.dumps(message, indent=2))
    return "", 200


if __name__ == "__main__":
    subscribe("niz2", "grupa", "printmessage")
    app.run(host="localhost", port=port, debug=True)
