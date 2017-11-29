import random
import time
from sanic import Sanic
from sanic.response import json

app = Sanic()

app.chance_of_hearing = random.random
BAKER_NAME = 'Barry'

@app.route('/v1/order', methods=['POST'])
async def drunk_baker_listen_for_order(request):
    if baker_hears_the_order():
        return prepare_the_order(request.json)
    else:
        baker_is_drunnkjdfdk()

def baker_hears_the_order():
    return app.chance_of_hearing() > 0.5

def prepare_the_order(order):
    order['madeBy'] = BAKER_NAME
    return json(order)

def baker_is_drunnkjdfdk():
    time.sleep(1000)
