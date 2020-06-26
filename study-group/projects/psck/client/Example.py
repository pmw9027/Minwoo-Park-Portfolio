
import sys

import psutil
from PyQt5 import QtWidgets

from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import QUrl
from PyQt5.QtWidgets import QApplication

if __name__ == '__main__':


    print(psutil.net_if_stats())