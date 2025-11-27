from flask import Flask, request
import requests
import json


app = Flask(__name__)
port = 8002


def subscribe(queue_name, queue_group, consumer_name):
    req = {
        "ime_niza": queue_name,
        "grupa": queue_group,
        "port": port,
        "ime_consumera": consumer_name
    }

    requests.post("http://localhost:9000/subscribe", data=json.dumps(req))


@app.route("/consumer", methods=['POST'])
def consumer():
    message = json.loads(request.data)
    print(message)
    return "", 200


if __name__ == "__main__":
    subscribe("lak_zadatak2", "grupa", "consumer")
    app.run(host="localhost", port=port, debug=True)
