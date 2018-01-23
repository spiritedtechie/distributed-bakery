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

    assert_items_equal(
        received_orders(baked_goods),
        ['I received 1 Cake made by Barry', 'I received 2 Brownies made by Barry'],
    )

def received_orders(baked_goods):
    return [order for order in baked_goods.text.split('\n') if order.strip()]

def assert_items_equal(list1, list2):
    assert set(list1) == set(list2)
