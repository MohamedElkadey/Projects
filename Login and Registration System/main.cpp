#include <bits/stdc++.h>
#include "fileop.h"
#define ll long long
using namespace std;
fileop f1;
void site( )
{
    f1.cl();
    vector<string>data = f1.data_e();
    cout<<"\t\t\twelcome "<<data[2]<<'\n';
    int n = data[2].length() + 8;
    cout<<"\t\t\t";
    while(n--)
    {
        cout<<'*';
    }
    cout<<"\n\n\n\n\n\n\n\n\n\n";




}

bool valide(string em)
{
    int b = em.find('@');
    if(b < 5)
    {
        return 0;
    }
    else
    {
        string x ="";
        for(int i =b;i<em.length();i++ )
        {
            x +=em[i];
        }
        if(x == "@gmail.com")
        {
            return 1;
        }
        else
            return 0;
    }
}
bool existing(string em)
{
    return f1.searchE(em);
}
bool colun(string x)
{
    for(int i =0;i<x.length();i++)
    {
        if(x[i] == ',')
            return 1;
    }
    return 0;
}
void login()
{
    string em ,ps="";
    cout<<"Enter your Email: ";
    cin>>em;
     bool f =1,d=1;
    f = valide(em);
    d = existing(em);
    while(!f || !d )
    {
        cin>>em;
        f = valide(em);
        d = existing(em);
    }
    cout<<"enter your password: ";
    cin>>ps;
    if(ps.length() < 7){
    while(ps.length() < 7 )
    {
        cout<<"\tNot Valid\nEnter your password: ";
        cin>>ps;
    }
    }
    //send to server em ps
    if(!f1.searchP(em,ps))
    {
        login();
    }
    site();


}
void singup()
{
    string fn,ln,em,ps;
    cout<<"Enter your frist name: ";
    cin>>fn;
    while(colun(fn))
    {
        cout<<"Enter your frist name: ";
        cin>>fn;
    }
    cout<<"Enter your last name: ";
    cin>>ln;
    while(colun(ln))
    {
        cout<<"Enter your frist name: ";
        cin>>fn;
    }

    cout<<"Enter your G-Email: ";
    cin>>em;

    while(!valide(em) || existing(em) || colun(em))
    {
        cout<<"this is not valid Email\n";
        cout<<"enter valid email: ";
        cin>>em;
    }
    cout<<"password must't have \',\' \n";
    cout<<"create password: ";
    cin>>ps;
    if(ps.length() < 7){
        while(ps.length() < 7 || colun(ps))
        {
            cout<<"enter valid password: ";
            cin>>ps;
        }
    }
    f1.addem(em,ps,fn,ln);
    //send to server em ps
    site();
}

int main() {

    cout<<"\t\t\tWelcome to My Site \n";
    cout<<"\t\t**********************************\n";

    int n;bool f =1;

    do{
    cout<<"\t      To login input 1\tto sing up input 2\n";
    cin>>n;
    if(n == 1)
    {
        login();
        f=0;
    }
    else if(n == 2)
    {
        singup();
        f=0;
    }
    }while(f);
}
