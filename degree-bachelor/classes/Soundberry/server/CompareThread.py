import threading
import math
from scipy import spatial
import setting


class CompareThread(threading.Thread):  # Thread Extends

    def __init__(self, id, dataQueue, list, cos_com):
        threading.Thread.__init__(self)
        self.id = id  # for print
        self.dataQueue = dataQueue  # data queue
        self.sum = cos_com
        self.list = list
        self.__suspend = False
        self.__exit = False
        self.list2 = []

    def run(self):

        i = 2
        while True:  # thread task

            if not self.dataQueue.empty():
                self.sum += self.cos_compare(self.dataQueue.get(), self.list[i])
                sim = self.cos_similarity(self.sum / i)
                self.list2.append(sim)
                i += 1

                cond = i/10

                if cond == 0:
                    if sim < 90:
                        break
                    else:
                        continue
                elif cond == 1:
                    if sim < 90:
                        break
                    else:
                        continue
                elif cond == 2:
                    if sim < 90:
                        break
                    else:
                        continue
                elif cond == 3:
                    if sim < 90:
                        break
                    else:
                        continue
                if i == len(self.list)-1 and sim > 84:
                    print(self.id, sim, "Success")
                    print(self.list2)
                    setting.myList[self.id[:-1]] = True

                    break
                elif i == len(self.list)-1:
                    break
                else:
                    continue

        self.my_suspend(sim)

    def my_suspend(self, sim):
        self.__suspend = True

    def my_exit(self):
        self.__exit = True

    def status(self):
        return self.__suspend

    @staticmethod
    def cos_compare(a, b):
        result = 1 - spatial.distance.cosine(a, b)
        return result

    @staticmethod
    def cos_similarity(a):
        return (a+1) * 50

    @staticmethod
    def compare(a, b):
        temp1 = pow(a - b, 2)
        temp2 = sum(temp1)
        return math.sqrt(temp2)

    @staticmethod
    def similarity(a):
        return (1 - a / 140) * 100
