from collections import deque
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())
graph = [[] for _ in range(N+1)] # 인접리스트
for _ in range(N):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False] * (N + 1) # 방문 여부
cycle = [-1] * (N + 1) # 사이클까지의 거리
# 사이클을 구함
def dfs(station, start, depth):
    for i in graph[station]:
        # 방문하지 않았으면
        if not visited[i]:
            visited[i] = True
            if dfs(i, start, depth + 1):
                return True
            # 백트래킹
            visited[i] = False
        # 순환선을 찾으면
        elif i == start and depth > 2:
            # 순환선 위 역의 거리를 0으로 표시
            for j in range(1, N+1):
                if visited[j]:
                    cycle[j] = 0
            return True
    return False

def bfs(start):
    visited = [False] * (N + 1) # 방문 여부 초기화
    dq = deque([(start, 0)])
    while dq:
        station, depth = dq.popleft()
        # 순환선을 찾으면
        if cycle[station] == 0:
            cycle[start] = depth
            return
        # 이미 방문했으면
        if visited[station]:
            continue
        # 방문 처리
        visited[station] = True
        # 인접한 역 탐색
        for i in graph[station]:
            dq.append((i, depth + 1))

# 순환선 찾기
for i in range(1, N+1):
    visited[i] = True
    if dfs(i, i, 1):
        break
    # 백트래킹
    visited[i] = False

# 지선 위 역의 거리 구하기
for i in range(1, N+1):
    if cycle[i] == -1:
        bfs(i)

# 출력
for i in range(1, len(cycle)):
    print(cycle[i], end=' ')