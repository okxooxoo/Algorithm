def solution(triangle):
    # 두 번째 줄부터 마지막 줄까지
    for i in range(1, len(triangle)):
        # 그 줄의 첫 번째 숫자
        triangle[i][0] += triangle[i-1][0]
        # 그 줄의 중간 숫자
        for j in range(1, i):
            triangle[i][j] += max(triangle[i-1][j-1], triangle[i-1][j])
        # 그 줄의 마지막 숫자
        triangle[i][i] += triangle[i-1][i-1]
    mx = max(triangle[-1])
    return mx