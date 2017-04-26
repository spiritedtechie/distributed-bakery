from bakery.baker import Baker, Reply


def test_give_order_to_happy_baker_who_always_says_ok():
    assert Baker().give_order((3, 'brownies')) == Reply.OK


def test_give_order_to_grumpy_baker_who_doesnt_accept_empty_orders():
    assert Baker().give_order(()) == Reply.NO
