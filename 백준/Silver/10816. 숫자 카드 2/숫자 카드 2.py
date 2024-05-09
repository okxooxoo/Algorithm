N = int(input())
inputs = list(map(int, input().split()))
M = int(input())
targets = list(map(int, input().split()))

cards = {}
for i in inputs:
    cards[i] = cards.get(i, 0) + 1

for i in targets:
    print(cards.get(i, 0), end=' ')