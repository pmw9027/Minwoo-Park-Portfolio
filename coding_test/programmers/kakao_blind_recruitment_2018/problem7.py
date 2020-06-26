import re
from datetime import datetime, timedelta

"""

Problem name: [1차] 셔틀버스
Status: solved

"""


def solution(n, t, m, timetable):

    today = datetime.today()
    start = today.replace(hour=9, minute=0, second=0, microsecond=0)

    compiler = re.compile("(\d\d):(\d\d)")

    t = timedelta(minutes=t)

    timetable = [today.replace(hour=int(compiler.match(s).groups()[0]), minute=int(compiler.match(s).groups()[1]), second=0, microsecond=0) for s in timetable if int(compiler.match(s).groups()[0]) != 24]

    timetable.sort(reverse=True)

    cnt = 0
    for i in range(n):
        cnt = 0
        for j in range(m):
            if len(timetable):
                crew = timetable.pop()
                if crew > start:
                    timetable.append(crew)
                    break
                cnt += 1

        start += t

    if cnt < m:
        answer = (start - t).strftime("%H:%M")
    else:
        answer = (crew - timedelta(minutes=1)).strftime("%H:%M")
    return answer


print(solution(10, 60, 45, ["23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]))

print(solution(1, 1, 5, ["00:01", "00:01", "00:01", "00:01", "00:01"]))

print(solution(1, 1, 1, ["23:59"]))

print(solution(2, 10, 2, ["09:10", "09:09", "08:00"]))