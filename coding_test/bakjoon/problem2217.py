

if __name__ == '__main__':

    x = input()

    arr = []

    for i in range(int(x)):
        y = input()
        arr.append(int(y))

    arr.sort(reverse=True)

    cnt = len(arr)
    tmp = 0
    while cnt != 0:
        t = arr.pop() * cnt
        if t > tmp:
            tmp = t
        cnt-=1
    print(tmp)






