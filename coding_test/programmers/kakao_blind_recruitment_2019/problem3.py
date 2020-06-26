from itertools import permutations, combinations, chain


def solution(relation):
    answer = 0

    column_cnt = len(relation[0])
    tuple_cnt = len(relation)
    tmp = [i for i in range(column_cnt)]
    keys = []

    for i in range(len(tmp)):

        for j in list(combinations(tmp, i+1)):
            if tuple_cnt == re_relation(relation, j):
                keys.append(j)

    tmp = []
    print(keys)

    for i in range(len(keys)):
        for j in keys[i+1:]:
            if set(keys[i]) < set(j):
                tmp.append(j)

    for i in set(tmp):
        keys.remove(i)
    answer = len(keys)

    print(keys)

    return answer


def re_relation(ori_relation, k):
    new_realation = []
    for t in ori_relation:
        new_row = []
        for i in k:
            new_row.append(t[i])
        new_realation.append(new_row)

    return len(set(tuple(sorted(sub)) for sub in new_realation))

solution([["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]])