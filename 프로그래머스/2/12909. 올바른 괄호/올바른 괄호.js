function solution(s) {
    const stack = [];
    for (p of s) {
        if (p === '(') {
            stack.push('(');
        } else if (stack.length > 0 && stack[stack.length - 1] === '(') {
            stack.pop();
        } else {
            return false;
        }
    }
    
    if (stack.length === 0)
        return true;
    else
        return false;
}