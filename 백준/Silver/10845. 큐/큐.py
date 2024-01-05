import sys
input = sys.stdin.readline

N = int(input())
queue = []
for _ in range(N):
    cmd = input().split()

    if cmd[0] == 'push':
        queue.append(cmd[1])
    elif cmd[0] == 'pop':
        print(queue.pop(0)) if len(queue) > 0 else print('-1')
    elif cmd[0] == 'size':
        print(len(queue))
    elif cmd[0] == 'empty':
        print('1') if len(queue) == 0 else print('0')
    elif cmd[0] == 'front':
        print(queue[0]) if len(queue) > 0 else print('-1')
    elif cmd[0] == 'back':
        print(queue[-1]) if len(queue) > 0 else print('-1')