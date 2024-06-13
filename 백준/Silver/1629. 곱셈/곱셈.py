A, B, C = map(int, input().split())

def power(A, B, C):
    if B == 1:
        return A % C
    
    temp = power(A, B // 2, C)

    if B % 2 == 1:
        return (temp * temp * A) % C
    else:
        return (temp * temp) % C

print(power(A, B, C))