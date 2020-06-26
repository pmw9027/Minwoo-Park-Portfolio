import re

"""

Problem name: [1차] 뉴스 클러스터링
Status: solved

"""


def solution(str1, str2):

    pattern = re.compile("[a-z][a-z]")

    str1 = str1.lower()
    str2 = str2.lower()
    str1 = [str1[i:i+2] for i in range(len(str1)) if pattern.match(str1[i:i+2])]
    str2 = [str2[i:i+2] for i in range(len(str2)) if pattern.match(str2[i:i+2])]

    union = sum([max(str2.count(s), str1.count(s)) for s in set(str1) | set(str2)])
    intersection = sum([min(str2.count(s), str1.count(s)) for s in set(str1)])


    if union == 0:
        answer = 65536
    else:
        answer = int((intersection / union) * 65536)

    return answer

print(solution("FRANCE", "FRENCH"))