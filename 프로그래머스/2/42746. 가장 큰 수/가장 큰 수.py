def solution(numbers):
    # 숫자를 문자열로 변환
    answer = list(map(str, numbers))
    # 숫자의 범위가 1000 이하이므로 3번 반복한 숫자를 비교
    answer.sort(key=lambda x: x*3, reverse=True)
    if answer[0] == '0':
        return '0'
    return ''.join(answer)
