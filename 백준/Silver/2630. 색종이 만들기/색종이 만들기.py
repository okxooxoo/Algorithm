N = int(input())
paper = []
for _ in range(N):
    row = list(map(int, input().split()))
    paper.append(row)

white = 0
blue = 0
def cut(x, y, l):
    global white
    global blue

    color = paper[x][y]
    for i in range(x, x+l):
        for j in range(y, y+l):
            if paper[i][j] != color:
                cut(x, y, l // 2)
                cut(x + l // 2, y, l // 2)
                cut(x, y + l // 2, l // 2)
                cut(x + l // 2, y + l // 2, l // 2)
                return
            
    if color == 0:
        white += 1
    else:
        blue += 1
    

cut(0, 0, N)
print(white)
print(blue)