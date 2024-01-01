function solution(s){
    const str = s.toLowerCase();
    if (str.split('p').length === str.split('y').length) {
        return true;
    }
    return false;
}