#ifndef FILEOP_H
#define FILEOP_H
#include <bits/stdc++.h>
using namespace std;

class fileop
{
     private:

        string email;
        string password;
        string F_name;
        string L_name;

    public:
        fileop();
        fileop(string file_name);
        ~fileop();

        vector<string> data_e();
        bool searchE(string em);
        bool searchP(string em,string password);
        void addem(string em,string password,string F_name,string L_name);
        void cl();

    protected:



};

#endif // FILEOP_H
