"""
    Problem Name    : N 으로 표
    Problem Status  : Solving
    Problem From    : Dynamic Programming
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/42895

"""

from copy import deepcopy


def solution(N, number):
    answer = -1

    expressions = {(int(str(N)*i), i) for i in range(1, 6)}

    for i in range(6):
        if [y for x, y in expressions if x == number]:
            answer = min([y for x, y in expressions if x == number])
            break

        tmp = deepcopy(expressions)

        for x, y in expressions:

            t = y + 1

            if t < 8:
                tmp = tmp.union(expressions.union({(x - N, t), (x + N, t), (x * N, t), (int(x / N), t)}))

                for x2, y2 in expressions:
                    if y + y2 > 8:
                        continue
                    tmp = tmp.union(expressions.union({(x + x2, y + y2)}))
                    if x - x2 > 0:
                        tmp = tmp.union(expressions.union({(x - x2, y + y2)}))

        expressions = tmp

    return answer


print(solution(5, 31168))

