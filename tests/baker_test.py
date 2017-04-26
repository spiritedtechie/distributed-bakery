from enum import Enum


class Reply(Enum):
    OK = 'Ok, sure'
    NO = 'Not gonna do that'


class Baker:
    def __init__(self):
        pass

    def give_order(self, order):
        if order:
            return Reply.OK
        else:
            return Reply.NO


def test_give_order_to_happy_baker_who_always_says_ok():
    assert Baker().give_order((3, 'brownies')) == Reply.OK


def test_give_order_to_grumpy_baker_who_doesnt_accept_empty_orders():
    assert Baker().give_order(()) == Reply.NO
