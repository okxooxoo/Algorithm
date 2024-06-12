string = input()
dic = {}
for s in string:
    dic[s] = dic.get(s, 0) + 1
is_palindrome = True
palindrome_str = ''
odd_char = ''
for s, count in sorted(dic.items()):
    palindrome_str += s * (count // 2)
    if count % 2 == 1:
        if odd_char:
            is_palindrome = False
            break
        else:
            odd_char = s
if is_palindrome:
    print(palindrome_str + odd_char + palindrome_str[::-1])
else:
    print("I'm Sorry Hansoo")