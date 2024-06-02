N = int(input())
dic = {}
for _ in range(N):
    title = input()
    dic[title] = dic.get(title, 0) + 1
mx = max(dic.values())
titles = sorted(dic.keys())
for title in titles:
    if dic[title] == mx:
        print(title)
        break