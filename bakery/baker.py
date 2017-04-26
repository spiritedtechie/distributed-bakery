from enum import Enum


class Reply(Enum):
    OK = 'Ok, sure'
    NO = 'Not gonna do that'


class Baker:
    def __init__(self):
        pass

    def give_order(self, order):
        if order:
            return Reply.OK
        else:
            return Reply.NO
