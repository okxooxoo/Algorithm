# 노래 개수, 노래 길이, 전화벨이 울리는 간격
song_num, song_length, bell_gap = map(int, input().split())

time = 0
total_time = (song_num - 1) * (song_length + 5) + song_length

while time < total_time:
    play_song = False
    ring_bell = False

    # 현재 노래가 재생되고 있는지 확인
    if time % (song_length + 5) < song_length:
        play_song = True

    # 현재 전화벨이 울리고 있는지 확인
    if time % bell_gap == 0:
        ring_bell = True

    if not play_song and ring_bell:
        break
    time += 1

if time == total_time:
    while True:
        if time % bell_gap == 0:
            print(time)
            break
        time += 1
else:
    print(time)