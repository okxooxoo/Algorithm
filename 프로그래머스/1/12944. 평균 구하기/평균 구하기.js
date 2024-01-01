function solution(arr) {
    let sum = 0;
    arr.forEach((value) => {
        sum += value;
    });
    let answer = sum / arr.length;
    return answer;
}