function solution(A,B){
    A.sort((a, b) => a - b);
    B.sort((a, b) => b - a);
    
    let sum = A.reduce((acc, value, index) => acc + value * B[index], 0);

    return sum;
}