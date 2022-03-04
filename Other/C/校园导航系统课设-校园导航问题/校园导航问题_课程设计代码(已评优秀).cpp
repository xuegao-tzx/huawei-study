#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>
#define JD 50
#define INF 999999
typedef struct {
	char name[JD];/*定义地点名称*/ 
	char xinxi[1000];/*定义地点信息*/ 
} VertexType;/*地图信息的定义*/ 
typedef struct {
	VertexType vexs[JD];
	int edges[JD][JD];
	int v,e;
}MGraph;/*地图（无向图）的边和节点的定义*/ 
static MGraph M;
void setColor();
void create();
void showjd();
void Showlj();
void jiemian();
void Introduce();
void Changejd();
void Increasejd();
void Deletejd();
void Increaselj();
void Deletelj();
void Zdlj();
void Zdlj_2();
int main(){
	create();
	while(1){
		system("cls");
		jiemian();
	}
	return 0;
}
void setColor(unsigned short ForeColor,unsigned short BackGroundColor){
	HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleTextAttribute(handle, ForeColor + BackGroundColor * 0x10);
}/*设置输出界面的颜色的函数*/ 
void jiemian(){
	setColor(9, 15);
	system("cls");
	int n=0;
	printf("\n\n		     欢迎光临中北大学主校区校园导航中心！    \n\n");
	printf("\n		            请选择您要进行的操作：           \n");
	printf("\n		        1.查看已有地点                       \n");
	printf("		        2.查看已有道路                       \n");
	printf("		        3.修改已有地点                       \n");
	printf("		        4.增加新的地点                       \n");
	printf("		        5.删除现有地点                       \n");
	printf("		        6.增加新的道路                       \n");
	printf("		        7.删除现有道路                       \n");
	printf("		        8.查找当前地点到其它地点的最短路径   \n");
	printf("		        9.查找任一的两个地点之间的最短路径   \n");
	printf("		        10.退出系统！                        \n");
	printf("\n		            注意:请务必输入整型数字！            \n\n");
	scanf("%d",&n);
	getchar();
	switch(n){
		case 1: Introduce(); break;
		case 2: Showlj(); break;
		case 3: Changejd(); break;
		case 4: Increasejd(); break;
		case 5: Deletejd(); break;
		case 6: Increaselj(); break;
		case 7: Deletelj(); break;
		case 8: Zdlj(); break;
		case 9: Zdlj_2(); break;
		case 10: system("cls"); printf("退出成功！\n"); exit(0);
		default: printf("您的输入有误，请重新输入！\n"); system("pause"); break;
	}
}
void create() {
	M.v=15;//已录入的地点数量
	M.e=23;//已录入地点的道路的数量
	int i,j,k;
	for(i=0; i<JD; i++){
		for(j=0; j<JD; j++){
			M.edges[i][j]=INF;
		}
	}
	strcpy(M.vexs[0].name,"校门区");
	strcpy(M.vexs[0].xinxi,"包括中北大学的正门一道门，不过基本上只行车，不走人;还有德怀楼，德怀广场，听力课也在这儿上");
	strcpy(M.vexs[1].name,"体育活动场所");
	strcpy(M.vexs[1].xinxi,"包括一个大的标准足球场和乒乓球馆，乒乓球课在此处上课");
	strcpy(M.vexs[2].name,"校医院");
	strcpy(M.vexs[2].xinxi,"打疫苗、体检以及生病时来这儿");
	strcpy(M.vexs[3].name,"科艺苑");
	strcpy(M.vexs[3].xinxi,"文艺表演的地方");
	strcpy(M.vexs[4].name,"餐厅一");
	strcpy(M.vexs[4].xinxi,"饭品种多，但价格也随之上涨，有超市、药店、唯一一个银行、杂货店");
	strcpy(M.vexs[5].name,"主操场");
	strcpy(M.vexs[5].xinxi,"最大的一个标准化操场，有真草坪的足球场，适合锻炼身体");
	strcpy(M.vexs[6].name,"图书馆");
	strcpy(M.vexs[6].xinxi,"书非常多，分专业存放，很好的自习场所");
	strcpy(M.vexs[7].name,"主教学楼区");
	strcpy(M.vexs[7].xinxi,"楼顶有中北大学四个大字的楼，也就是11号教学楼，很多课在这儿上，空教室也很多，楼前有一个大广场（行知广场），很多活动在这儿举行");
	strcpy(M.vexs[8].name,"软院楼");
	strcpy(M.vexs[8].xinxi,"今年新有的软件学院牌子，楼不大，但里面人才不少哦");
	strcpy(M.vexs[9].name,"9号楼");
	strcpy(M.vexs[9].xinxi,"很多课也在这儿上，教室较多，理学院主要在这儿");
	strcpy(M.vexs[10].name,"14号楼");
	strcpy(M.vexs[10].xinxi,"四层楼大教室，在这儿的课少");
	strcpy(M.vexs[11].name,"10号楼");
	strcpy(M.vexs[11].xinxi,"暂时没来这儿上过课，只在这儿考过试，离宿舍最近的教学楼了");
	strcpy(M.vexs[12].name,"田园区");
	strcpy(M.vexs[12].xinxi,"包括文澜宿舍以及田园食堂，田园实验室，食堂吃的不错，至少便宜，食堂上是实验室，从这儿走出一个又一个的软件人才。");
	strcpy(M.vexs[13].name,"致学楼区");
	strcpy(M.vexs[13].xinxi,"包括致学广场和致学楼，有卖纪念品的地方，还有个小食堂");
	strcpy(M.vexs[14].name,"篮球场");
	strcpy(M.vexs[14].xinxi,"打篮球的地方");
	M.edges[0][1]=M.edges[1][0]=230;
	M.edges[0][3]=M.edges[3][0]=470;
	M.edges[1][7]=M.edges[7][1]=540;
	M.edges[2][3]=M.edges[3][2]=300;
	M.edges[2][7]=M.edges[7][2]=440;
	M.edges[3][4]=M.edges[4][3]=100;
	M.edges[3][6]=M.edges[6][3]=400;
	M.edges[4][5]=M.edges[5][4]=180;
	M.edges[5][6]=M.edges[6][5]=150;
	M.edges[5][7]=M.edges[7][5]=200;
	M.edges[6][7]=M.edges[7][6]=140;
	M.edges[7][8]=M.edges[8][7]=80;
	M.edges[7][9]=M.edges[9][7]=190;
	M.edges[8][10]=M.edges[10][8]=200;
	M.edges[8][13]=M.edges[13][8]=310;
	M.edges[9][10]=M.edges[10][9]=230;
	M.edges[10][11]=M.edges[11][10]=250;
	M.edges[10][13]=M.edges[13][10]=140;
	M.edges[10][14]=M.edges[14][10]=200;
	M.edges[11][12]=M.edges[12][11]=320;
	M.edges[11][13]=M.edges[13][11]=210;
	M.edges[11][14]=M.edges[14][11]=240;
	M.edges[12][13]=M.edges[13][12]=350;
}
void showjd(){
	if(M.v==0){
		printf("		当前导航图中没有地点！\n\n");
		system("pause");
		return ;
	}
	if(M.v>0){
		printf("			中北大学导航图当前有如下地点：  \n\n");
		for(int i=0; i<M.v; i++){
			printf("\t\t		<%d>%s		\n",i+1,M.vexs[i].name);
		}
	}
}/*展示图中所有节点*/ 
void Showlj(){
	system("cls");
	if(M.e<=0){
		printf("		当前导航图中没有道路！\n\n");
		system("pause");
		return ;
	}
	if(M.e>0){
		printf("			中北大学导航图当前有如下%d条道路：  \n\n",M.e);
		int a,b;
		for(a=0; a<M.v; a++){
			for(b=0; b<a; b++){
				if(M.edges[a][b]!=0){
					if(M.edges[a][b]!=INF){
						printf("\t		【%s】 <---> 【%s】,距离是%d米 \n",M.vexs[a].name,M.vexs[b].name,M.edges[a][b]);
					}
				}
			}
		}
	}
	system("pause");
}/*展示无向图中所有边*/ 
void Introduce(){
	system("cls");
	int n=0;
	if(M.v==0){
		printf("本导航图暂时无地点！\n\n");
		system("pause");
		return ;
	}
	showjd();
	printf("\n请输入你要查看的地点代号：\n");
	while(1){
		scanf("%d",&n);
		if(n<1||n>M.v) printf("您的输入有误，请重新输入！\n");
		else break;
	}
	printf("%s：",M.vexs[n-1].name);
	printf("%s\n",M.vexs[n-1].xinxi);
	system("pause");
}/*查看某一地点详情*/ 
void Changejd(){
	system("cls");
	int n;
	if(M.v<=0){
		printf("导航图中无地点，无法操作！\n");
		system("pause");
		return ;
	}
	showjd();
	printf("请输入您要修改的地点代号：\n");
	scanf("%d",&n);
	while(n<=0||n>M.v){
		printf("您的输入有误，请重新输入！\n");
		scanf("%d",&n);
	}
	char newName[50],newxinxi[1000];
	printf("该地点当前的名字是:\n\n【%s】\n\n请输入更改后的名字：\n",M.vexs[n-1].name);
	scanf("%s",newName);
	getchar();
	printf("该地点当前的介绍为：\n\n%s\n\n请输入更改后新的地点介绍：\n",M.vexs[n-1].xinxi);
	scanf("%s",newxinxi);
	getchar();
	strcpy(M.vexs[n-1].name,newName);
	strcpy(M.vexs[n-1].xinxi,newxinxi);
	printf("\n地点信息修改成功!\n");
	system("pause");
}/*改变地点详情*/ 
void Increasejd(){
	system("cls");
	int a,d,i,m=0;
	if(M.v>JD){
		printf("地点已达最大上限50个，当前无法添加景点！\n");
		system("pause");
		return ;
	}
	char newName[50],newxinxi[1000];
	printf("请输入您要添加的地点名：\n");
	scanf("%s",newName);
	getchar();
	printf("请输入【%s】地点的介绍，最多可输入200字：\n",newName);
	scanf("%s",newxinxi);
	getchar();
	showjd();
	printf("请输入新增地点的相邻地点个数:\n");
	scanf("%d",&m);
	while(m<0||m>M.v){
		printf("您的输入有误，请重新输入！\n");
		scanf("%d",&m);
	}
	for(i=0; i<m; i++){
		printf("请输入第%d个相邻地点的代号：\n",i+1);
		scanf("%d",&a);
		while(a<=0||a>M.v||M.edges[a-1][M.v]!=INF){
			if(a<=0||a>M.v) printf("您的输入有误，请重新输入！范围在1~%d之间。\n",M.v);
			if(M.edges[a-1][M.v]!=INF) printf("请不要输入重复的相邻地点，重新输入：\n");
			scanf("%d",&a);
		}
		printf("请输入【%s】与【%s】之间的距离：\n",newName,M.vexs[a-1].name);
		scanf("%d",&d);
		while(d<=0||d>=INF){
			printf("您输入的距离有误！请重新输入：\n");
			scanf("%d",&d);
		}
		M.edges[a-1][M.v]=M.edges[M.v][a-1]=d;
	}
	strcpy(M.vexs[M.v].name,newName);
	strcpy(M.vexs[M.v].xinxi,newxinxi);
	M.v++;
	M.e=M.e+m;
	printf("地点添加成功!\n");
	system("pause");
}/*增加新的节点*/ 
void Deletejd(){
	system("cls");
	int a,i,j,c=0;
	if(M.v<1){
		printf("导航图中无地点，无法删除！\n");
		system("pause");
		return ;
	}
	showjd();
	printf("请输入您要删除的地点编号：\n");
	scanf("%d",&a);
	while(a<1||a>M.v){
		printf("您的输入有误，请重新输入！范围在1~%d之间。\n",M.v);
		scanf("%d",&a);
	}
	printf("地点：【%s】正在删除...\n",M.vexs[a-1].name);
	for(i=0; i<M.v; i++){
		if(M.edges[a-1][i]!=INF) c++;
	}
	for(i=a-1; i<M.v; i++){
		M.vexs[i]=M.vexs[i+1];
	}
	for(i=0; i<M.v; i++){
		for(j=a-1; j<M.v; j++) M.edges[i][j]=M.edges[i][j+1];
	}
	for(i=0; i<M.v; i++){
		for(j=a-1; j<M.v; j++) M.edges[j][i]=M.edges[j+1][i];
	}
	M.v--;
	M.e=M.e-c;
	printf("地点删除成功！\n");
	system("pause");
}/*删除节点*/ 
void Increaselj(){
	system("cls");
	int a,b,d;
	if(M.v<=1){
		if(M.v==1){
			printf("导航图中只有一个地点，无法添加道路！\n");
			system("pause");
			return ;
		}else{
			printf("导航图中无地点，无法添加道路！\n");
			system("pause");
			return ;
		}
	}
	showjd();
	printf("当前导航图中含有%d条道路！\n",M.e);
	printf("请输入您要添加道路的两个地点代号，中间使用空格隔开：\n");
	scanf("%d %d",&a,&b);
	while(a<1||a>M.v||b<1||b>M.v||a==b){
		if(a==b) printf("您输入的地点代号相同，请重新输入！\n");
		else printf("您的输入有误，请重新输入！范围在1~%d之间。\n",M.v);
		scanf("%d %d",&a,&b);
	}
	if(M.edges[a-1][b-1]!=INF) printf("【%s】与【%s】之间已经存在一条道路，无需再次添加！\n",M.vexs[a-1].name,M.vexs[b-1].name);
	else{
		printf("请输入【%s】和【%s】之间道路的长度：\n",M.vexs[a-1].name,M.vexs[b-1].name);
		scanf("%d",&d);
		while(d<=0||d>=INF){
			printf("您输入的长度有误，请重新输入！\n");
			scanf("%d",&d);
		}
		M.edges[a-1][b-1]=M.edges[b-1][a-1]=d;
		M.e++;
		printf("道路添加成功！当前导航图中含有%d条道路！\n",M.e);
	}
	system("pause");
}/*增加新的边*/ 
void Deletelj(){
	int a,b;
	system("cls");
	if(M.v<=0){
		printf("导航图中无地点，无法删除！\n");
		system("pause");
		return ;
	}
	if(M.e<=0){
		printf("导航图中无道路，无法删除！\n");
		system("pause");
		return ;
	}
	showjd();
	printf("当前导航图中一共有%d条道路\n",M.e);
	printf("请输入您要删除的道路对应的两个地点代号，中间使用空格隔开：\n");
	scanf("%d %d",&a,&b);
	while(a<1||a>M.v||b<1||b>M.v||a==b){
		if(a==b) printf("您输入的地点代号相同，请重新输入！\n");
		else printf("您的输入有误，请重新输入！范围在1~%d之间，地点代号不同。\n",M.v);
		scanf("%d %d",&a,&b);
	}
	if(M.edges[a-1][b-1]>=INF) printf("%s与%s之间没有道路，无法删除！\n",M.vexs[a-1].name,M.vexs[b-1].name);
	else{
		printf("正在删除【%s】与【%s】之间的道路...\n",M.vexs[a-1].name,M.vexs[b-1].name);
		M.edges[a-1][b-1]=M.edges[b-1][a-1]=INF;
		M.e--;
		printf("道路删除成功！当前导航图中含有%d条道路！\n",M.e);
	}
	system("pause");
}/*删除边*/ 
void Zdlj(){
	system("cls");
	int a,i,j,k,min,pre;
	int b[JD],minl[JD],shanl[JD];/*存放原来的节点和已生成的终点、存放最短路径的长度、存放上一条路的位置*/ 
	if(M.v<=1){
		if(M.v==1){
			printf("导航图中只有一个地点，无法查询最短路径！\n");
			system("pause");
			return ;
		}else{
			printf("导航图中无地点，无法查询最短路径！\n");
			system("pause");
			return ;
		}
	}
	if(M.e<=0){
		printf("导航图中无道路，无法查询最短路径！\n");
		system("pause");
		return ;
	}
	showjd();
	printf("请输入您所在地点位置的代号：\n");
	scanf("%d",&a);
	while(a<1||a>M.v){
		printf("您的输入有误，请重新输入！范围在1~%d之间。\n",M.v);
		scanf("%d",&a);
	}
	b[a-1]=1;
	for(i=0; i<M.v; i++){
		minl[i]=M.edges[a-1][i];
		shanl[i]=a-1;
		b[i]=0;
	}
	minl[a-1]=0;
	b[a-1]=1;
	shanl[a-1]=-1;
	for(i=0; i<M.v; i++){
		min=INF+1;
		for(k=1; k<M.v; k++){
			if(b[k]==0&&minl[k]<min){
				j=k;
				min=minl[k];
			}
		}
		b[j]=1;
		for(k=0; k<M.v; k++){
			if(b[k]==0&&(minl[j]+M.edges[j][k]<minl[k])){
				minl[k]=minl[j]+M.edges[j][k];
				shanl[k]=j;
			}
		}
	}
	int flag=1;
	for(i=0; i<M.v; i++){
		if(i!=a-1){
			if(minl[i]!=INF){
				flag=0;
				printf("%d米: %s",minl[i],M.vexs[i].name);
				pre=shanl[i];
				while(pre>=0){
					printf("  <--->  %s",M.vexs[pre].name);
					pre=shanl[pre];
				}
				printf("\n");
			}
		}
	}
	if(flag!=0) printf("【%s】与任何地点之间都没有可通道路！\n",M.vexs[a-1].name);
	system("pause");
}/*迪杰斯特拉算法改*/ 
void Zdlj_2(){
	system("cls");
	int a,b,i,j,k,min,pre,s[JD],minl[JD],shanl[JD];/*存放原来的节点和已生成的终点、存放最短路径的长度、存放上一条路的位置*/ 
	if(M.v<=1){
		if(M.v==1){
			printf("导航图中只有一个地点，无法查询最短路径！\n");
			system("pause");
			return ;
		}else{
			printf("导航图中无地点，无法查询最短路径！\n");
			system("pause");
			return ;
		}
	}
	if(M.e<=0){
		printf("导航图中无道路，无法查询最短路径！\n");
		system("pause");
		return ;
	}
	showjd();
	printf("请输入您要查询距离的两个地点代号,中间用空格隔开：\n");
	scanf("%d %d",&a,&b);
	while(a<1||a>M.v||b<1||b>M.v){
		printf("您的输入有误，请重新输入！范围在1~%d之间。\n",M.v);
		scanf("%d %d",&a,&b);
	}
	s[a-1]=1;
	for(i=0; i<M.v; i++){
		minl[i]=M.edges[a-1][i];
		shanl[i]=a-1;
		s[i]=0;
	}
	minl[a-1]=0;
	s[a-1]=1;
	shanl[a-1]=-1;
	for(i=0; i<M.v; i++){
		min=INF+1;
		for(k=1; k<M.v; k++){
			if(s[k]==0&&minl[k]<min){
				j=k;
				min=minl[k];
			}
		}
		s[j]=1;
		for(k=0; k<M.v; k++){
			if(s[k]==0&&(minl[j]+M.edges[j][k]<minl[k])){
				minl[k]=minl[j]+M.edges[j][k];
				shanl[k]=j;
			}
		}
	}
	if(minl[b-1]==INF) printf("【%s】与【%s】地点之间没有可通道路！\n",M.vexs[a-1].name,M.vexs[b-1].name);
	else {
		printf("【%s】 <---> 【%s】之间的最短距离是%d米。 \n",M.vexs[b-1].name,M.vexs[a-1].name,minl[b-1]);
		pre=shanl[b-1];
		printf("路径为：%s",M.vexs[b-1].name);
		while(pre>=0){
			printf("  <--->  %s",M.vexs[pre].name);
			pre=shanl[pre];
		}
	}
	printf("\n");
	system("pause");
}/*迪杰斯特拉算法改版*/ 
