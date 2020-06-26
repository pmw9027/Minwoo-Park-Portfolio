import wave
from socket import *
import sys
from time import ctime

import numpy as np
import pyaudio
import os
import time
from multiprocessing import Queue
from python_speech_features import mfcc, numpy
import scipy.io.wavfile as wav

from util import load_config

ID = 'AAAA'

config = load_config('config')

CHUNK = int(config['record']['chunk'])
FORMAT = pyaudio.paInt16
CHANNELS = int(config['record']['channels'])
RATE = int(config['record']['rate'])
HOST = config['server']['host']
PORT_UDP_SERVER = config['server']['port_udp_server']
PORT_TCP_SERVER = config['server']['port_tcp_server']
ADDR_TCP_SERVER = (HOST, PORT_TCP_SERVER)
ADDR_UDP_SERVER = (HOST, PORT_UDP_SERVER)

print '=============================================='
print '[INFO]SoundBerry Start'
print '=============================================='
q = Queue()

newpid = os.fork()
# Audio Record Process Code Begin
if newpid == 0:
    p = pyaudio.PyAudio()
    try:
        stream = p.open(format=FORMAT, channels=CHANNELS, rate=RATE, input=True, frames_per_buffer=CHUNK)
    except Exception as e:
        print(e)
        os.system('killall -9 python')
        time.sleep(1)
        stream = p.open(format=FORMAT, channels=CHANNELS, rate=RATE, input=True, frames_per_buffer=CHUNK)

    print('[INFO][RecordProcess] Start')

    print('[INFO][RecordProcess] Audio Driver Load')

    while True:
        data = stream.read(CHUNK)
        q.put(data)
    stream.close()
    p.terminate()

# Audio Record Process Code Begin

else:
    newpid = os.fork()

# Server UDP socket Proccess Code Begin
    if newpid is 0:
        print('[INFO][SocketProcess] UDP Client start')
        clientSocket = socket(AF_INET, SOCK_DGRAM)
        clientSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
        event = {}
        event['id'] = ID
        while True:
            event['time'] = ctime()
            clientSocket.sendto(str(event), ADDR_UDP_SERVER)
            time.sleep(3)

# Server UDP socket Proccess Code End

# Server TCP socket Proccess Code Begin
    else:
        p = pyaudio.PyAudio()
        print('[INFO][SocketProcess] TCP Client Start')
        clientSocket = socket(AF_INET, SOCK_STREAM)
        while True:
            try:
                print('[INFO][SocketProcess] Try Connecting Server...')
                clientSocket.connect(ADDR_TCP_SERVER)
                print('[INFO][SocketProcess] Success to Connect Server')
                while True:
                    if not q.empty():
                        try:
                            frames = [q.get()]
                            wf = wave.open("testfile.wav", 'wb')
                            wf.setnchannels(CHANNELS)
                            wf.setsampwidth(p.get_sample_size(FORMAT))
                            wf.setframerate(RATE)
                            wf.writeframes(b''.join(frames))
                            wf.close()
                            (rate, sig) = wav.read("testfile.wav")
                            mfcc_feat = mfcc(sig, rate)

                            clientSocket.send(mfcc_feat.astype(np.float16).tostring())
                        except Exception as e:
                            print('[ERROR][SocketProcess]%s' % e)
                            clientSocket.close()
                            break

            except KeyboardInterrupt:
                os.system('killall -9 python2.7')
                clientSocket.close()
                sys.exit()
            except Exception as e:
                print('[INFO][SocketProcess]Fail to Connect Server')
                clientSocket.close()
                time.sleep(30)
                print e
# Server TCP socket Proccess Code End
