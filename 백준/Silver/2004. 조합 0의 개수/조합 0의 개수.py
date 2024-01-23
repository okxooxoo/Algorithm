# 조합 공식
# n! / m!(n-m)!

n, m = map(int, input().split())

def countDivision(a, b):
    count = 0
    while a > 0:
        count += a // b
        a = a // b
    return count

count2 = countDivision(n, 2) - countDivision(m, 2) - countDivision(n-m, 2)
count5 = countDivision(n, 5) - countDivision(m, 5) - countDivision(n-m, 5)
print(min(count2, count5))