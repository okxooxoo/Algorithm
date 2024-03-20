def solution(nums):
    pokemon = []
    for num in nums:
        if num not in pokemon:
            pokemon.append(num)
    return min(len(pokemon), len(nums) // 2)