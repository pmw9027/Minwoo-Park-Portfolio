import subprocess
import smtplib
import string
import os
import time
import sys
import json
import urllib
import urllib2
from bs4 import BeautifulSoup
from StringIO import StringIO

import pycurl
def sendAutostartMail():

    user = 'alsdnv112@gmail.com'
    pw = 'special120'
    to = ['tnwndvhrrurr@paran.com', 'pmw9027@outlook.kr']
    subject = "[HS] Computer new notice"
    #ip = subprocess.check_output("hostname -I", shell=True)
    text = 'I LOVE JIWON'
    fr = user
    host = 'smtp.gmail.com:587'

    sleep_time = 60
    new_user = False
    new = False
    current = 914

    new_pid = os.fork()
    if new_pid < 0:
        print 'error'
        sys.exit(0)
    elif new_pid == 0:
        while True:
            if new_user:
                to.append('alsdnv112@naver.com')

    while True:
        opener = urllib2.build_opener(urllib2.HTTPCookieProcessor())
        response = opener.open('http://cis.hs.ac.kr/?mid=news_notice1')
        soup = BeautifulSoup(response.read())
        table = soup.find(class_="boardList")
        trs = table.tbody.find_all("tr", ["bg1", "bg2"])
        for tr in trs:
            tds = tr.find_all('td')
            num = tds[0].string
            title = tds[1].find('a').string
            if current < num:
                current = num
                text = title.encode('utf-8').strip()
                print current, text
                new = True
        if new is True:
            new = False
            server = smtplib.SMTP(host)
            server.starttls()
            server.login(user, pw)
            for i in to:
                body = string.join((
                    'From: %s' % fr,
                    'To: %s' % i,
                    'Subject: %s' % subject,
                    '\r\n',
                    text,
                ), '\r\n')
                server.sendmail(fr, i, body)

            server.quit()

            conn = pycurl.Curl()
            item = 'wtzTo6YQdPvJj9agEEJ9NdzAXKQo4hPGhmM0eawQQjIAAAFWSirwDw'
            http_header = "Authorization: Bearer "
            message_templete = 1121
            conn.setopt(pycurl.POST,True)
            conn.setopt(pycurl.URL, "https://kapi.kakao.com/v1/api/talk/memo/send")
            conn.setopt(pycurl.HTTPHEADER,["Authorization: Bearer wtzTo6YQdPvJj9agEEJ9NdzAXKQo4hPGhmM0eawQQjIAAAFWSirwDw"])
            #conn.setopt(pycurl.)
            #conn.setopt(pycurl.HTTPPOST,["template_id: 1121"])
            conn.setopt(pycurl.POSTFIELDS,json.dumps({"template_id": "1121"}))
            conn.perform()
        time.sleep(sleep_time)
if __name__ == "__main__":
    sendAutostartMail()