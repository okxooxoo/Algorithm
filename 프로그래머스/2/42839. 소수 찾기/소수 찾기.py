from itertools import permutations

def solution(numbers):
    s = set()
    for i in range(1, len(numbers)+1):
        for j in permutations(numbers, i):
            number = int(''.join(j))
            s.add(number)
    answer = 0
    for number in s:
        if isPrime(number):
            answer += 1
    return answer

def isPrime(number):
    if number == 0 or number == 1:
        return False
    for i in range(2, number):
        if number % i == 0:
            return False
    return True