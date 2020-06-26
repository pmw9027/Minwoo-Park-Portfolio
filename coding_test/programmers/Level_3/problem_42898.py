"""
    Problem Name    : 등굣길
    Problem Status  : Solving
    Problem From    : Dynamic Programming
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/42898

"""

moves = [[-1, 0], [1, 0], [0, 1], [0, -1]]

from copy import copy, deepcopy

def solution(m, n, puddles):

    maps = [[0] * m for _ in range(n)]
    maps[0][0] = 1
    maps[n - 1][m - 1] = 'G'
    for x, y in puddles:
        maps[y-1][x-1] = 'P'

    while True:
        map_t = deepcopy(maps)
        for x, x_maps in enumerate(maps):
            for y, value in enumerate(x_maps):
                if maps[x][y] == 0:
                    arr = [maps[x + i][y + j] for i, j in moves if 0 <= x + i < len(maps) and 0 <= y + j < len(maps[0])]
                    arr = [e for e in arr if e not in ['P', 'G'] and e > 0]
                    if arr:
                        map_t[x][y] = max(arr) + 1 if max(arr) > 0 else 0
                        # print(arr.count(max(arr)))
        maps = deepcopy(map_t)
        if map_t[-2][-1] != 0 or map_t[-1][-2] != 0:
            break

    answer = 0
    return answer / 1000000007


print(solution(4, 3, [[2, 2]]))

