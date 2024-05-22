N = int(input())
paper = []
for _ in range(N):
    row = list(map(int, input().split()))
    paper.append(row)

def count_blue(x, y, l):
    if l == 1:
        if paper[x][y] == 0:
            return 0
        else:
            return 1
    for i in range(x, x+l):
        for j in range(y, y+l):
            if paper[i][j] == 0: # 하얀색이면
                return count_blue(x, y, l // 2) \
                    + count_blue(x + l // 2, y, l // 2) \
                    + count_blue(x, y + l // 2, l // 2) \
                    + count_blue(x + l // 2, y + l // 2, l // 2)
    return 1

def count_white(x, y, l):
    if l == 1:
        if paper[x][y] == 1:
            return 0
        else:
            return 1
    for i in range(x, x+l):
        for j in range(y, y+l):
            if paper[i][j] == 1: # 파란색이면
                return count_white(x, y, l // 2) \
                    + count_white(x + l // 2, y, l // 2) \
                    + count_white(x, y + l // 2, l // 2) \
                    + count_white(x + l // 2, y + l // 2, l // 2)
    return 1

print(count_white(0, 0, N))
print(count_blue(0, 0, N))