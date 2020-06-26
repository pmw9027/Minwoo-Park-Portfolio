"""
    Problem Name    : 정수 삼각형
    Problem Status  : Solving
    Problem From    : Dynamic Programming
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/43105

"""


def solution(triangle):

    for level, arr in enumerate(triangle):
        if level == 0:
            continue

        for key, elem in enumerate(arr):
            triangle[level][key] += max(triangle[level - 1][key - 1 if key > 0 else 0:key + 1])

    return max(triangle[-1])


print(solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]))

