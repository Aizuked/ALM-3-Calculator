package org.example;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private boolean conditionsCheck(String exp) {
        Pattern p = Pattern.compile("\\(");
        Matcher m = p.matcher(exp);
        int countLeft = 0;
        while (m.find()){
            countLeft +=1;
        }
        p = Pattern.compile("\\)");
        m = p.matcher(exp);

        int countRight = 0;
        while (m.find()){
            countRight +=1;
        }

        if (countLeft != countRight) {
            System.out.println("Unmatched parenthesis!");
            return false;
        }

        if (exp.contains(".") || exp.contains(",")) {
            System.out.println("Unsupported floating point!");
            return false;
        }

        for (String toCheck : exp.split(" ")) {
            if (!toCheck.matches("[+\\-*/]")){
                try {
                    Integer.valueOf(toCheck);
                } catch (Exception e) {
                    continue;
                }
                if (Integer.valueOf(toCheck) > 255) {
                    System.out.println("Unsupported type!");
                    return false;
                }
            }
        }
        return true;
    }

    public String compute(String exp) {
        if (!conditionsCheck(exp)) {
            System.out.println("Prematurely ended with an error");
            return "-1";
        }
        char[] seq = exp.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        String output = "";
        int ctr = 0;
        for (char i : seq) {
            switch (i) {
                case '+': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    if (stack.isEmpty() || stack.peek() == '(') stack.push('+');
                    else {
                        while ( stack.peek() == '+' ||
                                stack.peek() == '-' ||
                                stack.peek() == '*' ||
                                stack.peek() == '/') {
                            output += stack.peek();
                            stack.pop();
                        }
                    }
                } break;
                case '-': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    if (stack.isEmpty() || stack.peek() == '(') stack.push('-');
                    else {
                        while ( stack.peek() == '+' ||
                                stack.peek() == '-' ||
                                stack.peek() == '*' ||
                                stack.peek() == '/') {
                            output += stack.peek();
                            stack.pop();
                        }
                    }
                } break;
                case '*': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    if (stack.isEmpty() || stack.peek() == '('
                            || stack.peek() == '+'
                            || stack.peek() == '-') stack.push('*');
                    else {
                        while (stack.peek() == '*' ||
                                stack.peek() == '/') {
                            output += stack.peek();
                            stack.pop();
                        }
                    }
                } break;
                case '/': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    if (stack.isEmpty() || stack.peek() == '('
                            || stack.peek() == '+'
                            || stack.peek() == '-') stack.push('/');
                    else {
                        while ( stack.peek() == '*' ||
                                stack.peek() == '/') {
                            output += stack.peek();
                            stack.pop();
                        }
                    }
                } break;
                case '(': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    stack.push('(');
                } break;
                case ')': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    while (stack.peek() != '(') {
                        output += stack.peek();
                        stack.pop();
                    }
                    stack.pop();
                } break;
                case ' ': {
                    if (ctr > 1) output += ' ';
                    ctr = 0;
                    break;
                }
                default:
                    output += i;
                    ctr++;
                    break;
            }
        }
        while (!stack.isEmpty()) {
            output += stack.peek();
            stack.pop();
        }
        return output;
    }

    public Integer calculate(String exp1) {
        if (!conditionsCheck(exp1)) {
            System.out.println("Prematurely ended with an error");
            return -1;
        }
        char[] seq1 = exp1.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        String output = "";
        int ctr = 0;
        for (char i : seq1) {
            switch (i) {
                case '+': {
                    ctr = 0;
                    Integer temp1 = stack.peek();
                    stack.pop();
                    Integer temp2 = stack.peek();
                    stack.pop();
                    stack.push(temp1 + temp2);
                } break;
                case '-': {
                    ctr = 0;
                    Integer temp1 = stack.peek();
                    stack.pop();
                    Integer temp2 = stack.peek();
                    stack.pop();
                    stack.push(temp2 - temp1);
                } break;
                case '*': {
                    ctr = 0;
                    Integer temp1 = stack.peek();
                    stack.pop();
                    Integer temp2 = stack.peek();
                    stack.pop();
                    stack.push(temp1 * temp2);
                } break;
                case '/': {
                    ctr = 0;
                    Integer temp1 = stack.peek();
                    stack.pop();
                    Integer temp2 = stack.peek();
                    stack.pop();
                    stack.push(temp1 / temp2);
                } break;
                case ' ': {
                    ctr = 0;
                } break;
                default: {
                    Integer temp = 0;
                    if(!stack.isEmpty()) temp = stack.peek();
                    if (ctr > 0) {
                        temp += i;
                        stack.pop();
                    }
                    stack.push(Integer.parseInt(String.valueOf(i)));
                    ctr++;
                } break;
            }
        }
        return stack.peek();
    }

}
