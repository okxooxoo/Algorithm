import sys
input = sys.stdin.readline

N = int(input())
coords = []
for _ in range(N):
    x, y = map(int, input().split())
    coords.append([y, x]) # 2차원 리스트
coords.sort()       
for coord in coords:
    print(coord[1], coord[0])