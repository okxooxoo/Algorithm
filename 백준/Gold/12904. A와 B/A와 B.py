S = input()
T = input()

while len(T) > 0:
    if T == S:
        break
    if T[-1] == 'A':
        T = T[:-1]
    elif T[-1] == 'B':
        T = T[:-1]
        T = T[::-1]
if T == S:
    print(1)
else:
    print(0)