

class CustomerQueue:
    orders = []

    def __init__(self, orders):
        self.orders = orders

    def take_order(self):
        if len(self.orders) == 0:
            return ()
        else:
            return self.orders.pop(0)
