N = int(input())
M = int(input())
K = 2 * N + 1
string = input()
dest = 'IO' * N + 'I'
count = 0
for i in range(M - K + 1):
    if string[i:i+K] == dest:
        count += 1
print(count)