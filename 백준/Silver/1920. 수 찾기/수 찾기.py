import sys
input = sys.stdin.readline

N = int(input())
A = sorted(list(map(int, input().split())))
M = int(input())
B = list(map(int, input().split()))

def binary_search(target, lst):
    start = 0
    end = len(lst) - 1

    while start <= end:
        mid = (start + end) // 2

        if lst[mid] == target:
            return 1
        elif lst[mid] > target:
            end = mid - 1
        else:
            start = mid + 1
    return 0

for b in B:
    print(binary_search(b, A))