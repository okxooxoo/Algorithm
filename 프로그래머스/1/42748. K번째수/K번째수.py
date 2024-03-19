def solution(array, commands):
    answer = []
    for command in commands:
        i, j, k = command
        subArray = sorted(array[i-1 : j])
        answer.append(subArray[k-1])
    return answer