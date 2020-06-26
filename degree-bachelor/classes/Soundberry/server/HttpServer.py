from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import json
from SocketServer import ThreadingMixIn
import threading
import re
import cgi


class HTTPRequestHandler(BaseHTTPRequestHandler):

    def do_POST(self):

        if None != re.search('/*', self.path):
            ctype, pdict = cgi.parse_header(self.headers.getheader('content-type'))
            if ctype == 'application/json':
                length = int(self.headers.getheader('content-length'))
                data = cgi.parse_qs(self.rfile.read(length), keep_blank_values=1)
                recordID = self.path.split('/')[-1]
                self.send_header('Content-Type', 'application/json')
                self.end_headers()

                print("record %s is added successfully" % recordID)
            else:
                self.send_response(200)
                self.send_header('Content-Type', 'application/json')
                self.end_headers()
                file = open('AAAA', 'r')
                dict = {}
                dict = file.read()
                self.wfile.write(dict[:-1])
                file.close()

        else:
            self.send_response(403)
            self.send_header('Content-Type', 'application/json')
            self.end_headers(LocalData.data)
            return

    def do_GET(self):

        event = {}

        if None != re.search('/*', self.path):

            recordID = self.path.split('/')[-1]

            if recordID in LocalData.iot:
                self.send_response(200)
                self.send_header('Content-Type', 'application/json')
                self.end_headers()
                self.wfile.write(LocalData.iot[recordID])
                print("ho")
            else:
                self.send_response(400, 'Bad Request: record does not exist')
                self.send_header('Content-Type', 'application/json')
                self.end_headers()
        else:
            self.send_response(403)
            self.send_header('Content-Type', 'application/json')
            self.end_headers()
        return


class ThreadedHTTPServer(ThreadingMixIn, HTTPServer):
    allow_reuse_address = True

    def shutdown(self):
        self.socket.close()
        HTTPServer.shutdown(self)


class SimpleHttpServer():

    def __init__(self, ip, port):
        self.server = ThreadedHTTPServer((ip, port), HTTPRequestHandler)

    def start(self):
        self.server_thread = threading.Thread(target=self.server.serve_forever)
        self.server_thread.daemon = True
        self.server_thread.start()

    def waitForThread(self):
        self.server_thread.join()

    def addRecord(self, recordID, jsonEncodedRecord):
        LocalData.records[recordID] = jsonEncodedRecord

    def stop(self):
        self.server.shutdown()
        self.waitForThread()


class LocalData(object):
    iot = {'AAAA':""}
    data = {'lastconnect': '', 'token': 'cup-GkSjiMQ:APA91bEZrUY9F80SzFqhiYv0AvU_zbGYsQSo8-ogY6FSa1hXQFdRVoAfy0N0dtyWT50jxh3FjC0w_KhJsKAAQsyV9LnX820OuelTJJTlZa8F5L522tG3Ekc1nZGbfxEQpD8QONDbu5kL', 'user': '' }
    iot['AAAA'] = data
    sample = []
    temp = []