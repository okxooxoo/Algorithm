import sys
input = sys.stdin.readline

N = int(input())
numbers = []
for _ in range(N):
    numbers.append(int(input()))
    
# 병합 정렬
def merge_sort(arr):
    if len(arr) < 2:
        return arr # 배열을 더 쪼갤 수 없으므로 바로 리턴
    
    mid = len(arr) // 2 # 중앙값의 인덱스
    left_arr = merge_sort(arr[:mid])
    right_arr = merge_sort(arr[mid:])
    
    return merge(left_arr, right_arr)

# 두 배열의 가장 작은 수 중 더 작은 값을 찾아서 배열에 삽입
# 위의 과정을 반복하여 오름차순으로 두 배열 병합
def merge(left_arr, right_arr):
    i = 0 # left_arr의 인덱스
    j = 0 # right_arr의 인덱스
    merged_arr = []
    
    while i < len(left_arr) and j < len(right_arr):
        if left_arr[i] < right_arr[j]:
            merged_arr.append(left_arr[i])
            i += 1
        else:
            merged_arr.append(right_arr[j])
            j += 1
    merged_arr += left_arr[i:] # 남은 수 한번에 삽입
    merged_arr += right_arr[j:] # 남은 수 한번에 삽입
    
    return merged_arr

for number in merge_sort(numbers):
    print(number)