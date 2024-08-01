import sys
import heapq
input = sys.stdin.readline

N = int(input())
schedule = []
computer_count = [0] * N  # 컴퓨터 이용한 사람 수
available_computer = [] # 사용 가능한 컴퓨터
min_heap = []

for _ in range(N):
    start, end = map(int, input().split())
    schedule.append((start, end))

schedule.sort(key=lambda x: x[0])  # 시작 시각을 기준으로 정렬

count = 0
for start, end in schedule:
    # 사용 가능한 컴퓨터 갱신
    while min_heap and min_heap[0][0] <= start:
        temp = heapq.heappop(min_heap)
        heapq.heappush(available_computer, temp[2])

    if available_computer:
        index = heapq.heappop(available_computer)
        computer_count[index] += 1
        heapq.heappush(min_heap, (end, start, index))

    else:
        computer_count[count] += 1
        heapq.heappush(min_heap, (end, start, count))
        count += 1
        
# 출력
print(count)
print(*computer_count[:count])