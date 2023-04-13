import java.util.*;
import java.io.*;

public class NFAReader {
    public static NFA read(String filename) throws IOException {
    Set<Integer> states = new HashSet<>();
    Set<Character> alphabet = new HashSet<>();
    Map<Integer, Map<Character, Set<Integer>>> transitions = new HashMap<>();
    int startState = -1;
    Set<Integer> acceptStates = new HashSet<>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = reader.readLine()) != null) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 1) { // state
            int state = Integer.parseInt(parts[0]);
            states.add(state);
            if (startState == -1) {
                startState = state;
            }
        } else if (parts.length == 2) { // accept state
            int state = Integer.parseInt(parts[0]);
            states.add(state);
            acceptStates.add(state);
        } else if (parts.length == 3) { // transition
            int fromState = Integer.parseInt(parts[0]);
            char symbol = parts[1].charAt(0);
            int toState = Integer.parseInt(parts[2]);
            states.add(fromState);
            states.add(toState);
            alphabet.add(symbol);
            // System.out.println("Accepted: " + fromState);
            Map<Character, Set<Integer>> transitionsFromState = transitions.computeIfAbsent(fromState, k -> new HashMap<>());
            Set<Integer> nextStates = transitionsFromState.computeIfAbsent(symbol, k -> new HashSet<>());
            nextStates.add(toState);
        } else {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
    }
    reader.close();
    return new NFA(states, alphabet, transitions, startState, acceptStates);
}
}