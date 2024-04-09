function solution(A,B){
    A.sort((a, b) => a - b);
    B.sort((a, b) => b - a);
    
    sum = 0;
    for (let i = 0; i < A.length; i++) {
        sum += A[i] * B[i];
    }

    return sum;
}