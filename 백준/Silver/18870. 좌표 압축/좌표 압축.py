import sys
input = sys.stdin.readline

N = int(input())
numbers = list(map(int, input().split()))
lst = sorted(list(set(numbers))) # 중복 제거 후 오름차순 정렬

dic = {}
for i in range(len(lst)):
    dic[lst[i]] = i

for i in numbers:
    print(dic[i] ,end=' ')