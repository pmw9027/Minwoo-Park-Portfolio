import re

"""

Problem name: [1차] 프렌즈4블록
Status: not solved

"""


def solution(m, n, board):

    answer = 0
    tmp = [1]
    while len(tmp) > 0:
        tmp = []

        for i in range(m):
            board[i] = list(board[i])

        for i in board:
            print(i)
        print()

        for i in range(m-1):
            for j in range(n-1):
                if board[i][j] == '0':
                    continue
                if board[i][j] == board[i][j+1] == board[i+1][j] == board[i+1][j+1]:
                    tmp.append((i, j))
        for i in tmp:
            for j in range(2):
                for k in range(2):
                    if board[i[0] + j][i[1] + k] != '0':
                        board[i[0] + j][i[1] + k] = '0'
                        answer += 1

        for j in range(n):
            column = [row[j] for row in board]
            cnt = column.count('0')
            for i in range(cnt):
                column.remove('0')
            for i in range(cnt):
                column.append('0')

            column.reverse()
            for i in reversed(range(m)):
                board[i][j] = column[i]

    return answer


print(solution(6, 6, ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]))