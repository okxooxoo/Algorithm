function solution(n) {
    const arr = [];
    while (n > 0) {
        arr.push(n % 10);
        n = parseInt(n / 10);
    }
    return arr;
}