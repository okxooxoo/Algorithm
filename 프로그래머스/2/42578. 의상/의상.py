def solution(clothes):
    hash_table = {}
    # 옷의 종류에 따른 개수 저장
    for name, kind in clothes:
        hash_table[kind] = hash_table.get(kind, 0) + 1
    answer = 1
    for kind, num in hash_table.items():
        answer *= num + 1
    return answer - 1