N = int(input())
table = []
for _ in range(N):
    x, y = map(int, input().split())
    table.append((x, y))
for i in range(N):
    grade = 1
    for j in range(N):
        if table[i][0] < table[j][0] and table[i][1] < table[j][1]:
            grade += 1
    print(grade, end=' ')