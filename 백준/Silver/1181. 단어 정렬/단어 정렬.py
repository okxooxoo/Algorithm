import sys
input = sys.stdin.readline

N = int(input())
words = []
for _ in range(N):
    word = input().strip() # 개행문자 제외하여 입력받음
    words.append(word)

words = list(set(words)) # 중복 제거
words.sort() # 사전순 정렬
words.sort(key=len) # 길이순 정렬

for word in words:
    print(word)