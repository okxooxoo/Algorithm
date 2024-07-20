import math

N = int(input())
# 반전 수열
inversion_sequence = [0] + list(map(int, input().split()))
# 원래 수열
sequence = [0] * N

# 트리의 높이
h = math.ceil(math.log2(N))
# 세그먼트 트리 생성
tree = [0] * (2 ** (h+1))

# 세그먼트 트리 초기화
def init(start, end, node):
    if start == end:
        tree[node] = 1
        return tree[node]
    mid = (start + end) // 2
    tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1)
    return tree[node]

# 원래 순열에 위치해야 할 index을 찾는다!
def get_index(start, end, node, value):
    if start == end:
        return start
    mid = (start + end) // 2
    if value < tree[node * 2 + 1]:
        return get_index(mid + 1, end, node * 2 + 1, value)
    else:
        return get_index(start, mid, node * 2, value - tree[node * 2 + 1])

# 세그먼트 트리 갱신
def update(start, end, node, index):
    if not (start <= index <= end):
        return
    tree[node] -= 1
    if start == end:
        return
    mid = (start + end) // 2
    update(start, mid, node * 2, index)
    update(mid + 1, end, node * 2 + 1, index)

init(0, N-1, 1)
for i in range(N, 0, -1):
    index = get_index(0, N-1, 1, inversion_sequence[i])
    sequence[index] = i
    update(0, N-1, 1, index)

print(*sequence)
