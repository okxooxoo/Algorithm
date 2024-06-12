n = int(input())
group = []
for _ in range(n):
    group.append(input().split())

group.sort(key=lambda x: int(x[1]))
group.sort(key=lambda x: int(x[2]))
group.sort(key=lambda x: int(x[3]))

print(group[-1][0])
print(group[0][0])