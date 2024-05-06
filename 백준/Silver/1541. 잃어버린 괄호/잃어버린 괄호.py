inputs = input().split('-')
s = 0
for i in inputs[0].split('+'):
    s += int(i)
for i in inputs[1:]:
    for j in i.split('+'):
        s -= int(j)
print(s)