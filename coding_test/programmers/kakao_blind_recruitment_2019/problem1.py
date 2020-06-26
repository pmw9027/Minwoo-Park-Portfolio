def solution(record):
    answer = []

    nickname = {}
    logs = []

    for str in record:
        strs = str.split(" ")

        if strs[0] == 'Enter':
            nickname[strs[1]] = strs[2]
            logs.append((strs[1], 0))
        elif strs[0] == 'Leave':
            logs.append((strs[1], 1))
        elif strs[0] == 'Change':
            nickname[strs[1]] = strs[2]

    answer = [f"{nickname[log[0]]}님이 들어왔습니다." if log[1] == 0 else f"{nickname[log[0]]}님이 나갔습니다." for log in logs]

    return answer


print(solution(["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]))