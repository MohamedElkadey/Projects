#include "fileop.h"
#include <bits/stdc++.h>
using namespace std;
fstream scv,ems;

fileop::fileop()
{
scv.open("output.txt",ios::in|ios::out|ios::app);
    if(!scv.is_open())
        cout<<"not opend\n";
    ems.open("ems.txt",ios::in|ios::out|ios::app);
    if(!ems.is_open())
        cout<<"not opend\n";

         email = "";
         password= "";
         F_name = "";
         L_name = "";


}

fileop::~fileop()
{
    cout<<"\tThank you\n";
}
// email user name f_name l_name password
bool fileop::searchE(string email)
{
    string e = "";
    while(getline(ems ,e))
    {
        if(e == email )
        {
            cout<<"email is exist\n";
            return 1;
        }
        e ="";
    }
    return 0;
}
string read_file(string em)
{
    fstream cs;
    cs.open("output.txt");
    cs.seekg(0,ios::beg);
    string g ="",s="";
    while(getline(cs,g))
    {
        s ="";
        for(int i =0;i<em.length()+1;i++)
        {
            if(g[i] != em[i])
                break;
            if(g[i] == ',')
            {
                break;
            }
            s += g[i];
        }
        if(s == em)
        {
            cs.close();
            return g;
        }
    }
    cs.close();
    return "";
}

bool fileop::searchP(string email,string ps)
{
   string data = read_file(email);
   if(data.size() == 0)
   {
       return 0;
   }
   else
   {
       string sd ="";
       int count =0;

       for(int i =email.length()+1;i <data.length();i++)
       {
           if(data[i] == ',' ||i ==data.length()-1 )
           {
               count++;
                if(count == 1)
                {
                    password = sd;

                }
                else if(count == 2)
                {
                    F_name = sd;

                }
                else
                {
                    L_name = sd+data[i];
                }
             sd = "";
           }
           else
           sd += data[i];
       }
       if(ps == password)
        return 1;
       else{
        return 0;
       }
   }
}

void fileop::addem(string em,string ps,string fn,string ln )
{
    if(ems.is_open())
    {
        // i had a problem in my ide so i had to open abc file :)
        fstream abc;
            abc.open("ems.txt",ios::in|ios::out|ios::app);

    abc<< em<<'\n';
    abc.close();
    }

    scv<<em<<','<<ps<<','<<fn<<','<<ln<<'\n';

    cout<<"\t\taccount added \n";


    email = em;
         password= ps;
         F_name = fn;
         L_name = ln;
}

 vector<string> fileop::data_e()
{
    vector<string>p;
    p.push_back(email);
    p.push_back(password);
    p.push_back(F_name);
    p.push_back(L_name);
    return p;

}

    void fileop::cl()
    {
        scv.close();
        ems.close();
    }




