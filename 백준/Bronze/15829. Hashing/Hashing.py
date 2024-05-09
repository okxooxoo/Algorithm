L = int(input())
inputs = list(input())
s = 0
for i in range(L):
    s += (ord(inputs[i]) - 96) * (31 ** i)
print(s % 1234567891)