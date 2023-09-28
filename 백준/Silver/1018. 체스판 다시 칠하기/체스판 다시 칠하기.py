N, M = map(int, input().split())
chess = []
for _ in range(N):
    chess.append(input())
    
color = 'W'
Min = N * M
for n in range(N-7):
    for m in range(M-7):
        count = 0
        for i in range(n, n+8):
            for j in range(m, m+8):
                if chess[i][j] != color:
                    count += 1
                if j != m+7:
                    if color == 'W':
                        color = 'B'
                    else:
                        color = 'W'
        if min(count, 64-count) < Min:
            Min = min(count, 64-count)
print(Min)