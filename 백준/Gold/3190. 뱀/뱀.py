from collections import deque

N = int(input()) # 보드의 크기
K = int(input()) # 사과의 개수
# True면 사과가 존재
board = [[False for _ in range(N)] for _ in range(N)]

for _ in range(K):
    r, c = map(int, input().split())
    board[r-1][c-1] = True

L = int(input()) # 뱀의 방향 변환 횟수
direction_info = []
for _ in range(L):
    X, C = input().split()
    direction_info.append((int(X), C))

# 자신의 몸과 부딪히는지 여부 반환
def is_bumped_self(head_r, head_c):
    for i in range(len(snake)-2, -1, -1):
        if head_r == snake[i][0] and head_c == snake[i][1]:
            return True
    return False

# 처음 방향 변환 정보 저장
direction_info.reverse()
X, C = direction_info.pop()

moves = { 'RIGHT': (0, 1), 'DOWN': (1, 0), 'LEFT': (0, -1), 'UP': (-1, 0) }
moves_key = ['RIGHT', 'DOWN', 'LEFT', 'UP']
moves_key_index = 0

snake = deque([(0, 0)])
time = 0

# 게임 시작
while True:
    time += 1
    # 몸길이 늘리기
    head_r, head_c = snake[-1]
    head_r += moves[moves_key[moves_key_index]][0]
    head_c += moves[moves_key[moves_key_index]][1]
    snake.append((head_r, head_c))
    # 보드 범위를 벗어나면
    if head_r < 0 or head_c < 0 \
    or head_r >= N or head_c >= N:
        break
    # 뱀이 자신의 몸과 부딪히면
    if is_bumped_self(head_r, head_c):
        break
    # 이동한 칸에 사과가 있다면
    if board[head_r][head_c]:
        board[head_r][head_c] = False
    # 이동한 칸에 사과가 없다면
    else:
        snake.popleft()
    # 방향 전환
    if X == time:
        if C == 'L':
            moves_key_index = (moves_key_index + 3) % 4
        else:
            moves_key_index = (moves_key_index + 1) % 4
        if direction_info:
            X, C = direction_info.pop()

print(time)