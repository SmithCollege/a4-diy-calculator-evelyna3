package a4;

import java.util.ArrayDeque;

public class Postfix {

    public static ArrayDeque<Double> stack;

    public static Double postfix(ArrayDeque<Object> tokens) {
        while(!tokens.isEmpty()){
            Object current = tokens.removeFirst();
            if(current instanceof Double){
                stack.addFirst((Double)current);
            } else if(current instanceof Character){
                Double second = stack.removeFirst();
                Double first = stack.removeFirst();
                Double result;
                if(current.equals('+')){
                    result = first + second;
                } else if(current.equals("-")){
                    result = first - second;
                } else if(current.equals('*')){
                    result = first*second;
                } else{
                    if(second != 0){
                        result = first/second;
                    } else{
                        throw new RuntimeException("You cannot divide by 0");
                    }
                }
                stack.addFirst(result);
            }
        }
        if(stack.size()>0){
            throw new IllegalArgumentException("Expression is malformed.");
        }
        return stack.getFirst();
    }
    
}