package com.example.myapplication;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button b0,b1,b3,b4,b5,b2,b6,b7,b8,b9,clearallb,deleteb,modeb,devideb,dotb,addb,subb,equal,mul,page,ex ,pi,fac,ksr,roat,power,log,ln,fpranc,lpranc,sin,cos,tan,anglin,twopg;
    private TextView resilt ,equation ,history;
    private TableLayout table;
    String equations = "" , results = "" ,bresult ="";
    boolean paste =false,rpage = true , ang = true , rsin = true;
    Map<Character, Integer>elements ;
    ArrayList<String>histlist = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        System.out.println("Start program");
        intial();
        setButtonListeners();
        startUpdating();



    }

    private void startUpdating() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(rsin)
                {
                    anglin.setVisibility(View.VISIBLE);
                    sin.setText("sin(");
                    cos.setText("cos(");
                    tan.setText("tan(");
                }
                else {
                    anglin.setVisibility(View.GONE) ;
                    sin.setText("arcsin(");
                    cos.setText("arccos(");
                    tan.setText("arctan(");

                }
                if(!ang)
                {
                    anglin.setText("rad");
                }
                else
                    anglin.setText("dgr");
                try {
                    equation.setText(equations);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }

                // Repeat this update every 500ms
                handler.postDelayed(this,100);
            }
        }, 100);
    }


    private void setButtonListeners() {
        System.out.println("Start listen");
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += '0';
                paste =false;
                solve();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '1';
                solve();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '2';
                solve();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '3';
                solve();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '4';
                solve();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '5';
                solve();

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '6';
                solve();

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '7';
                solve();

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '8';
                solve();

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paste =false;
                equations += '9';
                solve();

            }
        });

        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastres();
                if(equations.length() == 0)
                    equations +='0';
                if(equations.charAt(equations.length()-1) == '-')
                {
                    equations = deleteelement(equations);

                }
                else if(equations.charAt(equations.length()-1) == '+')
                {
                    equations = deleteelement(equations);
                }
                else
                    equations += '+';
                solve();

            }
        });
        subb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastres();
                if(equations.length() == 0)
                    equations +='0';

                if(equations.charAt(equations.length()-1) == '-')
                {
                    equations = deleteelement(equations);
                    equations += '+';
                }
                else
                    equations += '-';
                solve();

            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastres();

                if(equations.length() == 0)
                    equations +='1';

                equations += '*';
                solve();

            }
        });
        devideb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastres();
                if(equations.length() != 0)
                    equations += '/';
                solve();

            }
        });
        modeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastres();
                equations += '%';
                solve();

            }
        });
        deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations = deleteelement(equations);
                solve();

            }
        });
        clearallb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equations == "" ){

                    histlist.clear();
                    hist();
                }
                equations = "";
                solve();

            }
        });
        dotb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += '.';
                solve();

            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveq();
                bresult = results;
                equations ="";
                paste =true;
            }
        });

        //page,ex ,pi,fac,ksr,roat,power,log,ln,fpranc,lpranc,sin,cos,tan,anglein,twopg
        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!paste)
               {
                   visable();
               }
               else
                   gonek();
               paste = !paste;

            }
        });
        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += 'e';
                solve();

            }
        });
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += "π";
                solve();

            }
        });
        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += '!';
                solve();

            }
        });
        ksr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += "^-1";
                solve();

            }
        });

        roat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += "sqrt(";
                solve();

            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += '^';
                solve();

            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += "log(";
                solve();

            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += "ln(";
                solve();

            }
        });
        fpranc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations +='(';
                solve();

            }
        });
        lpranc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equations += ')';
                solve();

            }
        });
        anglin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ang = !ang;
            }
        });
        twopg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsin = !rsin;
            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rsin)
                {
                    equations += "sin(";
                }
                else
                {
                    equations += "arcsin(";
                }
                solve();

            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rsin)
                {
                    equations += "cos(";
                }
                else
                {
                    equations += "arccos(";
                }
                solve();

            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rsin)
                {
                    equations += "tan(";
                }
                else
                {
                    equations += "arctan(";
                }
                solve();

            }
        });


    }

    private void gonek() {
        //ex ,pi,fac,ksr,roat,power,log,ln,fpranc,lpranc,sin,cos,tan,anglein,twopg
        ex.setVisibility(View.GONE);
        pi.setVisibility(View.GONE);
        fac.setVisibility(View.GONE);
        ksr.setVisibility(View.GONE);
        roat.setVisibility(View.GONE);
        table.setVisibility(View.GONE);

    }

    private void visable() {
        //ex ,pi,fac,ksr,roat,power,log,ln,fpranc,lpranc,sin,cos,tan,anglein,twopg
        ex.setVisibility(View.VISIBLE);
        pi.setVisibility(View.VISIBLE);
        fac.setVisibility(View.VISIBLE);
        ksr.setVisibility(View.VISIBLE);
        roat.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);


    }

    private void pastres() {
        if(bresult.length() != 0 && paste)
        {
            equations = bresult;
            bresult = "";
        }

    }
    private void solveq() {
        System.out.println("equal start");
        try {
            solve();

            histlist.add(equations);
            hist();

        }
        catch (Exception e){
            System.out.println("erorr");
        }
    }

    private void hist() {
        histlist.add( "  = " + results);
        addStringsToTextView();
    }
    private void addStringsToTextView() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : histlist) {
            stringBuilder.append(str).append("\n");
        }
        history.setText(stringBuilder.toString().trim());
    }

    private void solve() {
        System.out.println(equations);
        double res ;
        solve_equation solve = new solve_equation();
        try {
            res = solve.solve(equations, ang);
        }catch (Exception e) {
            res = 0;
        }
        System.out.println(res);
//        Expression expression = new ExpressionBuilder(equations).build();
//        res = expression.evaluate();
        results = String.valueOf(res);
        resilt.setText(results);

    }

    public String deleteelement(String s) {
        if (s.equals("") || s.equals(null))
            return "";
        if(s.charAt(s.length()-1) == '(' &&  (s.length() >= 3 && s.substring(s.length()-3 ,s.length()).equals("ln(")))
            return s.substring(0 , s.length()-3);
        if (s.charAt(s.length() - 1) == '(' && s.length() >= 4) {
            String x = s.substring(s.length() - 4, s.length());
            System.out.println(x);
            if (x.equals("sin(") || x.equals("cos(") || x.equals("tan(") || x.equals("log("))
                return s.substring(0, s.length() - 4);
        }
        if(s.charAt(s.length()-1) == '(' && (s.length() >= 5 && s.substring(s.length()-5 ,s.length()).equals("sqrt(")) )
            return s.substring(0 , s.length()-5);
        if (s.charAt(s.length() - 1) == '('  && s.length() >= 7) {
            String y = s.substring(s.length() - 7, s.length() - 1);
            System.out.println(y);
            if ((y == "arcsin(" || y == "arccos(" || y == "arctan(") ) {
                return s.substring(0, s.length() - 7);
            }
        }
        return s.substring(0, s.length() - 1);
    }

    private void intial() {
        System.out.println("Start intialization");
        equations ="";
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4= findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);

        dotb = findViewById(R.id.dot);
        clearallb = findViewById(R.id.clearbutton);
        deleteb = findViewById(R.id.deleteb);
        modeb = findViewById(R.id.modeb);
        devideb = findViewById(R.id.devid);
        mul = findViewById(R.id.multiplication);
        addb = findViewById(R.id.add);
        subb = findViewById(R.id.subtraction);
        equal = findViewById(R.id.equal);

        page = findViewById(R.id.rkey);
        ex = findViewById(R.id.ekey);
        pi = findViewById(R.id.pi);
        fac = findViewById(R.id.factorial);
        ksr = findViewById(R.id.kasr);
        roat = findViewById(R.id.roat);
        power = findViewById(R.id.powerk);
        log = findViewById(R.id.logk);
        ln =findViewById(R.id.lnk);
        fpranc =findViewById(R.id.fpranc);
        lpranc =findViewById(R.id.lpranc);
        sin =findViewById(R.id.sink);
        cos =findViewById(R.id.cosk);
        tan =findViewById(R.id.tank);
        anglin =findViewById(R.id.anglinput);
        twopg =findViewById(R.id.twopg);
        table = findViewById(R.id.extratable);
        resilt = findViewById(R.id.result);
        history = findViewById(R.id.history);
        equation = findViewById(R.id.equation);
    }
}


