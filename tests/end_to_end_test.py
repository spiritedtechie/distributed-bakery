import requests

HUNGRY_PERSON_ENDPOINT = 'http://localhost:5000/v1/order'


def test_hungry_person_will_always_receive_a_cake():
    response = requests.post(HUNGRY_PERSON_ENDPOINT,
                             json={'name': 'Cake', 'quantity': 1})

    assert response.text == 'I received 1 Cake made by Barry'

def test_hungry_person_several_times():
    for x in range(0, 2):
        test_hungry_person_will_always_receive_a_cake()
