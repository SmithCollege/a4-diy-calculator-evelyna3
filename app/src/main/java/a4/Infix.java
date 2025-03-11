package a4;

import java.util.ArrayDeque;

public class Infix {

    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Object> output = new ArrayDeque<Object>();
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        while(!tokens.isEmpty()){
            Object current = tokens.removeFirst();
            if(current instanceof Double){
                output.addLast((Double)current);
                System.out.println("Output during double: " + output);
                System.out.println("Stack during double: " + stack);
            } else if(current.equals('+')||current.equals('-')||current.equals('*')||current.equals('/')||current.equals('^')){
                Character queueOp = (Character) current;
                if(!stack.isEmpty()){
                    System.out.println("Stack not empty: " + stack);
                    Character stackOp = stack.getFirst();
                    int queueNum = precedence(queueOp);
                    while(!stack.isEmpty() && queueNum <= precedence(stack.getFirst())){
                        System.out.println("Stack during while: " + stack);
                        output.addLast(stack.removeFirst());
                    }
                    stack.addFirst(queueOp);
                } else{
                    stack.addFirst(queueOp);
                }
            } else if(current.equals('(')){
                stack.addFirst((Character) current);
            } else if(current.equals(')')){
                while(!stack.getFirst().equals('(')){
                    output.addLast(stack.removeFirst());
                    if(stack.isEmpty()){
                        throw new IllegalArgumentException("Mismatched parentheses.");
                    }
                }
                stack.removeFirst();
            }
        }
        while(!stack.isEmpty()){
            if(stack.getFirst().equals('(')||stack.getFirst().equals(')')){
                throw new IllegalArgumentException("Mismatched parentheses.");
            } else if(stack.getFirst().equals('+')||stack.getFirst().equals('-')||stack.getFirst().equals('*')||stack.getFirst().equals('/')){
                output.addLast(stack.removeFirst());
                }
        }
        System.out.println(output);
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
        String test = "3*2^6+7";
        ArrayDeque<Object> test2 = Tokenizer.readTokens(test);
        System.out.println(test2);
        System.out.println(Infix.infixToPostfix(test2));
    }
}