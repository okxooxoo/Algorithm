N = int(input()) # 센서의 개수
K = int(input()) # 집중국의 개수

if K >= N:
    print(0)
else:
    sensor = list(map(int, input().split())) # 센서 좌표
    distance = [] # 센서 사이의 거리

    sensor.sort() # 센서 좌표 오름차순 정렬

    for i in range(N - 1):
        d = sensor[i + 1] - sensor[i]
        distance.append(d)

    distance.sort(reverse=True) # 센서 사이의 거리 내림차순 정렬

    print(sum(distance[K-1:]))