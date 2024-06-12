num = 0
for _ in range(3):
    s = input()
    if s.isdigit():
        num = int(s)
    elif num != 0:
        num += 1
num += 1
if num % 3 == 0:
    if num % 5 == 0:
        print('FizzBuzz')
    else:
        print('Fizz')
else:
    if num % 5 == 0:
        print('Buzz')
    else:
        print(num)