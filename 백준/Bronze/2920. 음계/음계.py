inputs = list(map(int, input().split()))
if inputs == sorted(inputs):
    print('ascending')
elif inputs == sorted(inputs, reverse=True):
    print('descending')
else:
    print('mixed')