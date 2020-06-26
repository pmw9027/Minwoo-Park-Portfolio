"""
    Problem Name    : 타일 장식
    Problem Status  : Solved
    Problem From    : Dynamic Programming
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/43104

"""


def func(n):
    if n == 1:
        return 1
    elif n == 2:
        return 2
    else:
        return func(n - 1) + func(n - 2)


def solution(n):

    answer_1 = 1
    answer_2 = 2
    answer = 2

    for i in range(3, n):
        answer = answer_1 + answer_2
        answer_1 = answer_2
        answer_2 = answer

    return answer * 2 + (answer_1 + answer_2) * 2


    # return func(n) * 2 + func(n - 1 ) * 2


print(solution(3))

