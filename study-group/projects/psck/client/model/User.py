

class User(object):
    u_id = None

    @staticmethod
    def login(u_id):
        User.u_id = u_id

    @staticmethod
    def is_login():
        if User.u_id is None:
            return False
        else:
            return True