S = list(input())

alphabet = [0] * 26
for s in S:
    alphabet[ord(s) - 97] += 1

print(*alphabet)