T = int(input())
for _ in range(T):
    H, W, N = map(int, input().split())
    Y = (N - 1) % H + 1
    X = (N - 1) // H + 1
    if X < 10:
        print(f'{Y}0{X}')
    else:
        print(f'{Y}{X}')