import sys
input = sys.stdin.readline

N, M = map(int, input().split())
dict = {}
for i in range(1, N+1):
    pokemon = input().rstrip()
    dict[str(i)] = pokemon
    dict[pokemon] = i

for _ in range(M):
    inputs = input().rstrip()
    print(dict[inputs])