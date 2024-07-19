import sys
import math

input = sys.stdin.readline

N, M = map(int, input().split())
numbers = []
for _ in range(N):
    numbers.append(int(input()))

# 트리의 높이
h = math.ceil(math.log2(N))
# 세그먼트 트리 생성
tree = [0] * (2 ** (h+1))

# 세그먼트 트리 초기화
def init(start, end, index):
    if start == end:
        tree[index] = numbers[start]
        return tree[index]
    mid = (start + end) // 2
    tree[index] = min(init(start, mid, index * 2), init(mid + 1, end, index * 2 + 1))
    return tree[index]

# 구간 최솟값 구하기
def section_min(start, end, index, left, right):
    # 범위 밖에 있는 경우
    if left > end or right < start:
        return sys.maxsize
    # 범위 안에 있는 경우
    if left <= start and right >= end:
        return tree[index]
    # 둘 다 아닌 경우
    mid = (start + end) // 2
    return min(section_min(start, mid, index * 2, left, right), section_min(mid + 1, end, index * 2 + 1, left, right))

init(0, N-1, 1)
for _ in range(M):
    a, b = map(int, input().split())
    print(section_min(0, N-1, 1, a-1, b-1))
    