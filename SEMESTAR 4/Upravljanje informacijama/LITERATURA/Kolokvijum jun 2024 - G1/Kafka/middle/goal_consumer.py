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


@app.route("/goal_consumer", methods=['POST'])
def goal_consumer():
    message = json.loads(request.data)
    print(message)
    return "", 200


if __name__ == "__main__":
    subscribe("srednji_zadatak_filter_goal_consumer", "grupa", "goal_consumer")
    app.run(host="localhost", port=port, debug=True)
