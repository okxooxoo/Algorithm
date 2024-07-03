import heapq
import sys
input = sys.stdin.readline

V, E = map(int, input().split())
K = int(input())
graph = [[] for _ in range(V+1)]
for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))


def dijkstra(start):
    heap = []
    visited = [False] * (V + 1)
    distance = [float('inf')] * (V + 1)

    heapq.heappush(heap, (0, start))
    distance[start] = 0

    while heap:
        current_cost, current_node = heapq.heappop(heap)
        # 방문 여부 확인
        if visited[current_node]:
            continue
        # 방문 처리
        visited[current_node] = True
        for next_node, next_cost in graph[current_node]:
            cost = current_cost + next_cost
            if distance[next_node] > cost:
                distance[next_node] = cost
                heapq.heappush(heap, (cost, next_node))

    return distance


for cost in dijkstra(K)[1:]:
    if cost == float('inf'):
        print('INF')
    else:
        print(cost)
