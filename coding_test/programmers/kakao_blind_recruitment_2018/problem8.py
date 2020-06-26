import re
from datetime import datetime, timedelta

"""

Problem name: [3차] n진수 게임
Status: solved

"""

def solution(n, t, m, p):

    answer = ''
    num = 0
    stage = 0
    while len(answer) < t:
        num_base = base(num, n)
        for i in num_base:
            if (stage % m) + 1 == p:
                answer += i
            if len(answer) == t:
                break
            stage += 1
        num += 1


    return answer


def base (n, k):
    if n == 0:
        return '0'
    nums = []
    while n:
        n, r = divmod(n, k)

        if r > 9:
            nums.append(chr(ord('A') + r - 10))
        else:
            nums.append(str(r))


    return ''.join(reversed(nums))

print(solution(2, 4, 2, 1))

print(solution(16, 16, 2, 1))

