def solution(s):
    answer = [0, 0]
    
    while (s != '1'):
        length_one = s.count('1')
        answer[1] += len(s) - length_one # 제거된 0의 개수 갱신

        s = bin(length_one)[2:]
        answer[0] += 1 # 이진 변환의 횟수 갱신

    return answer