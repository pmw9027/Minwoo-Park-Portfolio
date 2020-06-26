from datetime import datetime, timedelta

"""
Problem name: [1차] 추석트래픽
status: not solved
"""

def solution(lines):
    answer = 0

    logs = []

    for line in lines:
        line_split = line.split(" ")
        end = datetime.strptime(" ".join(line_split[:2]), "%Y-%m-%d %H:%M:%S.%f")
        start = end - timedelta(seconds=float(line_split[2][:-1])) - timedelta(seconds=0.001)
        # start = end - timedelta(seconds=float(line_split[2][:-1]))

        logs.append((start, end))

    second_1 = timedelta(seconds=1)

    temp = 0
    for log in logs:
        tmp = 0
        print(log)
        for a_log in [a for a in logs if log != a]:
            print(a_log)
            if log[1] <= a_log[0] <= log[1] + second_1 or log[1] <= a_log[1] <= log[1] + second_1:
                tmp += 1
            elif log[1] >= a_log[0] and a_log[1] >= log[1] + second_1:
                tmp += 1
        if tmp > temp:
            temp = tmp
        print()
    answer = temp + 1
    return answer



# solution([
#          '2016-09-15 20:59:57.421 0.351s',
#          '2016-09-15 20:59:58.233 1.181s',
#          '2016-09-15 20:59:58.299 0.8s',
#          '2016-09-15 20:59:58.688 1.041s',
#          '2016-09-15 20:59:59.591 1.412s',
#          '2016-09-15 21:00:00.464 1.466s',
#          '2016-09-15 21:00:00.741 1.581s',
#          '2016-09-15 21:00:00.748 2.31s',
#          '2016-09-15 21:00:00.966 0.381s',
#          '2016-09-15 21:00:02.066 2.62s'
#          ])

solution(["2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"])