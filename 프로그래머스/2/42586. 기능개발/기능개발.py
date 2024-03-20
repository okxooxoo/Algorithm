def solution(progresses, speeds):
    answer = []
    # 스택의 상단은 가장 먼저 배포되어야 하는 작업
    progresses.reverse()
    speeds.reverse()
    
    while progresses:
        for i in range(len(progresses)):
            progresses[i] += speeds[i]
        # 하루의 끝에 배포    
        count = 0
        while progresses and progresses[-1] >= 100:
            count += 1
            progresses.pop()
            speeds.pop()
        if count > 0:
            answer.append(count)
        
    return answer