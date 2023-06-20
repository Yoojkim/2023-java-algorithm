#오름차순으로 정렬
n,k=input().split()
n=int(n); k=int(k)
scores=input().split()

for i in range(len(scores)):
    scores[i]=int(scores[i])

#퀵 정렬은 sys 안 쓰면 재귀 Error
def insertSort(res):
    for i in range(1,len(res)):
        for j in range(i,0,-1):
            if res[j]>res[j-1]:
                res[j-1],res[j]=res[j],res[j-1]
            else:
                break

insertSort(scores)

print(scores[k-1])