from datetime import datetime, timedelta
import re

"""

Problem name: [1차] 캐시
Status: solved

"""


def solution(cacheSize, cities):
    answer = 0

    cache = {}

    if cacheSize == 0:
        return len(cities) * 5

    for i in range(len(cities)):

        if cities[i].lower() in cache.keys():
            answer += 1
            cache[cities[i].lower()] = i

        else:
            if len(cache) < cacheSize:
                cache[cities[i].lower()] = i
            else:
                cache.pop(min(cache.keys(), key=(lambda k: cache[k])))
                cache[cities[i].lower()] = i

            answer += 5
    return answer

print(solution(2, ["Jeju", "Pangyo", "NewYork", "newyork"]))
print(solution(2, ["Jeju", "Pangyo", "newyork", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]))
print(solution(5, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]))