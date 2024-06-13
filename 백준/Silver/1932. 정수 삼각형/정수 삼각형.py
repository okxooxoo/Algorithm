n = int(input())
s = 0
triangle = []
for _ in range(n):
    row = list(map(int, input().split()))
    triangle.append(row)
for i in range(n-2, -1, -1):
    for j in range(i+1):
        triangle[i][j] += max(triangle[i+1][j], triangle[i+1][j+1])
print(triangle[0][0])