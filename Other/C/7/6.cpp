#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<windows.h>
#include<time.h>

int null_count();
void start_game();
void reset_game();
void move_left();
void move_right();
void move_up();
void move_down();
void refresh_show();
void add_rand_num();
void check_if_over();
int board[4][4];
int score = 0;
int best = 0;
int if_need_add_num;
int if_game_over;
int i;
int j;
int k;

int main() {
	start_game();
}
void start_game() {
	reset_game();
	char fr;
	while (1) {
		fr = _getch();
		if (if_game_over) {
			if (fr == 'y' || fr == 'Y') {
				reset_game();
				continue;
			}
			else if (fr == 'n' || fr == 'N') {
				return;
			}
			else continue;
		}
		if_need_add_num = 0;
		switch (fr) {
		case 'a':
		case 'A':
		case '4':	
			move_left();
			break;
		case 'd':
		case 'D':
		case '6':	
			move_right();
			break;
		case 'w':
		case 'W':
		case '8':	
			move_up();
			break;
		case 's':
		case 'S':
		case '2':	
			move_down();
			break;
		case 't': 
		case 'T':
			exit(0);
		}
		if (score >= best) {
			best = score;
		}
		if (if_need_add_num) {
			add_rand_num();
			refresh_show();
		}
	}
}
int null_count() {
	int n = 0;
	for (i = 0; i < 4; i++) {
		for (j = 0; j < 4; j++) {
			if (board[i][j] == 0) {
				n++;
			}
		}
	}
	return n;
}
void reset_game() {
	score = 0;
	if_need_add_num = 1;
	if_game_over = 0;
	srand(time(0));
	int n = rand() % 16;
	for (i = 0; i < 4; i++) {
		for (j = 0; j < 4; j++) {
			if (n == 0) {
				int k = rand() % 3;
				if (k == 0 || k == 1) {
					board[i][j] = 2;
				}
				else {
					board[i][j] = 4;
				}
				n--;
			}
			else {
				board[i][j] = 0;
				n--;
			}

		}
	}
	add_rand_num();
	system("cls");
	refresh_show();
}
void move_left() {
	for (i = 0; i < 4; i++) {
		for (j = 1, k = 0; j < 4; j++) {
			if (board[i][j] > 0) {
				if (board[i][k] == board[i][j]) {
					board[i][k] *= 2;
					k++;
					score = score + 2 * board[i][j];
					board[i][j] = 0;
					if_need_add_num = 1;
				}
				else if (board[i][k] == 0) {
					board[i][k] = board[i][j];
					board[i][j] = 0;
					if_need_add_num = 1;
				}
				else {
					board[i][k + 1] = board[i][j];
					if ((k + 1) != j) {
						board[i][j] = 0;
						if_need_add_num = 1;
					}
					k++;
				}
			}
		}
	}
}
void move_right() {
	for (i = 0; i < 4; i++) {
		for (j = 2, k = 3; j >= 0; j--) {
			if (board[i][j] > 0) {
				if (board[i][k] == board[i][j]) {
					board[i][k] *= 2;
					k--;
					score = score + 2 * board[i][j];
					board[i][j] = 0;
					if_need_add_num = 1;
				}
				else if (board[i][k] == 0) {
					board[i][k] = board[i][j];
					board[i][j] = 0;
					if_need_add_num = 1;
				}
				else {
					board[i][k - 1] = board[i][j];
					if ((k - 1) != j) {
						board[i][j] = 0;
						if_need_add_num = 1;
					}
					k--;
				}
			}
		}
	}
}
void move_up() {
	for (i = 0; i < 4; i++) {
		for (j = 1, k = 0; j < 4; j++) {
			if (board[j][i] > 0) {
				if (board[k][i] == board[j][i]) {
					board[k][i] *= 2;
					k++;
					score = score + 2 * board[j][i];
					board[j][i] = 0;
					if_need_add_num = 1;
				}
				else if (board[k][i] == 0) {
					board[k][i] = board[j][i];
					board[j][i] = 0;
					if_need_add_num = 1;
				}
				else {
					board[k + 1][i] = board[j][i];
					if ((k + 1) != j) {
						board[j][i] = 0;
						if_need_add_num = 1;
					}
					k++;
				}
			}
		}
	}
}
void move_down() {
	for (i = 0; i < 4; i++) {
		for (j = 2, k = 3; j >= 0; j--) {
			if (board[j][i] > 0) {
				if (board[k][i] == board[j][i]) {
					board[k][i] *= 2;
					k--;
					score = score + 2 * board[j][i];
					board[j][i] = 0;
					if_need_add_num = 1;
				}
				else if (board[k][i] == 0) {
					board[k][i] = board[j][i];
					board[j][i] = 0;
					if_need_add_num = 1;
				}
				else {
					board[k - 1][i] = board[j][i];
					if ((k - 1) != j) {
						board[j][i] = 0;
						if_need_add_num = 1;
					}
					k--;
				}
			}
		}
	}
}
void refresh_show() {
	COORD pos = { 0, 0 };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);
	printf("GAME: 2048     SCORE: %6d    BEST: %6d\n", score, best);
	printf("*************************************************************\n\n");
	for (int i = 0; i < 4; i++)
	{
		printf("       ©¦");
		for (int j = 0; j < 4; j++) {
			if (board[i][j] != 0)
			{
				if (board[i][j] < 10)
				{
					printf("  %d", board[i][j]);
					printf("   ©¦");
				}
				else if (board[i][j] < 100)
				{
					printf("  %d", board[i][j]);
					printf("  ©¦");
				}
				else if (board[i][j] < 1000)
				{
					printf("  %d", board[i][j]);
					printf(" ©¦");
				}
				else if (board[i][j] < 10000)
				{
					printf(" %4d", board[i][j]);
					printf(" ©¦");
				}
				else
				{
					int n = board[i][j];
					for (int k = 1; k < 30; k++)
					{
						n /= 2;
						if (n == 1)
						{
							printf("2^%2d |", k);
							break;
						}
					}
				}
			}
			else
			{
				printf("     ");
				printf(" ©¦");
			}
		}
		if (i <= 2)
		{
			printf("\n        ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤\n");
		}
		else printf("\n");
	}
	printf("\n*************************************************************\n\n");
	printf("¡ü ¡ý  ¡û  ¡ú  ");
	if (null_count() == 0) {
		check_if_over();
		if (if_game_over) {
			printf("\nGAME OVER,TRY AGAIN?[Y/N]:");
		}
	}
}
void add_rand_num() {
	srand(time(0));
	int n = rand() % null_count();
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (board[i][j] == 0) {
				if (n != 0) {
					n--;
				}
				else {
					int k = rand() % 3;
					if (k == 0 || k == 1) {
						board[i][j] = 2;
						return;
					}
					else {
						board[i][j] = 4;
						return;
					}
				}
			}
		}
	}
}
void check_if_over() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 3; j++) {
			if (board[i][j] == board[i][j + 1] || board[j][i] == board[j + 1][i]) {
				if_game_over = 0;
				return;
			}
		}
	}
	if_game_over = 1;
}

