N, M = map(int, input().split())
video = list(map(int, input().split()))

start = max(video)
end = sum(video)

while start <= end:
    mid = (start + end) // 2

    count = 1
    sum = 0

    for v in video:
        if sum + v > mid:
            count += 1
            sum = 0
        sum += v

    if count <= M:
        end = mid - 1
    else:
        start = mid + 1

print(start)