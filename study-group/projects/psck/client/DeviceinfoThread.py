import platform
import socket
import uuid
import os
import datetime
import psutil
import time
from PyQt5.QtCore import QThread
from model.Device import DeviceInfo


class DeviceInfoThread(QThread):

    friend_device_info = []

    def __init__(self):
        QThread.__init__(self)
        DeviceInfoThread.friend_device_info.append(DeviceInfo(None, None))

        setattr(self.friend_device_info[0], 'd_boot_t', self.get_my_booting_time())
        setattr(self.friend_device_info[0], 'd_name', socket.gethostname())

    def __del__(self):
        self.wait()

    def run(self):
        while True:
            setattr(self.friend_device_info[0], 'd_ip', self.get_my_ip_address())
            setattr(self.friend_device_info[0], 'd_mac', self.get_my_mac_address())
            setattr(self.friend_device_info[0], 'd_cpu_per', psutil.cpu_percent())
            setattr(self.friend_device_info[0], 'd_mem_total', psutil.virtual_memory().total)
            setattr(self.friend_device_info[0], 'd_mem_avail', psutil.virtual_memory().available)
            setattr(self.friend_device_info[0], 'd_mem_per', psutil.virtual_memory().percent)
            time.sleep(1)

    @staticmethod
    def get_my_mac_address():  # Mac Address function
        if platform.system() == "Darwin":
            addrs = psutil.net_if_addrs().get('en0')
            for i in addrs:
                if i.family == 18:  # Mac 주소
                    return i.address

        elif platform.system() == "Windows":
            # mac_num = hex(uuid.getnode()).replace('0x', '').upper()
            # mac = '-'.join(mac_num[i: i + 2] for i in range(0, 11, 2))
            # return mac
            addrs = psutil.net_if_addrs().get('Wi-Fi')
            for i in addrs:
                if i.family == -1:
                    return i.address

    @staticmethod
    def get_my_ip_address():  # IP Address function
        if platform.system() == "Darwin":
            addrs = psutil.net_if_addrs().get('en0')
            for i in addrs:
                if i.family == 2:  # IP 주소
                    return i.address
        elif platform.system() == "Windows":
            # hostname = socket.gethostname()
            # return socket.gethostbyname(hostname)
            addrs = psutil.net_if_addrs().get('Wi-Fi')
            for i in addrs:
                if i.family == 2:
                    return i.address

    @staticmethod
    def get_my_booting_time():
        return datetime.datetime.fromtimestamp(psutil.boot_time())
