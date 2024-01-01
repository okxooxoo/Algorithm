function solution(n) {
    if (n % 2 === 1) return 2;
    for (let x = 3; x < n; x++) {
        if (n % x === 1) return x;
    }
    return answer;
}