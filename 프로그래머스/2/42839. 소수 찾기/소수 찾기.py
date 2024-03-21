from itertools import permutations
import math

def solution(numbers):
    number_set = set()
    for i in range(1, len(numbers)+1):
        for j in permutations(numbers, i):
            number = int(''.join(j))
            number_set.add(number)
    answer = 0
    for number in number_set:
        if is_prime(number):
            answer += 1
    return answer

def is_prime(number):
    if number in (0, 1):
        return False
    for i in range(2, int(math.sqrt(number)) + 1):
        if number % i == 0:
            return False
    return True