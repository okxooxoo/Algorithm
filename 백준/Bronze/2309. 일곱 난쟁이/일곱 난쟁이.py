def findDwarf(heights):
    s = sum(heights)
    for i in range(len(heights) - 1):
        for j in range(i + 1, len(heights)):
            if s - heights[i] - heights[j] == 100:
                heights.pop(j)
                heights.pop(i)
                return sorted(heights)
heights = []
for _ in range(9):
    h = int(input())
    heights.append(h)
results = findDwarf(heights)
for result in results:
    print(result)