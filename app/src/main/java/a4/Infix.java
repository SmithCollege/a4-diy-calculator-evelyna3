package a4;

import java.util.ArrayDeque;

public class Infix {

    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Object> output = new ArrayDeque<>();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        while(!tokens.isEmpty()){
            Object current = tokens.removeFirst();
            if(current instanceof Double){
                output.addLast((Double)current);
            } else if(current instanceof Character){
                Character queueOp = (Character) current;
                if(current.equals('+')||current.equals('-')||current.equals('*')||current.equals('/')||current.equals('^')){
                    int queueNum = precedence(queueOp);
                    while(!stack.isEmpty() && (queueNum < precedence(stack.getFirst()) || 
                    (queueOp != '^' && queueNum <= precedence(stack.getFirst())))){
                        output.addLast(stack.removeFirst());
                    }
                    stack.addFirst(queueOp);
            } else if(current.equals('(')){
                stack.addFirst((Character) current);
            } else if(current.equals(')')){
                while (!stack.isEmpty() && !stack.getFirst().equals('(')) {
                    output.addLast(stack.removeFirst());
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses.1");
                }
                    stack.removeFirst();
                }
            }
            } 
            while (!stack.isEmpty()) {
                Character op = stack.removeFirst();
                if (op == '(' || op == ')') {
                    throw new IllegalArgumentException("Mismatched parentheses.");
                }
                output.addLast(op);
            }

        Double result = Postfix.postfix(output);
        return result;
    }

    public static int precedence(Character operator){
        int result;
            if(operator.equals('/')||operator.equals('*')){
                result = 2;
            }else if(operator.equals('+')||operator.equals('-')){
                result = 1;
            } else if(operator.equals('^')){
                result = 3;
            } else{
                result = 0;
            }
        return result;
    }

    public static void main(String[] args) {
        String test = "3*(2^6+(8+7))";
        ArrayDeque<Object> test2 = Tokenizer.readTokens(test);
        System.out.println(test2);
        System.out.println(Infix.infixToPostfix(test2));
        
    }
}