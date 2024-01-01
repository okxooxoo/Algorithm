import sys
input = sys.stdin.readline

N = int(input())
stack = []
for _ in range(N):
    cmd = input().rstrip()

    # 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
    if cmd == "size":
        print(len(stack))
    elif cmd == "empty":
        print("1" if len(stack) == 0 else "0")
    elif cmd == "top":
        print(stack[-1] if len(stack) != 0 else "-1")
    elif cmd == "pop":
        print(stack.pop() if len(stack) != 0 else "-1")
    else:
        str, num = cmd.split()
        stack.append(int(num))