def solution(participant, completion):
    count = {}
    for runner in participant:
        # key 중 runner가 존재하지 않으면 0 반환
        count[runner] = count.get(runner, 0) + 1
    for runner in completion:
        count[runner] -= 1
    for key, value in count.items():
        if value == 1:
            return key
    