N = int(input())
times = list(map(int, input().split())) # 돈을 인출하는데 걸리는 시간
times.sort()

for i in range(1, N):
    times[i] += times[i-1]
print(sum(times))