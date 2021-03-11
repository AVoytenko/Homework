package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] isVisited = new boolean[adjacencyMatrix.length];
        deque.push(startIndex);
        isVisited[startIndex] = true;
        ArrayList<Integer> elements = new ArrayList<>();
        while (!deque.isEmpty()) {
            elements.add(deque.getFirst());
            int firstElement = deque.getFirst();
            deque.removeFirst();
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if(adjacencyMatrix[firstElement][i] && !isVisited[i]){
                    deque.add(i);
                    isVisited[i] = true;
                }
            }
        }
        return elements.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {

        String openBracket = "([{";
        String closedBracket = ")]}";

        char[] input = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (Character letter: input){
            if (openBracket.contains(letter.toString())){
                deque.push(letter);
            }
            if (closedBracket.contains(letter.toString())){

                if (deque.isEmpty()){
                    return false;
                }

                if(closedBracket.indexOf(letter) != openBracket.indexOf(deque.pop())){
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) throws IllegalArgumentException {
        String[] numbers = s.split(" ");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        long result = 0;
        int first;
        int second;
        try {
            for (String num :numbers) {
                deque.push(Integer.parseInt(num));
            }
        }
        catch (IllegalArgumentException e){
            for (int i = deque.size(); i < numbers.length ; i++) {
                first = deque.peek();
                deque.pop();
                second = deque.peek();
                deque.pop();
                switch (numbers[i]){
                    case "-":
                        deque.push(first - second);
                        break;
                    case  "+":
                        deque.push(first + second);
                        break;
                    case "*":
                        deque.push(first * second);
                        break;
                    case "/":
                        deque.push(first / second);
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported value: " + numbers[i]);

                }
                result = deque.getFirst();
            }
        }
        return result;
    }
}
