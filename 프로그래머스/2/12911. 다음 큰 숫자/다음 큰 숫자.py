def solution(n):
    one_count = bin(n).count('1')
    large_one_count = 0
    while one_count != large_one_count:
        n += 1
        large_one_count = bin(n).count('1')
    return n