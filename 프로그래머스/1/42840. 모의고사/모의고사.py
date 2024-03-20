def solution(answers):
    first = [1, 2, 3, 4, 5]
    second = [2, 1, 2, 3, 2, 4, 2, 5]
    third = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    counts = [0, 0, 0]
    for i in range(len(answers)):
        if first[i % 5] == answers[i]:
            counts[0] += 1
        if second[i % 8] == answers[i]:
            counts[1] += 1
        if third[i % 10] == answers[i]:
            counts[2] += 1
    mx = max(counts)
    idx = []
    for i in range(len(counts)):
        if counts[i] == mx:
            idx.append(i+1)
    return idx