N = int(input())
M = int(input()) # disable의 길이
if M > 0:
    disable = list(input().split())
else:
    disable = []

def is_valid_number(num):
    for n in str(num):
        if n in disable:
            return False
    return True

def get_down_count(num):
    while True:
        if is_valid_number(num):
            return abs(N - num) + len(str(num))
        if num < 0:
            return 500000
        num -= 1

def get_up_count(num):
    while True:
        if is_valid_number(num):
            return abs(N - num) + len(str(num))
        num += 1

# 모든 버튼이 고장난 경우
if len(disable) == 10:
    result = abs(N - 100)
# 0을 제외한 모든 버튼이 고장난 경우
elif len(disable) == 9 and '0' not in disable:
    result = min(abs(N - 100), N + 1)
else:
    result = min(abs(N - 100), get_down_count(N),  get_up_count(N))
print(result)