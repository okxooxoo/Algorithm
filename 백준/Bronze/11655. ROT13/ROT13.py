S = input()

result = ""
for s in S:
    if s.islower() or s.isupper():
        asc = ord(s)
        if asc >= 78 and asc <= 90 or asc >= 110:
            result += chr(asc - 13)
        else:
            result += chr(asc + 13)
    else:
        result += s

print(result)