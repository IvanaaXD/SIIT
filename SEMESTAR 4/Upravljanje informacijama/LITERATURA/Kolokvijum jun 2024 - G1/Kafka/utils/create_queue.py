import requests
import json


def create(queue_name):
    req = {
        "ime": queue_name
    }
    requests.post("http://localhost:9000/create", data=json.dumps(req))


if __name__ == "__main__":
    create("lak_zadatak1")
    create("lak_zadatak2")

    create("srednji_zadatak_producer_filter")
    create("srednji_zadatak_filter_goal_consumer")
    create("srednji_zadatak_filter_stats_consumer")

    create("teski_zadatak_producer_event")
    create("teski_zadatak_event_offside_handball")
    create("teski_zadatak_event_consumer")
    create("teski_zadatak_offside_handball_response")
    create("teski_zadatak_card_event")
    create("teski_zadatak_event_card")
