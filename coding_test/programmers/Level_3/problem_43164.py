"""
    Problem Name    : 여행경로
    Problem Status  : Solving
    Problem From    : DFS / BFS
    Problem Link    : https://programmers.co.kr/learn/courses/30/lessons/431624

"""


def solution(tickets):

    stack = []

    candidates = [[departure, arrival] for (departure, arrival) in tickets if departure == "ICN"]
    candidates.sort(reverse=False, key=lambda x: x[1])
    course = candidates[:1]
    stack.append((candidates[1:], tickets, course)) if candidates[1:] else None
    tickets.remove(course[0])

    while len(tickets):
        candidates = [[departure, arrival] for (departure, arrival) in tickets if departure == course[-1][1]]
        candidates.sort(reverse=False, key=lambda x: x[1])
        if not candidates:
            candidates, tickets, course = stack.pop(-1)
            stack.append((candidates[1:], tickets, course)) if candidates[1:] else None
        else:
            stack.append((candidates[1:], tickets, course)) if candidates[1:] else None
            tickets.remove(candidates[0])
            course.append(candidates[0])

    answer = ["ICN"]
    answer += [arrival for departure, arrival in course]

    return answer


# print(solution([["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]))
print(solution([["ICN", "JFK"], ["ICN", "HND"], ["HND", "ICN"], ["HND", "IAD"], ["JFK", "HND"]]))
# print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))로

