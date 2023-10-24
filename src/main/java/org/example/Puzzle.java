package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle {
    private int n;
    private List<Node> open = new ArrayList<>();
    private List<Node> closed = new ArrayList<>();

    public Puzzle(int size) {
        n = size;
    }

    private char[][] accept() {
        Scanner scanner = new Scanner(System.in);
        char[][] puz = new char[n][n];
        for (int i = 0; i < n; i++) {
            String inputLine = scanner.nextLine();
            String[] elements = inputLine.split(" ");
            for (int j = 0; j < n; j++) {
                if (elements[j].length() > 0) {
                    puz[i][j] = elements[j].charAt(0);
                } else {
                    // Handle cases where input is missing or empty
                    puz[i][j] = '_'; // You can use a different default value if needed
                }
            }
        }

        // Don't forget to close the scanner
        return puz; // Return the 2D character array
    }

        // Implement user input for puzzle here



    private int f(Node start, char[][] goal) {
        return h(start.getData(), goal) + start.getLevel();
    }

    private int h(char[][] start, char[][] goal) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (start[i][j] != goal[i][j] && start[i][j] != '_') {
                    temp++;
                }
            }
        }
        return temp;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setOpen(List<Node> open) {
        this.open = open;
    }

    public void setClosed(List<Node> closed) {
        this.closed = closed;
    }

    public void process() {
        System.out.println("Enter the start state matrix");
        char[][] start = accept();
        System.out.println("Enter the goal state matrix");
        char[][] goal = accept();

        Node startNode = new Node(start, 0, 0);
        startNode.setFval(f(startNode, goal));
        open.add(startNode);
        System.out.println("\n\n");

        while (true) {
            Node current = open.get(0);
            System.out.println("");
            System.out.println("  | ");
            System.out.println("  | ");
            System.out.println(" \\\'/ \n");

            for (char[] row : current.getData()) {
                for (char value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }

            if (h(current.getData(), goal) == 0) {
                break;
            }

            for (Node child : current.generateChild()) {
                child.setFval(f(child, goal));
                open.add(child);
            }
            closed.add(current);
            open.remove(0);

            open.sort((x, y) -> Integer.compare(x.getFval(), y.getFval()));
        }

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(3);
        puzzle.process();
    }
}

