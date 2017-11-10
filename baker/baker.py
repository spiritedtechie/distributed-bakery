from flask import Flask, request, jsonify
import random
import time
app = Flask(__name__)

app.chance_of_hearing = random.random
BAKER_NAME = 'Barry'


@app.route('/v1/order', methods=['POST'])
def drunk_baker_listen_for_order():
    if baker_hears_the_order():
        return prepare_the_order(request.get_json())
    else:
        baker_is_drunnkjdfdk()


def baker_hears_the_order():
    return app.chance_of_hearing() > 0.5

def prepare_the_order(order):
    order['madeBy'] = BAKER_NAME
    return jsonify(order)

def baker_is_drunnkjdfdk():
    time.sleep(1000)
