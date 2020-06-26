"""
    Problem Name    : 기능개발
    Problem Status  : Solved
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/42586?language=python3

"""

import math


def solution(progresses, speeds):
    temp = []
    answer = []

    for key, progress in enumerate(progresses):
        temp.append(math.ceil((100 - progress) / speeds[key]))

    tmp1 = 0

    for tmp in temp:
        if tmp <= tmp1:
            answer[-1] += 1
        else:
            tmp1 = tmp
            answer.append(1)

    return answer


print(solution([93, 30, 55], [1, 30, 5]))

