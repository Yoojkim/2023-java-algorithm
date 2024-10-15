import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        LinkedList<Character> operators = new LinkedList<>();
        LinkedList<String> operands = new LinkedList<>();

        for(char letter:expression.toCharArray()){
            if(letter >= 'A' && letter <='Z'){
                operands.add(Character.toString(letter));
                continue;
            }

            if(letter != ')'){
                operators.add(letter);
                continue;
            }

            LinkedList<Character> tempOperators = new LinkedList<>();
            LinkedList<String> tempOperands = new LinkedList<>();

            tempOperands.addFirst(operands.pollLast());
            while(true){
                char op = operators.pollLast();

                if(op == '('){
                    String result = calculate(tempOperators, tempOperands);
                    operands.addLast(result);
                    break;
                }
                tempOperators.addFirst(op);
                tempOperands.addFirst(operands.pollLast());
            }
        }

        String result = calculate(operators, operands);

        System.out.print(result);
    }

    private static String calculate(LinkedList<Character> operators, LinkedList<String> operands){
        LinkedList<String> operandStack = new LinkedList<>();
        LinkedList<Character> operatorStack = new LinkedList<>();

        operandStack.addLast(operands.poll());

        while(!operators.isEmpty()){
            char exp = operators.poll();

            if(exp == '+' || exp == '-'){
                operatorStack.addLast(exp);
                operandStack.addLast(operands.poll());

                continue;
            }

            String first = operandStack.pollLast();
            String second = operands.pollFirst();

            String combinedOperand = sb.append(first).append(second).append(exp).toString();
            sb.setLength(0);
            operandStack.addLast(combinedOperand);
        }

        while(operandStack.size() != 1){
            String first = operandStack.pollFirst();
            String second = operandStack.pollFirst();
            char exp = operatorStack.pollFirst();

            String combinedOperand = sb.append(first).append(second).append(exp).toString();
            sb.setLength(0);
            operandStack.addFirst(combinedOperand);
        }

        return operandStack.pop();
    }
}