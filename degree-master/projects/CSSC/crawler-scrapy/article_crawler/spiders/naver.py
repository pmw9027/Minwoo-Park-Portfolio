# -*- coding: utf-8 -*-
import scrapy


class NaverSpider(scrapy.Spider):
    name = 'naver'
    allowed_domains = ['naver.com']
    start_urls = ['http://naver.com/']

    def parse(self, response):
        pass
