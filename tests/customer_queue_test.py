from bakery.customer_queue import CustomerQueue


def test_take_order_from_queue_of_one_customer():
    assert CustomerQueue([(5, 'cakes')]).take_order() == (5, 'cakes')


def test_take_order_from_queue_of_no_customers():
    assert CustomerQueue([]).take_order() == ()


def test_take_order_from_queue_of_multiple_customers():
    cq = CustomerQueue([(5, 'cakes'), (10, 'muffins')])
    assert cq.take_order() == (5, 'cakes')
    assert cq.take_order() == (10, 'muffins')
