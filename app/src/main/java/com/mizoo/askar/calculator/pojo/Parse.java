package com.mizoo.askar.calculator.pojo;

public class Parse {

    int pos = -1, ch;
    String str;
    double result;




    void nextChar() {
        // 10+5*2
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }

    boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public double parse(final String str) {
        double x = 0.0;

        if (!str.isEmpty()) {
            this.str = str;
            nextChar();
             x = parseExpression();
            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);

        }
        result = x;
        return x;
    }

    // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor
    // factor = `+` factor | `-` factor | `(` expression `)`
    //        | number | functionName factor | factor `^` factor

    double parseExpression() {
        double x = parseTerm();
        for (; ; ) {
            if (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else if (eat('%')) x %= parseTerm(); // subtraction
            else return x;
        }
    }

    double parseTerm() {
        double x = parseFactor();
        for (; ; ) {
            if (eat('x')) x *= parseFactor(); // multiplication
            else if (eat('÷')) x /= parseFactor(); // division
            else return x;
        }
    }

    double parseFactor() {
        if (eat('+')) return parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            StringBuilder checkDot = new StringBuilder(str.substring(startPos, pos));
            int counter = 0;
            for (int i = 0; i < checkDot.length(); i++){
                if (checkDot.charAt(i) == '.'){
                    counter++;
                    if (counter > 1){
                        checkDot.deleteCharAt(i);
                    }
                }
            }
            x = Double.parseDouble(checkDot.toString());
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = str.substring(startPos, this.pos);
            x = parseFactor();
            if (func.equals("sqrt")) x = Math.sqrt(x);
            else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
            else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
            else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
            else if (func.equals("log")) x = Math.log10(x);
            else if (func.equals("ln")) x = Math.log(x);
            else throw new RuntimeException("Unknown function: " + func);
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

        return x;
    }
}

