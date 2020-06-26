from select import *
from socket import *
from time import ctime
import sys
import os
import numpy as np
import time
import setting
import signal

import Fcm
import HttpServer
from multiprocessing import Queue
import LocalSample
from CompareThread import CompareThread
from FCMThread import FCMThread

HOST = ''
GCM_HOST = 'https://fcm.googleapis.com/fcm/send'
GCM_SERVER_KEY = 'AIzaSyA3nOPC1X4__JDg4V_kXOxuboEQgKE8OmQ'


PORT_HTTP = 8000
PORT_UDP_SERVER = 8010
PORT_TCP_SERVER = 8020

ADDR_TCP_SERVER = (HOST, PORT_TCP_SERVER)
ADDR_UDP_SERVER = (HOST, PORT_UDP_SERVER)
ADDR_HTTP = (HOST, PORT_HTTP)

BUFSIZE = 100000


class LocalData(object):
    iot = {'AAAA':""}
    data = {'lastconnect': '', 'token': 'cup-GkSjiMQ:APA91bEZrUY9F80SzFqhiYv0AvU_zbGYsQSo8-ogY6FSa1hXQFdRVoAfy0N0dtyWT50jxh3FjC0w_KhJsKAAQsyV9LnX820OuelTJJTlZa8F5L522tG3Ekc1nZGbfxEQpD8QONDbu5kL', 'user': '' }
    iot['AAAA'] = data
    sample = []
    temp = []


ls = LocalSample.LocalSample()
ls.load_file()
print('[%s][INFO][IOTProcess] LoadSample Success ' % ctime())

if __name__ == '__main__':
    print('==============================================')
    print('[%s][INFO][Server] Start ' % ctime())
    print('==============================================')
    newpid = os.fork()
    if newpid == 0:
        newpid = os.fork()
        if newpid == 0:
            print('[%s][INFO][Process] Start ' % ctime())
            while True:
                continue
# Mobile Http Server Code Begin
        else:
            print('[%s][INFO][MobileProcess] Start ' % ctime())
            server = HttpServer.SimpleHttpServer('', PORT_HTTP)
            server.start()
            print('[%s][INFO][MobileProcess]HTTP Server Listen %s' % (ctime(), str(PORT_HTTP)))
            server.waitForThread()
# Mobile Http Server Code End
    else:
        newpid = os.fork()
# IOT Process Code Start
    # IOT UDP Socket Process Code Start
        if newpid == 0:
            print('[%s][INFO][IOTUDPSocketProcess] Start ' % ctime())
            serverSocket = socket(AF_INET, SOCK_DGRAM)
            serverSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
            serverSocket.bind(ADDR_UDP_SERVER)
            print('[%s][INFO][IOTUDPSocketProcess] UDP Server Listen %s' % (ctime(), str(PORT_UDP_SERVER)))
            while True:
                data, addr = serverSocket.recvfrom(1024)
                dict = eval(data)

                LocalData.iot[dict['id']]['lastconnect'] = dict['time']
                file = open(dict['id'], 'w')
                file.write(str(LocalData.iot[dict['id']]))
                file.close()

    # IOT UDP Socket Process Code End

    # IOT TCP Socket Process Code Start
        else:
            q = Queue()
            newpid = os.fork()

            if newpid == 0:
                print('[%s][INFO][IOTTCPSocketProcess] Start ' % ctime())
                serverSocket = socket(AF_INET, SOCK_STREAM)
                serverSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
                serverSocket.bind(ADDR_TCP_SERVER)
                serverSocket.listen(10)
                print('[%s][INFO][IOTTCPSocketProcess] TCP Server Listen %s' % (ctime(), str(PORT_TCP_SERVER)))

                connection_list = [serverSocket]
                while connection_list:
                    try:
                        read_socket, write_socket, error_socket = select(connection_list, [], [], 10)
                        for sock in read_socket:
                            if sock == serverSocket:
                                clientSocket, addr_info = serverSocket.accept()
                                connection_list.append(clientSocket)
                                print('[Accept][%s][%s]' % (ctime(), addr_info[0]))

                            else:
                                data = ''
                                while(sys.getsizeof(data) < 2871):
                                    data += sock.recv(BUFSIZE)
                                data = np.fromstring(data, dtype='float16')
                                data = np.reshape(data, (len(data)/13, 13))
                                q.put(data)
                    except KeyboardInterrupt:
                        serverSocket.close()
                        os.system('killall -9 python2.7')
                        sys.exit()

    # IOT TCP Socket Process Code End

    # Analysis and FCM code Start
            else:
                setting.init()
                FCMThread().start()
                print('[%s][INFO][IOTProcess] Start ' % ctime())
                li = []
                while True:
                    try:
                        if not q.empty():
                            for i in q.get():
                                # print(i)
                                for (queue, thread) in li:
                                    if thread.status() is False:
                                        queue.put(i)
                                    else:
                                        thread.my_exit()
                                        queue.close()
                                        li.remove((queue, thread))

                                for j in range(len(ls.mfcc)):
                                    cos_com = CompareThread.cos_compare(ls.mfcc[j][0], i)
                                    sim = CompareThread.cos_similarity(cos_com)
                                    if sim > 91:
                                        print(sim, ls.name[j])
                                        StringQueue = Queue()
                                        cat = CompareThread(ls.name[j], StringQueue, ls.mfcc[j], cos_com)
                                        cat.start()
                                        t = (StringQueue, cat)
                                        li.append(t)
                    except Exception as e:
                        print 'hi'
                        print e
    # Analysis and FCM code Start
# IOT Process Code End
