# 조합 함수를 이용한 풀이
from itertools import combinations

heights = []
for _ in range(9):
    h = int(input())
    heights.append(h)
for i in combinations(heights, 7): # 7명을 뽑는 조합
    if sum(i) == 100:
        results = sorted(list(i))
        break
for result in results:
    print(result)