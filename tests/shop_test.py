from bakery.shop import Shop


def test_shop_runs_correctly(capfd):
    shop = Shop()
    shop.run([('Brownies', 3), ('Cake', 1), ('Muffins', 2)])

    assert_customer_orders(capfd,
                           "I received 3 Brownies\n"
                           "I received 1 Cake\n"
                           "I received 2 Muffins\n")


def assert_customer_orders(capfd, orders):
    out, err = capfd.readouterr()
    assert out == orders
