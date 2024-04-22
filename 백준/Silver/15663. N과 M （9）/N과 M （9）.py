# N개의 자연수 중에서 M개를 고른 수열(N개의 자연수에 같은 수 포함)
N, M = map(int, input().split())
numbers = list(map(int, input().split()))
numbers.sort() # 수열은 사전 순으로 증가하는 순서로 출력

# 방문 여부 (같은 수를 구별하기 위해 result와 별도로 선언, 인덱스 0의 1과 인덱스 1의 1은 다름!)
visited = [False] * N
result = []
def dfs():
    if len(result) == M:
        print(*result)
        return
    prev = 0
    for i in range(N):
        if not visited[i] and numbers[i] != prev:
            prev = numbers[i]
            visited[i] = True
            result.append(numbers[i])
            dfs()
            # 백트래킹
            visited[i] = False
            result.pop()

dfs()