
def main():
    customer_queue = new Queue(CUSTOMER)
    baker_queue = new Queue(BAKER)
    shelve_queue = new Queue(SHELVE)

    while True:
        order = [next_id(), customer_queue.take_order()]
        baker_queue.place_order(order)
        if not shelve_queue.empty():
            shelve_queue.take_order()
        customer_queue.deliver_order()

if __name__ == "__main__":
    # execute only if run as a script
    main()