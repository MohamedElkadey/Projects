#include <bits/stdc++.h>
#define ll long long

using namespace std;

bool check(char x)
{
    x = char(tolower(x));
    if(x == 'r' || x == 'p'||x == 's')
        return true;
    else
        return false;
}
char inX()
{
    char x;
    do
    {
        cout<<"\tr -> Rock \t\tp -> Paper \t\ts -> Scissor\n";
        cout<<"enter your selection: ";
        cin>>x;
        cout<<endl;
    }while(!check(x));
    return x;
}
char randX()
{
    char op[]= {'r','p','s'};
    srand(time(0));
    int f =(rand()%10)/3;
    if(f == 3)
    {
        f = f -(rand() %10)/3;
    }
    return op[f];
}
char clcwn(vector<char>o)
{
    char a = o[0],b =o[1];
    if(a == 'p' && b == 'r')
    {
        return a;
    }
    else if(a == 'p'&& b == 's')
    {
        return b;
    }
    else
    {
        return a;
    }
}

void Who_win(int& SP,int& SC,char x,char comX,int& n )
{
    if(x == comX)
    {
        n++;
        //increase rounds by 1;
        cout<<"\t\tNO ONE WIN\n";

    }
    else
        {
            vector<char>o;
            o.push_back(x);
            o.push_back(comX);
            sort(o.begin(),o.end());
            char wn = clcwn(o);
            if(wn == x)
            {
                cout<<"\t\tYOU WIN THE ROUND\n";
                SP++;
            }
            else
            {
                cout<<"\t\tCOMPUTER WIN THE ROUND\n";
                SC++;
            }
            o.clear();
        }
}
int main()
{
    //ios::sync_with_stdio(false);
    //cin.tie(nullptr);

    cout<<"\t\t\t***************************\n";
    cout<<"\t\t\t* Rock Paper Scissor Game *\n";
    cout<<"\t\t\t***************************\n";
    cout<<"\t\t\t The game is  THREE ROUNDS \n";
    cout<<"if you want to input your choose select on of this char \n";

    int SP = 0,SC =0,n=3;
    for(int i =0;i<n;i++)
    {
        char x = inX();
        char comX = randX();
        cout<<"computer selection\t"<<comX<<"\n\n";
        Who_win(SP,SC,x,comX,n);
        cout<<"\tYOUR SCODE IS "<<SP<<" COMPUTER SCODE IS "<<SC<<"\n";
        if(abs(SP - SC) == 2)
            break;

    }
    if(SP >SC)
    {
        cout<<"\t\tYOU WIN THE GMAE\n";
    }
    else
        cout<<"\t\tGAME OVER\n";
    return 0;
}
