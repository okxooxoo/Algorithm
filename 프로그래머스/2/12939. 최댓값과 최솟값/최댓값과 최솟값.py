def solution(s):
    lst = list(map(int, s.split()))
    answer = ''
    answer += str(min(lst))
    answer += ' '
    answer += str(max(lst))
    return answer