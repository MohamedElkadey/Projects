    #include <bits/stdc++.h>
#include <conio.h>
#include <windows.h>
#define ll long long
using namespace std;

bool game = 1;
const int width = 42;
const int height = 22;
int fx,fy,score,ns =1,nsp=1,gx = 3,gy = 4;
enum eDirection { stop = 0, lf, rt, up, dn };
eDirection dir;
vector<int>sx(100),sy(100);
vector<int>sxp(100),syp(100);

void setup()
{
    game =1;
    dir = stop;
    score = 0;
    fx = rand() % (width-2)+1;
    fy = rand() % (height-2)+1;
    //cout<<fx<<"        "<<fy;
    sx[0] = 20;
    sy[0] = 10;
    gx = (rand() %(width-2)+1)% (rand()/ 3);
    gy = (rand() %(height-2)+1)% (rand()/2);

}
void draw()
{
    system("cls");
    //cout<<fx<<"      "<<fy<<"\n";
    for(int i =0;i< height;i++ )
    {
        cout<<"\t\t";
        for(int j =0;j <width;j++)
        {
            if(i == 0||i == height-1)
            {
                if(j % gy == 0 && j != 0)
                {
                    cout<<"_";
                }
                else
                    cout<<"#";
            }
            else if(j == 0 || j == width-1)
            {
                if(i%gx == 0 )
                    cout<<"|";
                else
                    cout<<"#";
            }
            else if(i == fy && j == fx)
            {
                cout<<'F';
            }

            else {
                    bool print = 0;
                    for(int q=0;q <ns;q++)
                    {
                        if(sx[q] == j &&sy[q] == i)
                        {
                            print = 1;
                            if(q == 0)
                            {
                                cout<<'O';
                            }
                            else cout<<'o';
                        }
                    }
                if(!print)
                    {
                        cout<<' ';
                    }

            }
        }
        cout<<'\n';
    }
    cout<<"your score is "<<score<<"\n";
}

void input()
{
    if(_kbhit())
    {
        switch(_getch())
        {
        case 'a':
            if(dir != rt)
                dir = lf;
            break;
        case 'd':
            if(dir != lf)
                dir = rt;
            break;
        case 'w':
            if(dir != dn)
                dir = up;
            break;
        case 's':
            if(dir != up)
                dir = dn;
            break;
        }
    }
}
void update()
{
    switch (dir) {
        case lf:
            sx[0]--;
            break;
        case rt:
            sx[0]++;
            break;
        case up:
            sy[0]--;
            break;
        case dn:
            sy[0]++;
            break;
        default:
            break;
    }
    if(sx[0] == 0 ||sx[0] == width-1 ||sy[0] == 0|| sy[0] == height-1 )
    {
        if(sx[0] == 0 && sy[0]%gx == 0)
        {
            sx[0] = width-1;
        }
        else if(sx[0] == width-1 && sy[0]%gx == 0)
        {
            sx[0] = 0;
        }
        else if(sx[0]%gy == 0 && sy[0] == 0)
        {
            sy[0] = height-1;
        }
        else if( sx[0]%gy == 0 && sy[0] == height-1)
        {
            sy[0] = 0;
        }
        else
            game =0;
    }
    for(int n =1;n<ns;n++)
    {
        if(sx[0] == sx[n] && sy[0] == sy[n])
        {
            game =0;
            break;
        }
    }
    if(sx[0] == fx && sy[0] == fy)
    {
        score+=10;
        fx = rand() % (width-2)+1;
        fy = rand() % (height-2)+1;
        ns++;
    }

    for(int i = 1;i<nsp;i++)
    {
        sx[i] = sxp[i-1];
        sy[i] = syp[i-1];
    }
    if(ns > nsp)
    {
        sx[nsp] = sx[nsp-1];
        sy[nsp] = sy[nsp-1];
        nsp = ns;
    }
    sxp = sx;
    syp = sy;

}
int main()
{
    srand(time(0));
    setup();
    while(game)
    {
    draw();
    input();
    update();
    Sleep(50);
    }
    cout<<"\t\t\t\tGAME OVER!\n";
    return 0;
}
