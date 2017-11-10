from flask import Flask, request
import requests
app = Flask(__name__)

BAKER_ENDPOINT = 'http://baker:5000'

@app.route('/v1/order', methods=['POST'])
def make_an_order():
    order = requests.post(BAKER_ENDPOINT + '/v1/', json=request.get_json())

    quantity = order.json()['quantity']
    item = order.json()['name']
    made_by = order.json()['madeBy']

    return "I received %s %s made by %s" % (quantity, item, made_by)
