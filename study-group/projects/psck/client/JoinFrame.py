# -*- coding: utf-8 -*-

# form implementation generated from reading ui file 'form.ui'
#
# Created by: PyQt5 UI code generator 5.7.1
#
# WARNING! All changes made in this file will be lost!
import sys

import pymongo
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QApplication

from FailDialog import FailDialog
from MongoDao import JoinDao
from Myhttp import Communication
from model.Account import Account


class JoinFrame(object):

    qwidget = None
    verticalLayoutWidget = None
    verticalLayout = None
    horizontalLayout_3 = None
    verticalLayout_4 = None
    label = None
    label_2 = None
    verticalLayout_5 = None
    lineEdit = None
    lineEdit_2 = None
    horizontalLayout = None
    pushButton = None
    pushButton_2 = None

    @staticmethod
    def init():
        JoinFrame.qwidget = QtWidgets.QWidget()
        JoinFrame.verticalLayoutWidget = QtWidgets.QWidget(JoinFrame.qwidget)
        JoinFrame.verticalLayout = QtWidgets.QVBoxLayout(JoinFrame.verticalLayoutWidget)
        JoinFrame.horizontalLayout_3 = QtWidgets.QHBoxLayout()
        JoinFrame.verticalLayout_4 = QtWidgets.QVBoxLayout()
        JoinFrame.label = QtWidgets.QLabel(JoinFrame.verticalLayoutWidget)
        JoinFrame.label_2 = QtWidgets.QLabel(JoinFrame.verticalLayoutWidget)
        JoinFrame.verticalLayout_5 = QtWidgets.QVBoxLayout()
        JoinFrame.lineEdit = QtWidgets.QLineEdit(JoinFrame.verticalLayoutWidget)
        JoinFrame.lineEdit_2 = QtWidgets.QLineEdit(JoinFrame.verticalLayoutWidget)
        JoinFrame.horizontalLayout = QtWidgets.QHBoxLayout()
        JoinFrame.pushButton = QtWidgets.QPushButton(JoinFrame.verticalLayoutWidget)
        JoinFrame.pushButton_2 = QtWidgets.QPushButton(JoinFrame.verticalLayoutWidget)
        JoinFrame.setup_ui()

    @staticmethod
    def setup_ui():
        JoinFrame.qwidget.setObjectName("form")
        JoinFrame.qwidget.resize(400, 300)

        JoinFrame.verticalLayoutWidget.setGeometry(QtCore.QRect(50, 60, 323, 151))
        JoinFrame.verticalLayoutWidget.setObjectName("verticalLayoutWidget")
        JoinFrame.verticalLayout.setContentsMargins(0, 0, 0, 0)
        JoinFrame.verticalLayout.setObjectName("verticalLayout")
        JoinFrame.horizontalLayout_3.setObjectName("horizontalLayout_3")
        JoinFrame.verticalLayout_4.setObjectName("verticalLayout_4")
        JoinFrame.label.setObjectName("label")
        JoinFrame.verticalLayout_4.addWidget(JoinFrame.label)
        JoinFrame.label_2.setObjectName("label_2")
        JoinFrame.verticalLayout_4.addWidget(JoinFrame.label_2)
        JoinFrame.horizontalLayout_3.addLayout(JoinFrame.verticalLayout_4)
        JoinFrame.verticalLayout_5.setObjectName("verticalLayout_5")
        JoinFrame.lineEdit.setObjectName("lineEdit")
        JoinFrame.verticalLayout_5.addWidget(JoinFrame.lineEdit)
        JoinFrame.lineEdit_2.setObjectName("lineEdit_2")
        JoinFrame.verticalLayout_5.addWidget(JoinFrame.lineEdit_2)
        JoinFrame.horizontalLayout_3.addLayout(JoinFrame.verticalLayout_5)
        JoinFrame.verticalLayout.addLayout(JoinFrame.horizontalLayout_3)
        JoinFrame.horizontalLayout.setObjectName("horizontalLayout")
        JoinFrame.pushButton.setObjectName("pushButton")
        JoinFrame.horizontalLayout.addWidget(JoinFrame.pushButton)
        JoinFrame.pushButton_2.setObjectName("pushButton_2")
        JoinFrame.horizontalLayout.addWidget(JoinFrame.pushButton_2)
        JoinFrame.verticalLayout.addLayout(JoinFrame.horizontalLayout)
        JoinFrame.verticalLayoutWidget.raise_()

        JoinFrame.retranslateUi()

        JoinFrame.pushButton.clicked.connect(JoinFrame.ok_btn_click)
        JoinFrame.pushButton_2.clicked.connect(JoinFrame.cancel_btn_click)

        QtCore.QMetaObject.connectSlotsByName(JoinFrame.qwidget)

    @staticmethod
    def retranslateUi():
        _translate = QtCore.QCoreApplication.translate
        JoinFrame.qwidget.setWindowTitle(_translate("form", "Join"))
        JoinFrame.label.setText(_translate("form", "ID"))
        JoinFrame.label_2.setText(_translate("form", "PW"))
        JoinFrame.pushButton.setText(_translate("form", "Join"))
        JoinFrame.pushButton_2.setText(_translate("form", "Cancel"))

    @staticmethod
    def widget_show():
        JoinFrame.qwidget.show()

    @staticmethod
    def widget_hide():
        JoinFrame.qwidget.hide()

    @staticmethod
    def ok_btn_click():
        account = Account(JoinFrame.lineEdit.text(),JoinFrame.lineEdit_2.text())

        result = Communication.join(account)
        if result['success']:
            JoinFrame.widget_hide()
        else:
            FailDialog.retranslateUi('Fail', result['message'])
            FailDialog.widget_show()

    @staticmethod
    def cancel_btn_click():
        JoinFrame.widget_hide()