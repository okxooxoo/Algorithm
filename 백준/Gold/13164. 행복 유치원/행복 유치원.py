# N = 원생의 수, K = 나누고자 하는 조의 개수
N, K = map(int, input().split())
height = list(map(int, input().split()))
height_differ = []

# 이웃한 원생 간 키의 차이 저장
for i in range(N - 1):
    height_differ.append(height[i + 1] - height[i])

# 인접한 두 원생의 키 차이가 크면 조를 나누기 위해 내림차순 정렬
height_differ.sort(reverse=True)

print(sum(height_differ[K-1:]))