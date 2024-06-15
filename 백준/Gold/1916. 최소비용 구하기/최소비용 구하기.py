import heapq
import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    start, end, cost = map(int, input().split())
    graph[start].append((end, cost))
start, end = map(int, input().split())

def dijkstra(start, end):
    heap = []
    distance = [sys.maxsize] * (N+1)
    visited = [False] * (N+1)

    heapq.heappush(heap, (0, start))
    distance[start] = 0

    while heap:
        current_cost, current_node = heapq.heappop(heap)
        if current_node == end:
            return current_cost
        if visited[current_node]:
            continue
        visited[current_node] = True
        for next_node, next_cost in graph[current_node]:
            cost = current_cost + next_cost
            if distance[next_node] > cost:
                distance[next_node] = cost
                heapq.heappush(heap, (cost, next_node))

print(dijkstra(start, end))