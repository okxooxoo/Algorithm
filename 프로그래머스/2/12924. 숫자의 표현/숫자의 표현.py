def solution(n):
    result = 1
    for i in range(1, n // 2 + 1):
        s = 0
        while s < n:
            s += i
            if s == n:
                result += 1
                break
            i += 1
    return result