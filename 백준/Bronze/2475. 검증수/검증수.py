numbers = list(map(int, input().split()))
s = 0
for number in numbers:
    s += number ** 2
print(s % 10)