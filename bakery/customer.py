class Customer:
    orders = []

    def __init__(self, orders):
        self.orders = orders

    def place_order(self):
        if len(self.orders) == 0:
            return ()
        else:
            return self.orders.pop(0)

    def receive_order(self, baked_goods):
        print "I received %s" % (baked_goods,)
