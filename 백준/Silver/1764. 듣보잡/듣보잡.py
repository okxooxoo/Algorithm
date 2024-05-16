N, M = map(int, input().split())
ear = set()
eye = set()
for _ in range(N):
    ear.add(input())
for _ in range(M):
    eye.add(input())

result = sorted(ear & eye)
print(len(result))
for name in result:
    print(name)