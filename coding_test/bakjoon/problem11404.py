import sys
import collections


city_cnt = int(sys.stdin.readline())
bus_cnt = int(sys.stdin.readline())
buses = collections.deque()
for i in range(city_cnt):
    buses.append([None] * city_cnt)

for i in range(bus_cnt):
    bus = sys.stdin.readline().split(' ')
    buses[int(bus[0])-1][int(bus[1])-1] = int(bus[2])


tmp = [-1] * city_cnt
for i in range(city_cnt):
    tmp[i] = 0
    # min()