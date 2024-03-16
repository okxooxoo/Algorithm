import sys
input = sys.stdin.readline

N = int(input())
timetable = []
for _ in range(N):
    start, end = map(int, input().split())
    timetable.append([start, end])

timetable.sort(key = lambda x: x[0]) # 시작하는 시간(start)을 기준으로 오름차순 정렬
timetable.sort(key = lambda x: x[1]) # 끝나는 시간(end)을 기준으로 오름차순 정렬

last = 0 # 마지막으로 회의가 끝난 시간
count = 0
for start, end in timetable:
    if last <= start:
        last = end
        count += 1
print(count)