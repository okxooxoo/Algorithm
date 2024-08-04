import sys
import math
input = sys.stdin.readline

N, M, K = map(int, input().split())
numbers = []
for _ in range(N):
    numbers.append(int(input()))

# 세그먼트 트리 생성
h = math.ceil(math.log2(N))
tree = [0] * (2 ** (h+1))

# 세그먼트 트리 초기화
def init(start, end, index):
    if start == end:
        tree[index] = numbers[start]
        return tree[index]
    
    mid = (start + end) // 2
    tree[index] = (init(start, mid, index * 2) * init(mid + 1, end, index * 2 + 1)) % 1_000_000_007
    return tree[index]

# 세그먼트 트리 갱신
def update(start, end, index, target):
    # 구간 밖에 있는 경우
    if target < start or target > end:
        return
    # 구간 안에 있고 리프 노드인 경우
    if start == end:
        tree[index] = numbers[target]
    # 구간 안에 있고 리프 노드가 아닌 경우
    else:
        mid = (start + end) // 2
        update(start, mid, index * 2, target)
        update(mid + 1, end, index * 2 + 1, target)
        tree[index] = (tree[2 * index] * tree[2 * index + 1]) % 1_000_000_007

# 구간곱 구하기
def section_multiply(start, end, index, left, right):
    # 구간 밖에 있는 경우
    if left > end or right < start:
        return 1
    # 구간 안에 있는 경우
    if left <= start and right >= end:
        return tree[index]
    # 여러 구간에 걸친 경우
    mid = (start + end) // 2
    return (section_multiply(start, mid, index * 2, left, right) * section_multiply(mid + 1, end, index * 2 + 1, left, right)) % 1_000_000_007

init(0, N-1, 1)
for _ in range(M+K):
    a, b, c = map(int, input().split())
    if a == 1:
        numbers[b-1] = c
        update(0, N-1, 1, b-1)
    if a == 2:
        print(section_multiply(0, N-1, 1, b-1, c-1))