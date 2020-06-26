import threading
import time
from Fcm import Fcm
import setting


class FCMThread(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        self.hi = 1
        # self.q = q

    def run(self):
        while True:
            time.sleep(3)
            for k, v in setting.myList.items():
                if v:
                    # self.q.queue.clear()
                    Fcm(k).send()
                    print("send FCM "+k)
                    setting.myList[k] = False