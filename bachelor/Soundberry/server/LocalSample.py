import numpy as np


class LocalSample(object):

    def __init__(self):
        global names
        names = []
        self.name = []
        self.mfcc = []
        names = self.name

    def load_file(self):
        file = open("list", 'r')
        line = file.readline()
        while line:
            line = line[:len(line)-1]
            file2 = np.loadtxt("./sample/"+line)
            self.mfcc.append(file2)
            self.name.append(line)
            print(line, len(self.mfcc[len(self.mfcc)-1]))
            line = file.readline()
        file.close()
