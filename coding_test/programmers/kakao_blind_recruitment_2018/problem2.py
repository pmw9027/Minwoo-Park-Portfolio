from datetime import datetime, timedelta

"""

Problem name: 비밀지도
Status: solved

"""


def solution(n, arr1, arr2):
    answer = []

    for i in range(n):
        tmp = arr1[i] | arr2[i]
        tmp2 = ''
        for j in range(1, n+1):
            tmp2 += '#' if tmp / (2 ** (n - j)) >= 1 else ' '
            if tmp / (2 ** (n - j)) >= 1:
                tmp -= (2 ** (n - j))

        answer.append(tmp2)

    return answer

print(solution(5, [9, 20, 28, 18, 11], [30, 1, 21, 17, 28]))