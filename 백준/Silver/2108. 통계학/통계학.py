import sys
input = sys.stdin.readline

N = int(input())
lst = []
dic = {}
for _ in range(N):
    num = int(input())
    lst.append(num)
    dic[num] = dic.get(num, 0) + 1
max_count = max(dic.values())
max_counts = [k for k, v in dic.items() if v == max_count] # 최빈값 찾기
lst.sort()
max_counts.sort()
print(round(sum(lst) / N)) # 평균
print(lst[N // 2]) # 중앙값
print(max_counts[0] if len(max_counts) == 1 else max_counts[1]) # 최빈값
print(lst[-1] - lst[0]) # 범위