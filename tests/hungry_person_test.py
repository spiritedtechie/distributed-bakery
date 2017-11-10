import requests

HUNGRY_PERSON_ENDPOINT = 'http://localhost:6000'


def test_hungry_person_will_always_receive_a_cake():
    response = requests.post(HUNGRY_PERSON_ENDPOINT + '/v1/order', json={'name': 'Cake', 'quantity': 1})
    assert response.text == 'I received 1 Cake made by Barry'
