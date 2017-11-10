from flask import Flask, request, jsonify
import random
import time
app = Flask(__name__)

app.chance_of_hearing = random.random
BAKER_NAME = 'Barry'

@app.route('/v1/', methods=['POST'])
def drunk_baker_listen_for_order():
    if app.chance_of_hearing() > 0.5:
        order = request.get_json()
        order['madeBy'] = BAKER_NAME
        return jsonify(order)
    else:
        time.sleep(1000)
