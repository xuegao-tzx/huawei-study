#include <stdio.h>   
#include <stdlib.h>
#define OVERFLOW -2          
#define OK 1                
#define ERROR 0
#define MAXQSIZE 100        
#define MAX_VERTEX_NUM  20   
typedef int QElemType;
typedef int Status;       
typedef float ElemType;       
typedef int VRType;
typedef int VertexType;
typedef struct ArcCell{
	VRType   adj; 
}ArcCell,AdjMatrix[MAX_VERTEX_NUM][MAX_VERTEX_NUM]; 
typedef struct{
	VertexType vexs[MAX_VERTEX_NUM];    
	AdjMatrix  arcs;     
	int vexnum,arcnum;    
}MGraph; 
typedef struct            
{
	QElemType *base;       
	int front;              
	int rear;              
}SqQueue; 
typedef enum { TRUE=1 , FALSE=0 } Boolean; 
int LocateVex(MGraph H, int v)
{  
	for(int i=0; i<=H.vexnum; ++i)
	if(H.vexs[i]==v) return i;  
	return -1; 
}
Status CreateGraph(MGraph &H)
{
	VertexType v1,v2;
	int i,j;
	printf("请依次输入无向图G的顶点数,弧数,用空格隔开:\n");
	scanf("%d %d",&H.vexnum,&H.arcnum);
	printf("请依次输入无向图G的顶点名称(仅限数字),用空格隔开:\n"); 
	for(int i=0;i<H.vexnum;++i) scanf("%d",&H.vexs[i]);   
	for(int i=0;i<H.vexnum;++i)
	for(int j=0;j<H.vexnum;++j) H.arcs[i][j].adj=0;
	printf("请依次输入无向图G每条弧依附的两顶点名称,用空格隔开,输完一组按回车.\n"); 
	for(int k=0;k<H.arcnum;++k)
	{ 
		scanf("%d",&v1);
        scanf("%d",&v2);
		i=LocateVex(H,v1);
		j=LocateVex(H,v2);    
		H.arcs[i][j].adj=1;
		H.arcs[j][i]=H.arcs[i][j];  
	}
	return OK; 
}
Status PrintAdjMatrix(MGraph H)
{ 
	for(int i=0;i<H.vexnum;i++)
	{
		for(int j=0;j<H.vexnum;j++)
		printf(" %3d ", H.arcs[i][j].adj);
		printf("\n\n");
		}  
}
int FirstAdjVex(MGraph H,VertexType v)
{
	int i,j=0,k;
	k=LocateVex(H,v);  
	for(i=0;i<H.vexnum;++i)
    if(H.arcs[k][i].adj!=j) return i;       
	return -1;             
}
int NextAdjVex(MGraph H,VertexType v,VertexType w)
{
	int i,j=0,k1,k2;
	k1=LocateVex(H,v);  
	k2=LocateVex(H,w);  
	for(i=k2+1;i<H.vexnum;++i)
	if(H.arcs[k1][i].adj!=j) return i;        
	return -1;              
}
Boolean visited[MAX_VERTEX_NUM];    
Status (*VisitFunc)(int v);         
Status Print(int v){  
	printf(" %3d ",v);
	return OK;
}
Status InitQueue(SqQueue &Q) 
{   
	if(!(Q.base=(QElemType *)malloc(sizeof(QElemType))))
	{  
		printf("内存分配失败，程序即将退出！\n");
  		exit(OVERFLOW);
	}
	Q.front=Q.rear=0;
	return OK;
}
Status QueueEmpty(SqQueue Q)
{   
	if(Q.rear==Q.front) return OK; 
	else return ERROR; 
}
Status EnQueue(SqQueue &Q,QElemType e)
{    
	if((Q.rear+1)%MAXQSIZE==Q.front)  
	return ERROR;
	Q.base[Q.rear]=e;
	Q.rear=(Q.rear+1)%MAXQSIZE;   
	return OK; 
}
Status DeQueue(SqQueue &Q,QElemType &e)
{   
	if(Q.front==Q.rear) 
    return ERROR;
	e=Q.base[Q.front];
	Q.front=(Q.front+1)%MAXQSIZE;  
	return OK; 
} 
void DFSTraverse(MGraph H,Status (*Visit)(int v))
{
	void DFS(MGraph H,int v);  
	VisitFunc=Visit;        
	for(int v=0;v<H.vexnum;++v) visited[v]=FALSE;    
	for(int v=0;v<H.vexnum;++v)
    if(!visited[v]) DFS(H,v);    
}
void DFS(MGraph H,int v)
{
	visited[v]=TRUE; 
	VisitFunc(H.vexs[v]); 
	for(int w=FirstAdjVex(H,H.vexs[v]);w>=0;w=NextAdjVex(H,H.vexs[v],H.vexs[w]))
	{  
		if(!visited[w]) DFS(H,w);   
	}
}

void BFSTraverse(MGraph H,Status (*Visit)(int v))
{
	SqQueue Q; 
	for(int v=0;v<H.vexnum;++v) visited[v]=FALSE;     
	InitQueue(Q);          
	for(int v=0;v<H.vexnum;++v)
	if(!visited[v])
	{        
		visited[v]=TRUE;  
		Visit(H.vexs[v]);   
		EnQueue(Q,v);    
		while(!QueueEmpty(Q))
		{    
    		DeQueue(Q,v);     
    		for(int w=FirstAdjVex(H,H.vexs[v]);w>=0;w=NextAdjVex(H,H.vexs[v],H.vexs[w]))
			{ 
				if(!visited[w])
				{  
					visited[w]=TRUE;  
					Visit(H.vexs[w]);   
				} 
			}
		} 
	}
}
int main(int argc,char *argv[])
{
	MGraph H; 
	CreateGraph(H);      
	printf(" 图的邻接矩阵：\n\n"); 
	PrintAdjMatrix(H);   
	printf("深度优先遍历结果：");
	DFSTraverse(H,Print);
	printf("\n广度优先遍历结果：");
	BFSTraverse(H,Print); 
	return 0;
} 
