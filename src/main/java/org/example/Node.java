package org.example;

import java.util.ArrayList;
import java.util.List;

class Node {
    private char[][] data;
    private int level;
    private int fval;

    public Node(char[][] data, int level, int fval) {
        this.data = data;
        this.level = level;
        this.fval = fval;
    }

    public List<Node> generateChild() {
        int[] blankPos = find(data, '_');
        int x = blankPos[0];
        int y = blankPos[1];
        int[][] valList = {{x, y - 1}, {x, y + 1}, {x - 1, y}, {x + 1, y}};
        List<Node> children = new ArrayList<>();
        for (int[] i : valList) {
            char[][] child = shuffle(data, x, y, i[0], i[1]);
            if (child != null) {
                Node childNode = new Node(child, level + 1, 0);
                children.add(childNode);
            }
        }
        return children;
    }

    private char[][] shuffle(char[][] puz, int x1, int y1, int x2, int y2) {
        if (x2 >= 0 && x2 < puz.length && y2 >= 0 && y2 < puz.length) {
            char[][] tempPuz = copy(puz);
            char temp = tempPuz[x2][y2];
            tempPuz[x2][y2] = tempPuz[x1][y1];
            tempPuz[x1][y1] = temp;
            return tempPuz;
        } else {
            return null;
        }
    }

    private char[][] copy(char[][] root) {
        char[][] temp = new char[root.length][root[0].length];
        for (int i = 0; i < root.length; i++) {
            for (int j = 0; j < root[0].length; j++) {
                temp[i][j] = root[i][j];
            }
        }
        return temp;
    }

    private int[] find(char[][] puz, char x) {
        int[] result = new int[2];
        for (int i = 0; i < puz.length; i++) {
            for (int j = 0; j < puz[0].length; j++) {
                if (puz[i][j] == x) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public char[][] getData() {
        return data;
    }

    public int getLevel() {
        return level;
    }

    public int getFval() {
        return fval;
    }

    public void setFval(int fval) {
        this.fval = fval;
    }

    public void setData(char[][] data) {
        this.data = data;
    }
}

