N = int(input())
fruits = list(map(int, input().split()))
fruits_count = dict()
mx = 0
# 투포인터
left = 0
for right in range(N):
    fruits_count[fruits[right]] = 1 + fruits_count.get(fruits[right], 0)
    
    # 과일의 종류가 2개 초과면
    while len(fruits_count) > 2:
        fruits_count[fruits[left]] -= 1
        if fruits_count[fruits[left]] == 0:
            del fruits_count[fruits[left]]
        left += 1
    mx = max(mx, right-left+1)

print(mx)