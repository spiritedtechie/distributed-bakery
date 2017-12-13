import requests
import time

HUNGRY_PERSON_ENDPOINT = 'http://localhost:5000/v1'


def test_hungry_person_will_always_receive_exactly_what_they_asked_for():
    response = requests.post(HUNGRY_PERSON_ENDPOINT + '/order',
                                 json={'name': 'Cake', 'quantity': 1})
    assert response.text == 'OK'
    response = requests.post(HUNGRY_PERSON_ENDPOINT + '/order',
                             json={'name': 'Brownies', 'quantity': 2})
    assert response.text == 'OK'

    time.sleep(3)

    baked_goods = requests.get(HUNGRY_PERSON_ENDPOINT + '/show-me-what-you-got')

    assert baked_goods.text == 'I received 1 Cake made by Barry\n' \
                               'I received 2 Brownies made by Barry\n'

