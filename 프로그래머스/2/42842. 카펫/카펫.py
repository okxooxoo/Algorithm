import math

def solution(brown, yellow):
    area = yellow + brown
    area_combination = get_combination(area) # 곱해서 넓이가 되는 두 수의 조합
    yellow_combination = get_combination(yellow) # 곱해서 yellow가 되는 두 수의 조합
    
    for a, b in area_combination:
        if [a - 2, b - 2] in yellow_combination:
            return [b, a]

def get_combination(number):
    combination = []
    for i in range(1, int(math.sqrt(number)) + 1):
        if number % i == 0:
            combination.append([i, number // i])
    return combination