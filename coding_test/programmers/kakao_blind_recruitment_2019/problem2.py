def solution(N, stages):

    steps = [[i+1, 0, 0] for i in range(N)]

    for stage in stages:
        for i in range(stage - 1):
            steps[i][1] += 1

        if stage != N + 1:
            steps[stage - 1][2] += 1

    answer = [step[2] / (step[1]+step[2]) if step[1] != 0 else (0 if step[2] == 0 else 1) for step in steps]
    answer = sorted(range(len(answer)), key=lambda k: answer[k], reverse=True)

    answer = [a+1 for a in answer]
    return answer


print(solution(5, [2, 1, 2, 6, 2, 4, 3, 3]))