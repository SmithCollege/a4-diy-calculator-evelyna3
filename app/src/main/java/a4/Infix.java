package a4;

import java.util.ArrayDeque;

public class Infix {

    public static Double infixToPostfix(ArrayDeque<Object> tokens) {
        ArrayDeque<Object> output = new ArrayDeque<Object>();
        while(!tokens.isEmpty()){
            if(tokens.peekFirst() instanceof Double){
                output.addFirst(tokens.removeFirst());
            } else if(tokens.peekFirst() instanceof Character){
                
            }
        }
        return 0.0;
    }

}

