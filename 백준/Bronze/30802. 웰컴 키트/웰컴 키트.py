N = int(input())
Tshirt = list(map(int, input().split()))
T, P = map(int, input().split())
T_count = 0
for num in Tshirt:
    T_count += (num - 1) // T + 1
print(T_count)
print(N // P, N % P)