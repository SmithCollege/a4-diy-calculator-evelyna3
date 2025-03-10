package a4;

import java.util.ArrayDeque;

public class Postfix {

    public static Double postfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Double> stack = new ArrayDeque<Double>();
        while(!tokens.isEmpty()){
            Object current = tokens.removeFirst();
            if(current instanceof Double){
                stack.addFirst((Double)current);
            } else if(current instanceof Character){
                if(stack.size()>=2){
                    Double second = stack.removeFirst();
                    Double first = stack.removeFirst();
                    Double result;
                    if(current.equals('+')){
                        result = first + second;
                    } else if(current.equals('-')){
                        result = first - second;
                    } else if(current.equals('*')){
                        result = first*second;
                    } else if(current.equals('/')){
                        if(second != 0){
                            result = first/second;
                        } else{
                            throw new RuntimeException("You cannot divide by 0.");
                        }
                    } else{
                        throw new IllegalArgumentException("Expression is malformed.");
                    }
                    if(result != null){
                        stack.addFirst(result);
                    }
                } else{
                    throw new IllegalArgumentException("Expression is malformed.");
                }
            } else{
                throw new IllegalArgumentException("Expression is malformed.");
            }
        }
        if(stack.size()!= 1){
            throw new IllegalArgumentException("Expression is malformed.");
        }
        return stack.getFirst();
    }
    public static void main(String[] args){
        // String myString = "5.0 b 3.0 -";
        // ArrayDeque<Object> test = Tokenizer.readTokens(myString);
        // System.out.println(test);
        // try{
        //     System.out.println(postfix(test));
        // } catch(Exception E){
        //     System.out.println(E);
        // }
        String[] expressions = {
            "+", "7 *", "7 7 7", "7 + 0", "3 b 4"
        };
        for (int i = 0; i < expressions.length; i++) {
            try{
                Postfix.postfix(Tokenizer.readTokens(expressions[i]));
            } catch(Exception E){
                System.out.println(E);
            }
        }
    }
} 