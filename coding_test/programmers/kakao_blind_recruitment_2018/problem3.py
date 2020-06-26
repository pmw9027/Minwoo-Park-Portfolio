from datetime import datetime, timedelta
import re

"""

Problem name: [1차] 다트 게임
Status: solved

"""


def solution(dartResult):
    scores = []

    j = 0
    for i in re.findall('\d+[SDT][*#]?', dartResult):

        shot = re.match("(\d+)([SDT])([*#]?)", i).groups()

        score = int(shot[0])
        score **= 2 if shot[1] == 'D' else 3 if shot[1] == 'T' else 1

        if shot[2] == '#':
            score *= -1
        elif shot[2] == '*':
            score *= 2
            if j != 0:
                scores[j-1] *= 2
        j += 1
        scores.append(score)
    answer = sum(scores)
    return answer

print(solution('1D2S#10S'))