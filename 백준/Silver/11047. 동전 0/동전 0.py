N, K = map(int, input().split())
A = []
for _ in range(N):
    A.append(int(input()))
count = 0
while K > 0:
    money = A.pop()
    count += K // money
    K = K % money
print(count)