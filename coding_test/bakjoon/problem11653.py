

if __name__ == '__main__':

    x = int(input())
    original = x
    arr = []

    i = 2
    while i * i <= original:
        if x % i == 0:
            x /= i
            arr.append(i)
        else:
            i += 1
    arr.append(int(x))
    for i in arr:
        print(i)

