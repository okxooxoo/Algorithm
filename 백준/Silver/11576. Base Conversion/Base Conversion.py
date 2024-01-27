A, B = map(int, input().split())
m = int(input())
lst = list(map(int, input().split()))

dec = 0
i = 1
# A진법을 10진법으로 변환
while lst:
    dec += lst.pop() * i
    i *= A
result = []
# 10진법을 B진법으로 변환
while dec:
    result.append(dec % B)
    dec //= B

print(*reversed(result))