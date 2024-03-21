from functools import cmp_to_key

def solution(numbers):
    answer = list(map(str, numbers))
    answer.sort(key=cmp_to_key(compare), reverse=True)
    if answer[0] == '0':
        return '0'
    return ''.join(answer)

def compare(a, b):
    if int(a + b) > int(b + a):
        return 1
    if int(a + b) < int(b + a):
        return -1
    return 0