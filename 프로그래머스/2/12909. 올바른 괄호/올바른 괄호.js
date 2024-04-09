function solution(s) {
    let count = 0;
    for (p of s) {
        if (count < 0) return false;
        if (p === '(')
            count++;
        else
            count--;
    }
    
    return count === 0;
}