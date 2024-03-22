from collections import deque

def solution(n, edge):
    # ex) 1번 인덱스에는 1번 노드와 연결된 노드들의 번호가 담김
    graph = [[] for i in range(n+1)]
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    # 1번 노드부터 시작
    result = bfs(1, graph)
    return result

def bfs(start_node, graph):
    distance = {} # 각 노드까지의 거리
    dq = deque([(start_node, 0)]) # 노드 번호와 거리
    visited = set([start_node]) # 방문 처리
    while dq:
        node, dist = dq.popleft()
        distance[node] = dist
        for new_node in graph[node]:
            if new_node not in visited:
                visited.add(new_node)
                dq.append((new_node, dist+1))
    # 가장 멀리 떨어진 노드를 찾음
    maxDist = max(distance.values())
    count = 0
    for node, dist in distance.items():
        if dist == maxDist:
            count += 1
    return count