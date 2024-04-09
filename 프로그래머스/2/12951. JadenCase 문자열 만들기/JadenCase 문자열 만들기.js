function solution(s) {
    const arr = Array.from(s);
    let answer = '';
    let isFirst = true;
    
    arr.forEach((value) => {
        if (value === ' ') { // 공백이면
            answer += value;
            isFirst = true;
        } else if (!isNaN(value)) { // 숫자면 (공백도 숫자로 취급하므로 조심!)
            answer += value;
            isFirst = false;
        } else if (value === value.toUpperCase()) { // 대문자면
            answer += isFirst ? value : value.toLowerCase();
            isFirst = false;
        } else { // 소문자면
            answer += isFirst ? value.toUpperCase() : value;
            isFirst = false;
        }
    });
    
    return answer;
}