from bakery.baker import Baker


def test_listen_for_order_of_3_brownies():
    assert Baker().listen_for(('Brownies', 3)) == ('Brownies', 3)


def test_listen_for_order_of_2_loaves_of_bread():
    assert Baker().listen_for(('Loaves of bread', 2)) == ('Loaves of bread', 2)
