import os
import sys

import mmap
import numpy as np
import scipy.io.wavfile as wav
import struct

from python_speech_features import mfcc
import numpy
import wave
import pyaudio
import sys
import array
from multiprocessing import Queue

import numpy as np
import scipy.io.wavfile as wav
from python_speech_features import mfcc
CHUNK = 1024
FORMAT = pyaudio.paInt16
CHANNELS = 1
RATE = 44100
RECORD_SECONDS = 3

WAVE_OUTPUT_FILENAME = sys.argv[1]
p = pyaudio.PyAudio()

_big_endian = False

def _byteswap3(data):
    ba = bytearray(data)
    ba[::3] = data[2::3]
    ba[2::3] = data[::3]
    return bytes(ba)

_array_fmts = None, 'b', 'h', None, 'i'

stream = p.open(format=FORMAT,
            channels=CHANNELS,
            rate=RATE,
            input=True,
            frames_per_buffer=CHUNK)

print "* Recording audio..."

frames = []
_sampwidth = p.get_sample_size(FORMAT)
"""
for i in range(0, int(RATE / CHUNK * RECORD_SECONDS)):
    data = stream.read(CHUNK)
    frames.append(data)
    print data
"""
q = Queue()
newpid = os.fork()
if newpid == 0:
    while True:
        data = stream.read(CHUNK)
        q.put(data)
else:
    while True:
        if q.qsize():
            frames.append(q.get())
            nframes = len(frames) // (_sampwidth * CHANNELS)
            if p.get_sample_size(FORMAT) in (2, 4) and sys.byteorder == 'big':

                a = array.array(_array_fmts[_sampwidth])
                a.fromstring(frames)
                frames = a
                assert frames.itemsize == _sampwidth
                frames.byteswap()
            else:
                if _sampwidth == 3 and sys.byteorder == 'big':
                    frames = _byteswap3(frames)

            print(numpy.fromstring(frames.pop(), dtype='float64'))
            data = np.array(frames)
            # print(type())
            print(data)
            # mfcc_feat = mfcc(data, RATE)
            # print(mfcc_feat)
            frames = []


print "* done\n"

(rate, sig) = wav.read(sys.argv[1])
print(rate, type(sig))