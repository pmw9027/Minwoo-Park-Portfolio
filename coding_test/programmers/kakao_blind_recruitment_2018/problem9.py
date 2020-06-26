"""

Problem name: [3차] 압축
Status: solved

"""


def solution(msg):
    answer = []

    index = [chr(ord('A') + i) for i in range(26)]

    i = 0
    while i < len(msg):
        for j in reversed(range(len(max(index, key=lambda k: len(k))))):

            keyword = msg[i:i + j + 1]
            if keyword in index:
                answer.append(index.index(keyword) + 1)
                index.append(msg[i:i + j + 2])
                i += len(keyword)
                break

    print(index)
    return answer


print(solution("MESSAGE"))
print(solution("KAKAO"))

