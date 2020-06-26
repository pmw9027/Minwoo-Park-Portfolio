import sys
import collections

arr = collections.deque()
tmp = []
ttt = {1, 2, 3, 4, 5, 6, 7, 8, 9}
for i in range(9):
    string = sys.stdin.readline()
    t = {int(temp) for temp in string.split(" ")}
    arr.append(t)
    for k in [j for j, x in enumerate(t) if x == 0]:
        tmp.append([i, k, ttt - set(t)])

while len(tmp) > 0:
    for i in tmp:
        if len(i[2]) > 1:
            i[2] -= {row[i[1]] for row in arr}

            if len(ttt) > 1:
                tt = []

                x = int(int((i[0] / 3)) * 3)
                y = int(int((i[1] / 3)) * 3)
                for j in range(3):
                    for k in range(3):
                        tt.append(arr[x+j][y+k])

                i[2] -= set(tt)

        if len(i[2]) == 1:
            arr[i[0]][i[1]] = i[2].pop()
            tmp.remove(i)

    if [i for i in tmp if len(i[2]) == 1] == 0:
        # print(tmp)
        exit()


for i in arr:
    print(' '.join([str(j) for j in i]))



