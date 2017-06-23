from bakery.customer import Customer


def test_place_order_from_queue_of_one_customer():
    assert Customer([(5, 'cakes')]).place_order() == (5, 'cakes')


def test_place_order_from_queue_of_no_customers():
    assert Customer([]).place_order() == ()


def test_place_order_from_queue_of_multiple_customers():
    customer = Customer([(5, 'cakes'), (10, 'muffins')])
    assert customer.place_order() == (5, 'cakes')
    assert customer.place_order() == (10, 'muffins')
