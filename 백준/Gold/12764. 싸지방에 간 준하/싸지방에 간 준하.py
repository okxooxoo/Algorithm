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
heapq.heappush(min_heap, (schedule[0][1], schedule[0][0], 0))  # 종료 시간, 시작 시간, 컴퓨터 index
computer_count[0] += 1

count = 0
for i in range(1, N):
    start = schedule[i][0]
    end = schedule[i][1]

    # 사용 가능한 컴퓨터 갱신
    while min_heap and min_heap[0][0] <= start:
        temp = heapq.heappop(min_heap)
        heapq.heappush(available_computer, temp[2])

    if available_computer:
        index = heapq.heappop(available_computer)
        computer_count[index] += 1
        heapq.heappush(min_heap, (end, start, index))

    else:
        count += 1
        computer_count[count] += 1
        heapq.heappush(min_heap, (end, start, count))
        
# 출력
print(count+1)
print(*computer_count[:count+1])