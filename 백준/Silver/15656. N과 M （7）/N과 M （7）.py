# N개의 자연수 중에서 M개를 고른 수열 (중복 허용)
N, M = map(int, input().split())
numbers = list(map(int, input().split()))
numbers.sort() # 수열은 사전 순으로 증가하는 순서로 출력

visited = []
def dfs():
    if len(visited) == M:
        print(*visited)
        return
    
    for n in numbers:
        visited.append(n)
        dfs()
        # 백트래킹
        visited.pop()

dfs()