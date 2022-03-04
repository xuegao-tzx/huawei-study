#include<stdio.h>
#include<stdlib.h>
#define OK 1
#define OVERFLOW -1

typedef int Status;
typedef char TElemType;
typedef struct BiTNode
{
TElemType data;
struct BiTNode *lchild;
struct BiTNode *rchild;
}BiTNode;
typedef BiTNode *BiTree;

Status Create(BiTree &T)/*构建二叉树*/
{
	char ch;
	ch=getchar();
	if(ch=='*') T=NULL;
	else 
	{
		if (!(T=(BiTNode *)malloc(sizeof(BiTNode))))exit(OVERFLOW);
		T->data=ch; 
		Create(T->lchild); 
		Create(T->rchild); 
	}
	return OK;
} 
void Display(TElemType& e){printf("%c\t",e);}
void PreOrder(BiTree T, void(*visit)(TElemType& e))/*先序遍历*/
{
	if (T==NULL) return;
	visit(T->data);
	PreOrder(T->lchild,visit);
	PreOrder(T->rchild,visit);
}
void InOrder(BiTree T, void(*visit)(TElemType& e))/*中序遍历*/
{ 
	if (T==NULL) return;
	InOrder(T->lchild,visit);
	visit(T->data);
	InOrder(T->rchild,visit);
}
int k=0,top=-1;
void push(BiTNode** a,BiTNode* elem){a[++top]=elem;}/*前序和中序遍历使用的进栈函数*/
void pop( )/*弹栈函数*/
{
    if(top==-1)return;
    top--;
}
void displayElem(BiTNode* elem){printf("%c       ",elem->data);}/*模拟操作结点元素的函数，输出结点本身的数值*/
BiTNode* getTop(BiTNode**a){return a[top];}/*拿到栈顶元素*/
void InOrderf(BiTree Tree)/*中序遍历非递归算法*/
{
    BiTNode* a[20];
    BiTNode * p;
    push(a,Tree);
    while (top!=-1) 
	{
        while((p=getTop(a))&&p){push(a,p->lchild);}
        pop();
        if (top!=-1) 
		{
            p=getTop(a);
            pop();
            displayElem(p);
            push(a,p->rchild);
        }
    }
}
void PostOrder(BiTree T, void(*visit)(TElemType& e))/*后序遍历*/
{
	if(T==NULL)return;
	PostOrder(T->lchild,visit);
	PostOrder(T->rchild,visit);
	visit(T->data);
}
int BiTreeHeight(BiTree T)/*运用递归求二叉树的高度*/
{
	int LH,RH;
	if(!T)return 0;
	else
	{
		LH=BiTreeHeight(T->lchild);
		RH=BiTreeHeight(T->rchild);
		if(LH>RH)return(LH+1);
		else return(RH+1);
	}
} 
int leaves(BiTree T)/*求叶子结点数目*/
{
	if(!T)return 0;
    else
	{
		if (T->lchild==NULL&&T->rchild==NULL) k++;
		else 
		{
	   		if(T->lchild)leaves(T->lchild);
	   		if(T->rchild)leaves(T->rchild);
		}
	}	   	   
	return(k);
}

int main()
{
	BiTree R;
	printf("输入二叉树的先序遍历结点，(*为空)建立二叉树：\n");
	Create(R);
	printf("\n先序遍历递归算法结果：\n");
	PreOrder(R,Display);
	printf("\n中序遍历递归算法结果：\n");
	InOrder(R,Display);
	printf("\n中序遍历非递归算法结果：\n");
	InOrderf(R);
	printf("\n后序遍历递归算法结果：\n");
	PostOrder(R,Display);
	printf("\n二叉树的高度：%d\n",BiTreeHeight(R));
	printf("\n二叉树结点的个数：%d\n",leaves(R));
	return 0;
}
