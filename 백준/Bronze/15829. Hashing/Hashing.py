L = int(input())
inputs = list(input())
s = 0
for i in range(L):
    s += ((ord(inputs[i]) - 96) * (31 ** i)) % 1234567891
print(s)