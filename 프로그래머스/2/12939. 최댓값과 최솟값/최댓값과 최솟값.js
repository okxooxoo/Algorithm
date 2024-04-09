function solution(s) {
    const numbers = s.split(' ').map(Number);
    let answer = '';
    answer += Math.min(...numbers);
    answer += ' ';
    answer += Math.max(...numbers);
    return answer;
}