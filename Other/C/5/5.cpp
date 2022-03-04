#include<stdio.h>
#include<stdlib.h>
#define EQ(a,b) ((a)==(b))
#define LT(a,b) ((a)<(b))
#define LQ(a,b) ((a)<=(b))
typedef int KeyType;
typedef struct{
KeyType key;
}ElemType;
typedef struct{
	ElemType *elem; 
	int length;
}SSTable;
int create(SSTable *ST)
{
	int i,n;
	printf("请输入你要创建的表的长度：\n");
	scanf("%d",&n);
	ST->elem =(ElemType *)malloc((n+1)*sizeof(ElemType));
	if(!ST->elem )
	return 0;
	printf("请输入%d个数据(若采用二分法查找关键字，则顺序输入).\n",n);
	for(i=1;i<=n;i++)
	{
		scanf("%d",&ST->elem [i].key);
	}
	ST->length =n;
	return 1;
} 
int search1(SSTable ST,KeyType key)
{
	int i=1;
	ElemType *p;
	while(i<=ST.length&&!EQ(ST.elem[i].key,key))
	++i;
	if(i<=ST.length) return i;
	else return 0;
}
int search2(SSTable ST,KeyType key)
{
	int i;
	ST.elem[0].key=key;
	for(i=ST.length;!EQ(ST.elem[i].key,key);--i);
	return i;
}
int search3(SSTable ST,KeyType key)
{
	int low=1;
	int high=ST.length;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(EQ(key,ST.elem[mid].key))
		return mid;
		else if(LT(key,ST.elem[mid].key)) high=mid-1;
		else low=mid+1;
	}
	return 0;
}
int Destroy(SSTable& ST)
{
	free(ST.elem);
	ST.elem=NULL;
	ST.length=0;
	return 1;
}
int main()
{
	int i,T;
	SSTable ST;
	KeyType key;  
	printf("请输入创建表的类型(0为无序表,1为有序表):\n");
	scanf("%d",&T);
	create(&ST); 
	printf("查找表创建成功!\n");
	printf("请输入需查找的关键字：\n");
	scanf("%d",&key);
	if(T==0)
	{
		printf("顺序查找关键字结果为:\n");
		i=search1(ST,key);
		if(i==0) printf("查找失败!表中无此元素!\n");
		else printf("查找成功!位置是%d\n",i);
		printf("改进算法顺序查找关键字结果为：\n");
		i=search2(ST,key);
		if(i==0) printf("查找失败!表中无此元素!\n");
		else printf("查找成功!位置是%d\n",i);
	}
	else
	{
		printf("二分查找关键字结果为：\n");
		i=search3(ST,key);
		if(i==0) printf("查找失败!表中无此元素!\n");
		else printf("查找成功!位置是%d\n",i);
	}
	if(Destroy(ST)) printf("销毁查找表成功！\n");
}
