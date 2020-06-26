import pymongo
import yaml

from Util import MyYaml


class LoginDao(object):

    connection = pymongo.MongoClient(MyYaml.mongodb_host, MyYaml.mongodb_port)
    db = connection.test
    collection = db.user

    @staticmethod
    def login(u_id, u_pw):
        docs = LoginDao.collection.find({"id": u_id, "pw": u_pw})
        if docs.count():
            return True
        else:
            return False


class JoinDao(object):

    connection = pymongo.MongoClient(MyYaml.mongodb_host, MyYaml.mongodb_port)
    db = connection.test
    collection = db.user

    @staticmethod
    def join(u_id, u_pw):
        JoinDao.collection.insert({'id': u_id, 'pw': u_pw})
        return True


