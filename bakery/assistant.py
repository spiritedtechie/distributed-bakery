class Assistant:

    def __init__(self, baker):
        self.baker = baker

    def handle_order(self, customer):
        order = customer.place_order()
        baked_goods = self.baker.listen_for(order)
        customer.receive_order(baked_goods)
