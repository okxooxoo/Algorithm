from heapq import heappop, heappush, heapify

def solution(scoville, K):
    heapify(scoville) # 힙으로 변환
    count = 0
    while scoville:
        a = heappop(scoville)
        if a >= K:
            return count
        if not scoville:
            return -1
        b = heappop(scoville)
        mix = a + b * 2
        heappush(scoville, mix)
        count += 1
    return -1