S = input()
substring = set()
for i in range(1, len(S) + 1):
    for j in range(len(S) + 1 - i):
        substring.add(S[j:j+i])
print(len(substring))