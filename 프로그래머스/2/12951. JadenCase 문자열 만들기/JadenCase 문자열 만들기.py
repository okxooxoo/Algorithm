def solution(s):
    answer = list(s) # 문자열 변경을 위함
    isFirst = True
    for i in range(len(answer)):
        if answer[i].isalpha():
            if isFirst:
                answer[i] = answer[i].upper()
                isFirst = False
            else:
                answer[i] = answer[i].lower()
        elif answer[i] == ' ':
            isFirst = True
        else:
            isFirst = False
    return ''.join(answer)