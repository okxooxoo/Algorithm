S = list(input())
l = len(S)
suffix = [] # 접미사 리스트
for _ in range(l):
    suffix.append("".join(S))
    S.pop(0)
suffix.sort()
for s in suffix:
    print(s)