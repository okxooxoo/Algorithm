import sys
import math
input = sys.stdin.readline

N, M = map(int, input().split())
numbers = []
for _ in range(N):
    numbers.append(int(input()))

# 트리의 높이
h = math.ceil(math.log2(N))
# 최솟값 세그먼트 트리 생성
min_tree = [0] * (2 ** (h+1))
# 최댓값 세그먼트 트리 생성
max_tree = [0] * (2 ** (h+1))

# 최솟값 세그먼트 트리 초기화
def min_init(start, end, index):
    if start == end:
        min_tree[index] = numbers[start]
        return min_tree[index]
    
    mid = (start + end) // 2
    min_tree[index] = min(min_init(start, mid, 2 * index), min_init(mid + 1, end, 2 * index + 1))
    return min_tree[index]

# 최댓값 세그먼트 트리 초기화
def max_init(start, end, index):
    if start == end:
        max_tree[index] = numbers[start]
        return max_tree[index]
    
    mid = (start + end) // 2
    max_tree[index] = max(max_init(start, mid, 2 * index), max_init(mid + 1, end, 2 * index + 1))
    return max_tree[index]

# 구간 최솟값 구하기
def get_section_min(start, end, index, left, right):
    # 범위 밖에 있는 경우
    if left > end or right < start:
        return sys.maxsize
    # 범위 안에 있는 경우
    if left <= start and right >= end:
        return min_tree[index]
    # 여러 범위에 걸친 경우
    mid = (start + end) // 2
    return min(get_section_min(start, mid, 2 * index, left, right), get_section_min(mid + 1, end, 2 * index + 1, left, right))

# 구간 최댓값 구하기
def get_section_max(start, end, index, left, right):
    # 범위 밖에 있는 경우
    if left > end or right < start:
        return 0
    # 범위 안에 있는 경우
    if left <= start and right >= end:
        return max_tree[index]
    # 여러 범위에 걸친 경우
    mid = (start + end) // 2
    return max(get_section_max(start, mid, 2 * index, left, right), get_section_max(mid + 1, end, 2 * index + 1, left, right))

min_init(0, N-1, 1)
max_init(0, N-1, 1)
for _ in range(M):
    a, b = map(int, input().split())
    section_min = get_section_min(0, N-1, 1, a-1, b-1)
    section_max = get_section_max(0, N-1, 1, a-1, b-1)
    print(section_min, section_max)