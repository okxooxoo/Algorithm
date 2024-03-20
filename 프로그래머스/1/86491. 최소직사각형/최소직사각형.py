def solution(sizes):
    w = []
    h = []
    for x, y in sizes:
        if x < y:
            x, y = y, x
        w.append(x)
        h.append(y)
    answer = max(w) * max(h)
    return answer