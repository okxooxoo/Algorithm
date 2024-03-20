def solution(n, lost, reserve):
    # 본인이 본인에게 빌려주는 경우 제거
    realLost = [student for student in lost if student not in reserve]
    realReserve = [student for student in reserve if student not in lost]
    realReserve.sort()
    
    for number in realReserve:
        if number-1 in realLost:
            realLost.remove(number-1)
        elif number+1 in realLost:
            realLost.remove(number+1)
            
    return n - len(realLost)