from bakery.shop import Shop


def test_shop_runs_correctly(capfd):
    shop = Shop()
    shop.run([('Brownies', 3), ('Cake', 1), ('Muffins', 2)])
    out, err = capfd.readouterr()
    assert out == "I received ('Brownies', 3)\n" \
                  "I received ('Cake', 1)\n" \
                  "I received ('Muffins', 2)\n"
