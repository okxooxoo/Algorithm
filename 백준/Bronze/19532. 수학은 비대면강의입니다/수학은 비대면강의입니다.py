a, b, c, d, e, f = map(int, input().split())
x = (f*b - e*c) // (b*d - e*a)
y = (a*f - c*d) // (e*a - b*d)
print(x, y)