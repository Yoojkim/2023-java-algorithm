n=int(input())

lis=list(map(int,input().split())) 

n_list=[]

for i in range (n):
    n_list.append(lis[i])

n_list.sort()

print(n_list[0],n_list[n-1])