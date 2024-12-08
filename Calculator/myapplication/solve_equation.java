package com.example.myapplication;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class solve_equation {

    public solve_equation() {}

    public static double solve(String equation, boolean ang) {
        if (equation.length() == 0)
            return 0;

        String processedEquation = start(equation, ang);
        System.out.println("Processed Equation: " + processedEquation);
        Expression expression = new ExpressionBuilder(processedEquation).build();
        double result = expression.evaluate();
        return result;
    }

    public static String start(String s, boolean rsin) {
        StringBuilder number = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current) || current == '.') {
                if (current == '.' && number.length() == 0) {
                    number.append('0');
                }
                number.append(current);
            } else {
                if (current == '!') {
                    result.append(calcfac(number.toString()));
                    number.setLength(0);
                }else if (current == 'e' || current == 'π') {
                    result.append(current == 'e' ? Math.E : Math.PI);
                }
                if (number.length() > 0) {
                    result.append(number);
                    number.setLength(0);
                }

                if (isOperation(current)) {
                    result.append(current);
                }else if (current == '(' && i != s.length() - 1) {
                    StringBuilder innerString = new StringBuilder();
                    int j = i + 1;
                    while (j < s.length() && s.charAt(j) != ')') {
                        innerString.append(s.charAt(j));
                        j++;
                    }

                    if (j < s.length() && s.charAt(j) == ')') {
                        i = j;
                        result.append(start(innerString.toString(), rsin));
                    } else {
                        throw new IllegalArgumentException("Unmatched '(' at position " + i);
                    }
                }

                else if (!Character.isWhitespace(current)) {
                    StringBuilder function = new StringBuilder();
                    int j = i;

                    while (j < s.length() && s.charAt(j) != '(') {
                        function.append(s.charAt(j));
                        j++;
                    }

                    if (j < s.length() && s.charAt(j) == '(') {
                        StringBuilder innerNumber = new StringBuilder();
                        j++;

                        while (j < s.length() && s.charAt(j) != ')') {
                            innerNumber.append(s.charAt(j));
                            j++;
                        }
                            String inp = start(innerNumber.toString() , rsin);
                       // if (j < s.length() && s.charAt(j) == ')') {

                            String functionName = function.toString().trim();
                            String argument = inp.trim();

                            double functionResult = solveFunction(functionName, argument, rsin);
                            result.append(functionResult);


                            i = j;
//                        } else {
//                            // Handle error: Missing closing parenthesis
//                            throw new IllegalArgumentException("Unmatched '(' for function: " + function);
//                        }
                    }
                }

            }
        }

        if (number.length() > 0) {
            result.append(number);
        }

        return result.toString();
    }

    private static double solveFunction(String fn, String num, boolean ang) {
        double n = Double.parseDouble(num);
        if(fn.equals("arcsin")) {
            double val = (Math.asin(n) * 180) / Math.PI;
            return val;
        }

        else if (fn.equals( "arccos")) {
            double val = (Math.acos(n) * 180) / Math.PI;
            return val;

        }
        else if(fn.equals( "arctan")) {
            double val = (Math.atan(n) * 180) / Math.PI;
            return val;
        }
        else if(fn.equals("sqrt"))
            return Math.sqrt(n);
        else if(fn.equals( "log"))
            return Math.log10(n);
        else if(fn.equals("ln") )
            return Math.log(n);

        if (ang) {
            n =Math.toRadians(n);
        }
            if( fn.equals("sin")){
                return Math.sin(n);
            }
            else if( fn.equals( "cos"))
                return Math.cos(n);
            else 
                return Math.tan(n);


    }

    private static long calcfac(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("Factorial requires a number before '!'");
        }
        int n = Integer.parseInt(number);
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        return factorial(n);
    }

    private static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private static boolean isOperation(char c) {
        String operations = "+-*/%^";
        return operations.indexOf(c) >= 0;
    }
}
