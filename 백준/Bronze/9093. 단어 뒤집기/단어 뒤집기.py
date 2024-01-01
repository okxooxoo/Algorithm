import sys
input = sys.stdin.readline

N = int(input())
for _ in range(N):
    sentence = input().split()
    for word in sentence:
        print(word[::-1], end=' ')
    print()