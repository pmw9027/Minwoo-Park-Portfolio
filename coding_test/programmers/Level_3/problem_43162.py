"""
    Problem Name    : Network
    Problem Status  : Solving
    Problem From    : DFS / BFS
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/43162

"""


def solution(n, computers):

    networks = [{ind for ind, elem in enumerate(computer) if elem == 1} for key, computer in enumerate(computers)]
    for i, network in enumerate(networks):
        arr = []
        for ind, j in enumerate(networks[i+1:]):
            if network.intersection(j):
                network.union(j)
                arr.append(ind)

    i = 0
    while i < len(networks):
        j = i + 1
        while j < len(networks):
            if networks[i].intersection(networks[j]):
                networks[i].union(networks[j])
                networks.remove(networks[j])
            else:
                j += 1
        i += 1


    return len(networks)


print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]]))
print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))

