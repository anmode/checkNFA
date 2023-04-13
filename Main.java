// import java.io.IOException;

// public class Main {
//     public static void main(String[] args) throws IOException {
//         String filename = "nfa.txt";
//         NFA nfa = NFAReader.read(filename);
//         boolean accepted = nfa.accept("ab");
//         System.out.println("Accepted: " + accepted);
//     }
// }

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "nfa.txt";
        NFA nfa = NFAReader.read(filename);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input string: ");
        String input = scanner.nextLine();

        boolean accepted = nfa.accept(input);
        System.out.println("Accepted: " + accepted);
    }
}


// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;

// public class Main {
//     public static void main(String[] args) throws IOException {
//         String filename = "nfa.txt";
//         NFA nfa = NFAReader.read(filename);
//         String input = new String(Files.readAllBytes(Paths.get("input.txt")));
//         boolean accepted = nfa.accept(input);
//         System.out.println("Accepted: " + accepted);
//     }
// }

