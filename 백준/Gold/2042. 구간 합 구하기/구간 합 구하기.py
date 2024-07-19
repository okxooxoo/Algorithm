# N은 수의 개수
# M은 수의 변경이 일어나는 횟수
# K은 구간의 합을 구하는 횟수
import sys
import math

input = sys.stdin.readline

N, M, K = map(int, input().split())
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
    tree[index] = init(start, mid, index * 2) + init(mid + 1, end, index * 2 + 1)
    return tree[index]

# 구간 합 구하기
def section_sum(start, end, index, left, right):
    # 범위 밖에 있는 경우
    if left > end or right < start:
        return 0
    # 범위 안에 있는 경우
    if left <= start and right >= end:
        return tree[index]
    # 둘 다 아닌 경우
    mid = (start + end) // 2
    return section_sum(start, mid, index * 2, left, right) \
        + section_sum(mid + 1, end, index * 2 + 1, left, right)

# 배열 값 수정
def update(start, end, index, target, value):
    # 범위 밖에 있는 경우
    if target < start or target > end:
        return
    # 범위 안에 있는 경우
    tree[index] += value
    if start == end:
        return
    mid = (start + end) // 2
    update(start, mid, index * 2, target, value)
    update(mid + 1, end, index * 2 + 1, target, value)

init(0, N-1, 1)
for _ in range(M+K):
    a, b, c = map(int, input().split())
    if a == 1:
        # b번째 수를 c로 바꾸기
        update(0, N-1, 1, b-1, c-numbers[b-1])
        numbers[b-1] = c
    if a == 2:
        # b번째 수부터 c번째 수까지의 합 구하기
        print(section_sum(0, N-1, 1, b-1, c-1))