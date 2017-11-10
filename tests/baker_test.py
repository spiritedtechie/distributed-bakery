from baker import baker
import json
import multiprocessing
import time


def test_drunk_baker_hears_order_and_returns_cake():
    baker.app.chance_of_hearing = lambda: 1
    response = place_order_with_baker()
    assert json.loads(response.data) == {'name': 'Cake', 'quantity': 1, 'madeBy': 'Barry'}

def test_drunk_baker_does_not_hear_order_and_never_responds():
    baker.app.chance_of_hearing = lambda: 0
    p = multiprocessing.Process(target=place_order_with_baker)
    p.start()

    time.sleep(2)
    assert p.is_alive()
    p.terminate()

def place_order_with_baker():
    baker_client = baker.app.test_client()
    return baker_client.post('/v1/',
                             data=json.dumps({'name': 'Cake', 'quantity': 1}),
                             content_type='application/json')
