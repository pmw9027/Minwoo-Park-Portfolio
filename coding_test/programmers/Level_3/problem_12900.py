"""
    Problem Name    : 2 x n 타일링
    Problem Status  : Solved
    Problem From    : 연습문제
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/12900

"""


def solution(n):

    answer = 0

    ans_n_minus_2 = 1
    ans_n_minus_1 = 2

    for i in range(3, n ):
        answer = (ans_n_minus_2 + ans_n_minus_1) % 1000000007
        ans_n_minus_2 = ans_n_minus_1
        ans_n_minus_1 = answer

    return (ans_n_minus_2 + ans_n_minus_1) % 1000000007


print(solution(4))

