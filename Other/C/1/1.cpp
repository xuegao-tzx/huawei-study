#include <stdio.h>
#include <stdlib.h>
#define OK 1
#define ERROR 0
#define OVERFLOW -2

typedef int Status; 
typedef float ElemType; 
typedef ElemType* Triplet;

Status initTriplet(Triplet &T,ElemType v0,ElemType v1,ElemType v2)
{
	T = (ElemType*)malloc(3*sizeof(ElemType)); 
	if(!T) exit (OVERFLOW);
	T[0]=v0; T[1]=v1; T[2]=v2;
	return OK;
}
Status DestroyTriplet(Triplet &T)
{
	free(T); T=NULL;
	return OK;
}
Status getElem(Triplet T,Status i,ElemType &e)
{
	if(i<1||i>3) return ERROR;
	else e=T[i-1];
	return OK;
}
Status putElem(Triplet &T,Status i,ElemType e)
{
	if(i<1||i>3) return ERROR;
	else T[i-1]=e;
	return OK;
}
Status isAscending(Triplet T)
{	
	return (T[0]<=T[1])&&(T[1]<=T[2]);
} 
Status isDescending(Triplet T)
{	
	return (T[0]>=T[1])&&(T[1]>=T[2]);
} 
Status isqt(Triplet T)
{
	return (T[1]<T[2]);
}
Status isqt1(Triplet T)
{
	return (T[1]>T[2]);
}
ElemType getMax(Triplet T,ElemType &e)
{
	if(T[0]>T[1]) e=T[0];
	else e=T[1];
	if(T[2]>e) e=T[2];
	return e;
}
ElemType getMin(Triplet T,ElemType &e)
{
	if(T[0]<T[1]) e=T[0];
	else e=T[1];
	if(T[2]<e) e=T[2];
	return e;
}
Status main()
{
	Triplet T;
	Status flag;
	ElemType  v0,v1,v2,e;
	printf("请输入三元组的三个值v0,v1,v2:\n"); 
	scanf("%f,%f,%f",&v0,&v1,&v2);
	flag=initTriplet(T,v0,v1,v2);
	printf("调用初始化函数后,flag=%d,T的三个值为%4.2f,%4.2f,%4.2f\n",flag,T[0],T[1],T[2]);
	if(isAscending(T))
	{
		if(isDescending(T)) printf("该三元组元素均相等,");
		else printf("该三元组元素为升序,");
	}
	else 
	{  
		if(isqt(T)) printf("该三元组元素即不升序也不降序,");
	    else if(isqt1(T)) printf("该三元组元素即不升序也不降序,");
		else printf("该三元组元素为降序,");
	}
	printf("且该三元组中的最大值为：%4.2f,最小值为：%4.2f",getMax(T,e),getMin(T,e));
	DestroyTriplet(T);
	return OK;
}
