from flask import Flask, request
import requests
app = Flask(__name__)

BAKER_ENDPOINT = 'http://baker:5000/v1/order'

@app.route('/v1/order', methods=['POST'])
def make_an_order():
    order = yell_the_order()
    return say_what_was_received(order.json())


def yell_the_order():
    return requests.post(BAKER_ENDPOINT, json=request.get_json())

def say_what_was_received(order):
    return "I received %s %s made by %s" % (order['quantity'],
                                            order['name'],
                                            order['madeBy'])
