from assistant import Assistant
from baker import Baker
from customer import Customer


class Shop:
    def __init__(self):
        pass

    def run(self, orders):
        baker = Baker()
        assistant = Assistant(baker)
        customer = Customer(orders)

        for i in range(len(orders)):
            assistant.handle_order(customer)


if __name__ == '__main__':
    Shop().run()
