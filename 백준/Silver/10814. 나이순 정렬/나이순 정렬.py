import sys
input = sys.stdin.readline

N = int(input())
people = []
for _ in range(N):
    age, name = input().split()
    people.append([int(age), name])

people.sort(key=lambda x:x[0])

for [age, name] in people:
    print(age, name)
