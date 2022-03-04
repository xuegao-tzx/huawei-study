#include<stdio.h>
#include<stdlib.h>
#define  MAXSIZE  100
using namespace std;				
typedef struct
{
	int key;
	char *otherinfo;
}ElemType;  
typedef struct
{
    ElemType *r;	         						
    int  length;            					
}SqList;											
void BubbleSort(SqList L)
{
	int m,j,flag;
	ElemType t;
    m=L.length-1; flag=1; 				
    while((m>0)&&(flag==1))
	{
		flag=0;           			
        for(j=1;j<=m;j++)
		if(L.r[j].key>L.r[j+1].key) 
		{
			flag=1;					
			t=L.r[j];L.r[j]=L.r[j+1];L.r[j+1]=t;	
		}							
		--m;
    }								
}									
void SelectSort(SqList L) 
{ 
	int i,j,k;
	ElemType t;
    for(i=1;i<L.length;++i) 
	{  											
		k=i;                 
        for(j=i+1;j<=L.length;++j)
		if(L.r[j].key<L.r[k].key)  k=j;		
		if(k!=i) {t=L.r[i];L.r[i]=L.r[k];L.r[k]=t;}      
    }												
}		
void InsertSort(SqList L)
{
	int i,j;
	for(i=2;i<=L.length;++i)
		if(L.r[i].key<L.r[i-1].key)
		{   										
			L.r[0]=L.r[i];				 			
            L.r[i]=L.r[i-1];	            		
            for(j=i-2; L.r[0].key<L.r[j].key;--j)		
			L.r[j+1]=L.r[j];				
            L.r[j+1]=L.r[0];					
		}											
}												
int Partition(SqList L,int low,int high)
{ 
	int pivotkey;
	L.r[0]=L.r[low];                    	
	pivotkey=L.r[low].key;		   		
	while(low<high)
	{									
		while(low<high&&L.r[high].key>=pivotkey) --high;
		L.r[low]=L.r[high];				
		while(low<high&&L.r[low].key<=pivotkey) ++low;
		L.r[high]=L.r[low];					
	}
	L.r[low]=L.r[0];						
	return  low;							
}
void QSort(SqList L,int low,int high)
{
	int pivotloc;
    if(low<high)
	{										
       pivotloc=Partition(L,low,high); 		
       QSort(L,low,pivotloc-1);				
       QSort(L,pivotloc+1,high);        	
	}
}											
void QuickSort(SqList L)
{
   QSort(L,1,L.length);
}	
int Create_Sq(SqList *L)
{
	int i,n;
	printf("请输入你要创建的表的长度：\n");
	scanf("%d",&n);
	L->r =(ElemType *)malloc((n+1)*sizeof(ElemType));
	if(!L->r )
	return 0;
	printf("请输入%d个数据.\n",n);
	for(i=1;i<=n;i++)
	{
		scanf("%d",&L->r[i].key);
	}
	L->length =n;
	return 1;
} 
void show(SqList &L)
{
	int i;
	for(i=1;i<=L.length;i++)
	printf("%d",L.r[i].key);
}
int main()
{
	int j; 
	SqList L;
	L.r=new ElemType[MAXSIZE+1];
	L.length=0;
	Create_Sq(&L);			
    while(1)
	{
		printf("\n请选择:\n");
		printf("1.冒泡排序	2.简单选择	3.直接插入	4.快速排序	5.退出程序\n");
		scanf("%d",&j);
		switch(j)
        {
            case 1:	
				SelectSort(L);
				printf("1.冒泡排序:\n");
				show(L);
				break;
            case 2:
				BubbleSort(L);
				printf("2.简单选择:\n");
				show(L);
				break;
            case 3:
				InsertSort(L);
				printf("3.直接插入:\n");
				show(L);
				break;
			case 4:	
				QuickSort(L);
				printf("4.快速排序:\n");
				show(L);
				break; 
			case 5:
				exit(0);	
        }
    }
}

