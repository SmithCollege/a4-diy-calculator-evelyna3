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
            } else if(current.equals('+')||current.equals('-')||current.equals('*')||current.equals('/')){
                Character queueOp = (Character) current;
                if(!stack.isEmpty()){
                    int queueNum;
                    int stackNum;
                    if(queueOp.equals('+')||queueOp.equals('-')){
                        queueNum = 1;
                    } else {
                        queueNum = 2;
                    }
                    if(stack.peekFirst().equals('+')||stack.peekFirst().equals('-')){
                        stackNum = 1;
                    } else{
                        stackNum = 2;
                    }
                    while(queueNum<stackNum){
                        output.add(stack.removeFirst());
                        if(stack.peekFirst().equals('+')||stack.peekFirst().equals('-')){
                            stackNum = 1;
                        } else{
                            stackNum = 2;
                        }
                    }
                    stack.addFirst(queueOp);
                }
            } else if(current.equals('(')){
                stack.add((Character) current);
            } else if(current.equals(')')){
                while(!stack.peekFirst().equals('(')){
                    output.add(stack.removeFirst());
                    if(stack.isEmpty()){
                        throw new IllegalArgumentException("Mismatched parentheses.");
                    }
                }
                stack.removeFirst();
            }
        }
        while(!stack.isEmpty()){
            if(stack.peekFirst().equals('(')||stack.peekFirst().equals(')')){
                throw new IllegalArgumentException("Mismatched parentheses.");
            } else if(stack.peekFirst().equals('+')||stack.peekFirst().equals('-')||stack.peekFirst().equals('*')||stack.peekFirst().equals('/')){
                output.addLast(stack.removeFirst());
                }
        }
        Double result = Postfix.postfix(output);
        return result;
    }

}

