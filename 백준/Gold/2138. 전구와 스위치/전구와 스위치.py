N = int(input())
current = list(map(int, input()))
dest = list(map(int, input()))
# 첫번째 스위치를 누르는 경우
temp1 = current[:]
temp1[0] = 1 - temp1[0]
temp1[1] = 1 - temp1[1]
count1 = 1
for i in range(1, N):
    if temp1[i-1] != dest[i-1]:
        temp1[i-1] = 1 - temp1[i-1]
        temp1[i] = 1 - temp1[i]
        if i != N-1:
            temp1[i+1] = 1 - temp1[i+1]
        count1 += 1
if temp1 != dest:
    count1 = 100001
# 첫번째 스위치를 누르지 않는 경우
temp2 = current[:]
count2 = 0
for i in range(1, N):
    if temp2[i-1] != dest[i-1]:
        temp2[i-1] = 1 - temp2[i-1]
        temp2[i] = 1 - temp2[i]
        if i != N-1:
            temp2[i+1] = 1 - temp2[i+1]
        count2 += 1
if temp2 != dest:
    count2 = 100001
answer = min(count1, count2)
if answer == 100001:
    print(-1)
else:
    print(answer)