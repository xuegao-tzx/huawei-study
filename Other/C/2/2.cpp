#include<stdio.h>
#include<stdlib.h>
#define OK 1
#define ERROR 0

typedef int Status;
typedef float ElemType;
struct LNode {
  ElemType data;
  struct LNode *next;
}; 
typedef struct LNode LNode; 
typedef LNode *LinkList;

Status CreateList(LinkList &L, int n)
{
	L=(LinkList)malloc(sizeof(LNode));
	L->next=NULL;  
	LinkList p;
	int i;
	for(i=0;i<n;i++)
	{
		p=(LinkList)malloc(sizeof(LNode));
		scanf("%f",&p->data);
		p->next=L->next ;
		L->next=p;
	}
}

Status GetElem(LinkList L, int i,ElemType &e)
{ 
	LNode *p;int j;
	p=L->next; 
	j=1; 
	while(p->next&&j<i) 
	{ p=p->next; ++j; }
	if(!p||j>i) return ERROR;
	e=p->data;
	return OK;
}

Status ListInsert(LinkList L, int i, ElemType e)
{
	LNode *p,*s;int j;
	p=L; j=0;
	while(p&&j<i-1) { p=p->next; ++j; } 
	if (!p||j>i-1) return ERROR; 
	s = ( LinkList ) malloc ( sizeof (LNode) ); 
	s->data=e; 
	s->next=p->next; 
	p->next=s; 
	return OK;
}

Status ListDelete(LinkList &L,int i,ElemType &e)
{
	LNode *p,*q;int j=0;p=L;
	while( p->next && j<i-1 ) { p=p->next; ++j; } 
	if (!p||j>i-1) return ERROR; 
	q=p->next;  
	p->next=q->next;  
	e=q->data; 
	free (q);   
	return OK;
} 

Status PrintList(LinkList L)
{
	LinkList p;
	p=L->next ;
	while(p!=NULL)
	{
		printf("%f	",p->data);
		p=p->next;
	}
}

main()
{
	LinkList L;
	Status i,n,k;
	ElemType e;
	printf("请输入数据的个数:\n");
	scanf("%d",&n);
	printf("请逆顺序输入元素的值:\n");
	CreateList(L,n);
	printf("请输入插入元素位置及元素:\n");
	scanf("%d %f",&i,&e);
	ListInsert(L,i,e);
	PrintList(L);
	printf("\n");
	printf("请输入删除元素位置:\n");
	scanf("%d",&i);
	if(0<i&&i<=(n+1)) 
	{
		ListDelete(L,i,e);
		printf("删除元素为:%f\n",e);
		printf("请输入查找元素位置:\n");
		scanf("%d",&i);
		if(0<i&&i<=n) 
		{
			GetElem(L,i,e);
			printf("所查找元素的值为：%f\n",e);
		}
		else	printf("无法查找元素!\n");
	}
	else 
	{
		printf("无法删除元素!\n");
		printf("请输入查找元素位置:\n");
		scanf("%d",&i);
		if(0<i&&i<=n+1) 
		{
			
			GetElem(L,i,e);
			printf("所查找元素的值为：%f\n",e);
		}
		else	printf("无法查找元素!\n");
	}
	PrintList(L);
	printf("\n");	
	printf("单链表为:");
	PrintList(L);	
} 

