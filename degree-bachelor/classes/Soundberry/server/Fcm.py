import requests
import json
from time import ctime


class Fcm(object):
    def __init__(self, name):

        self.host = 'https://fcm.googleapis.com/fcm/send'
        self.server_key = 'AIzaSyA3nOPC1X4__JDg4V_kXOxuboEQgKE8OmQ'
        self.token_key = "fprJgQ-6MmU:APA91bEwaoL-YESqRIXHJfwM5kdJ-TGfrng08cpm1eexUF2vADu0DbU7upQACT7OpwA-ZV3KaNoeLGxn8JvR3Q9a4elh7PTIytQYWJeeSbvt4p4cVfBH3ASijouY64Ia1orn5gy7ViCi"
        self.headers = {"Content-type": "application/json",
                       "Authorization": "key="+self.server_key}
        self.data = {"data": {
                "name": name,
                "time":  str(ctime())
            },
                "to": self.token_key
            }

    def send(self):
        r = requests.post(self.host, data=json.dumps(self.data), headers=self.headers)
        # print r.status_code
        # print r.text