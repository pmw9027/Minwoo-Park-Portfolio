

if __name__ == '__main__':

    x = int(input())

    arr = []
    output = []
    for i in range(x):
        y = input()
        tmp = y.split(" ")
        if tmp[0] == 'push':
            arr.append(tmp[1])
        elif tmp[0] == 'top':
            output.append(-1) if len(arr) == 0 else output.append(arr[-1])
        elif tmp[0] == 'size':
            output.append(len(arr))
        elif tmp[0] == 'empty':
            output.append(1) if len(arr) == 0 else output.append(0)
        elif tmp[0] == 'pop':
            output.append(-1) if len(arr) == 0 else output.append(arr.pop())



    for i in output:
        print(i)