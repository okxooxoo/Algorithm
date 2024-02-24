def getMaxLengthInList(lst):
    length = [1] * len(lst)
    for i in range(1, len(lst)):
        if lst[i] == lst[i-1]:
            length[i] += length[i-1]
    return max(length)

def getMaxLengthInMatrix(matrix):
    length = []
    for i in range(N):
        length.append(getMaxLengthInList(matrix[i]))
    for j in range(N):
        col = [lst[j] for lst in matrix]
        length.append(getMaxLengthInList(col))
    return max(length)

N = int(input())
colors = [] # 사탕의 색상을 담을 2차원 배열
mx = 0
for _ in range(N):
    colors.append(list(input()))

for i in range(N):
    for j in range(N - 1):
        colors[i][j], colors[i][j+1] = colors[i][j+1], colors[i][j]
        temp = getMaxLengthInMatrix(colors)
        if mx < temp: mx = temp
        colors[i][j], colors[i][j+1] = colors[i][j+1], colors[i][j]

for i in range(N - 1):
    for j in range(N):
        colors[i][j], colors[i+1][j] = colors[i+1][j], colors[i][j]
        temp = getMaxLengthInMatrix(colors)
        if mx < temp: mx = temp
        colors[i][j], colors[i+1][j] = colors[i+1][j], colors[i][j]
print(mx)