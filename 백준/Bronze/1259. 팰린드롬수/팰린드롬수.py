while True:
    N = input()
    if N == '0':
        break
    if N == ''.join(reversed(N)):
        print('yes')
    else:
        print('no')