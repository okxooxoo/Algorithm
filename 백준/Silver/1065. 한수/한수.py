h=int(input())
def hansu(n) :
    if 0<n<100 :
        m=n
    else :
        count=0
        for i in range(100,n+1) :
            a=i//100
            b=(i-a*100)//10
            c=i%10
            if (a+c)/2==b :
                count+=1
        m=99+count
    return m
print(hansu(h))