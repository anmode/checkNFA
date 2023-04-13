import java.util.*;
import java.io.*;

public class NFA {
    private Set<Integer> states;
    private Set<Character> alphabet;
    private Map<Integer, Map<Character, Set<Integer>>> transitions;
    private int startState;
    private Set<Integer> acceptStates;

    public NFA(Set<Integer> states, Set<Character> alphabet, 
               Map<Integer, Map<Character, Set<Integer>>> transitions,
               int startState, Set<Integer> acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.startState = startState;
        this.acceptStates = acceptStates;
    }

    public boolean accept(String input) {
        Set<Integer> currentStates = epsilonClosure(Collections.singleton(startState));
        for (char c : input.toCharArray()) {
            currentStates = move(currentStates, c);
            currentStates = epsilonClosure(currentStates);
        }
        return !Collections.disjoint(currentStates, acceptStates);
    }

    private Set<Integer> epsilonClosure(Set<Integer> states) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.addAll(states);
        while (!stack.isEmpty()) {
            int state = stack.pop();
            visited.add(state);
            Map<Character, Set<Integer>> transitionsFromState = transitions.get(state);
            if (transitionsFromState != null) {
                Set<Integer> epsilonTransitions = transitionsFromState.get(null);
                if (epsilonTransitions != null) {
                    for (int nextState : epsilonTransitions) {
                        if (!visited.contains(nextState)) {
                            stack.push(nextState);
                        }
                    }
                }
            }
        }
        return visited;
    }

    private Set<Integer> move(Set<Integer> states, char symbol) {
        Set<Integer> result = new HashSet<>();
        for (int state : states) {
            Map<Character, Set<Integer>> transitionsFromState = transitions.get(state);
            if (transitionsFromState != null) {
                Set<Integer> nextStates = transitionsFromState.get(symbol);
                if (nextStates != null) {
                    result.addAll(nextStates);
                    System.out.println("NextState: " + nextStates);
                }
            }
        }
        return result;
    }
}
