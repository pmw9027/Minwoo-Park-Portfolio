import re
"""

Problem name: [3차] 파일명 정렬
Status: solved

"""


def solution(files):

    compiler = re.compile("(\D+)(\d*)(.*)")
    answer = []

    i = 0
    for file in files:
        groups = compiler.match(file).groups()
        groups += (i,)
        i += 1
        answer.append(groups)

    answer = sorted(answer, key=lambda k: k[3])
    answer = sorted(answer, key=lambda k: int(k[1]))
    answer = sorted(answer, key=lambda k: k[0].lower())

    answer = [s[0]+s[1]+s[2] for s in answer]

    return answer

print(solution(["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]))

