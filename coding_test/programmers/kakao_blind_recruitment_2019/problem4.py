def solution_old(food_times, k):

    print(sum(food_times))

    if sum(food_times) <= k:
        return -1

    ind = 0
    i = 0
    while i < k:
        j = (i + ind) % len(food_times)
        if food_times[j] == 0:
            ind += 1
        else:
            food_times[j] -= 1
            i += 1

    answer = (i + ind) % len(food_times)

    return answer + 1


def solution(food_times, k):
    if sum(food_times) <= k:
        return -1

    # tmp = len(food_times)
    for k - len(food_times) <= len(food_times):
        k -=  len(food_times)

        for food in food_times:

            food+=-1

    answer = k + 1

    return answer


print(solution([3, 1, 2], 5))
print(solution([4, 3, 5, 6, 2], 7))
print(solution([4, 1, 1, 5], 4))


