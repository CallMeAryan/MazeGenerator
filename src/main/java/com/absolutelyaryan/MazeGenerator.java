package com.absolutelyaryan;

import com.absolutelyaryan.objects.Cell;

import java.util.*;

public class MazeGenerator {
    public String generateMaze(int size) {
        String[][] maze2DArray = maze2D(size);
        String[][] mazeGenerated = generateMaze(maze2DArray);
        return convert2D(mazeGenerated);
    }


    private String[][] generateEmptyMaze(int size) {
        int mazeSize = 2 * size + 1;
        String[][] maze2D = new String[mazeSize][mazeSize];

        for (int i = 0; i < mazeSize; i++) {
            Arrays.fill(maze2D[i], " ");
        }

        maze2D[0][1] = "S";
        maze2D[mazeSize - 1][mazeSize - 2] = "E";

        return maze2D;
    }


    private static String[][] maze2D(int size) {
        String[][] maze2D = new String[2 * size + 1][2 * size + 1];

        for (int columnIndex = 0; columnIndex < 2 * size + 1; ++columnIndex) {
            for (int rowIndex = 0; rowIndex < 2 * size + 1; ++rowIndex) {
                if (rowIndex == 1 && columnIndex == 0) maze2D[columnIndex][rowIndex] = "S";
                else if (rowIndex == 2 * size - 1 && columnIndex == 2 * size) maze2D[columnIndex][rowIndex] = "E";
                else if (rowIndex % 2 == 0) maze2D[columnIndex][rowIndex] = columnIndex % 2 == 0 ? "+" : "|";
                else maze2D[columnIndex][rowIndex] = columnIndex % 2 == 0 ? "-" : "0";
            }
        }

        return maze2D;
    }


    private String[][] generateMaze(String[][] maze2D) {
        Stack<Cell> location = new Stack<>();
        int size = (maze2D.length - 1) / 2;
        int totalCells = size * size;
        int visitedCells = 1;
        Cell current = new Cell(0, 0);

        while (visitedCells < totalCells) {
            ArrayList<String> direction = new ArrayList<>(Arrays.asList("NORTH", "EAST", "SOUTH", "WEST"));
            Collections.shuffle(direction);
            String random = validSpot(maze2D, current, direction);
            if (random.equals("BACKTRACK")) {
                if (!location.isEmpty()) {
                    current = location.pop();
                } else {
                    break;
                }
            } else {
                current = move(maze2D, current, random);
                visitedCells++;
                location.push(current);
            }
        }

        return maze2D;
    }


    private String validSpot(String[][] maze2D, Cell current, ArrayList<String> direction) {
        int size = (maze2D.length - 1) / 2;
        int x = 2 * current.getx() + 1;
        int y = 2 * current.gety() + 1;

        if (direction.isEmpty()) {
            return "BACKTRACK";
        }

        String random = direction.remove(0);

        switch (random) {
            case "NORTH":
                if (current.gety() - 1 < 0 || maze2D[y - 3][x].equals("#") || maze2D[y - 1][x].equals("#") || maze2D[y - 2][x - 1].equals("#") || maze2D[y - 2][x + 1].equals("#")) {
                    return validSpot(maze2D, current, direction);
                }
                break;
            case "EAST":
                if (current.getx() + 1 >= size || maze2D[y + 1][x + 2].equals("#") || maze2D[y - 1][x + 2].equals("#") || maze2D[y][x + 1].equals("#") || maze2D[y][x + 3].equals("#")) {
                    return validSpot(maze2D, current, direction);
                }
                break;
            case "SOUTH":
                if (current.gety() + 1 >= size || maze2D[y + 1][x].equals("#") || maze2D[y + 3][x].equals("#") || maze2D[y + 2][x - 1].equals("#") || maze2D[y + 2][x + 1].equals("#")) {
                    return validSpot(maze2D, current, direction);
                }
                break;
            case "WEST":
                if (current.getx() - 1 < 0 || maze2D[y - 1][x - 2].equals("#") || maze2D[y + 1][x - 2].equals("#") || maze2D[y][x - 3].equals("#") || maze2D[y][x - 1].equals("#")) {
                    return validSpot(maze2D, current, direction);
                }
                break;
        }

        return random;
    }

    private Cell move(String[][] maze2D, Cell current, String random) {
        maze2D[1][1] = "#";
        switch (random) {
            case "NORTH" -> {
                current.setNext(new Cell(current.getx(), current.gety() - 1));
                current = current.getNext();
                maze2D[2 * current.gety() + 2][2 * current.getx() + 1] = "#";
                maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
            }
            case "EAST" -> {
                current.setNext(new Cell(current.getx() + 1, current.gety()));
                current = current.getNext();
                maze2D[2 * current.gety() + 1][2 * current.getx()] = "#";
                maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
            }
            case "SOUTH" -> {
                current.setNext(new Cell(current.getx(), current.gety() + 1));
                current = current.getNext();
                maze2D[2 * current.gety()][2 * current.getx() + 1] = "#";
                maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
            }
            case "WEST" -> {
                current.setNext(new Cell(current.getx() - 1, current.gety()));
                current = current.getNext();
                maze2D[2 * current.gety() + 1][2 * current.getx() + 2] = "#";
                maze2D[2 * current.gety() + 1][2 * current.getx() + 1] = "#";
            }
        }

        return current;
    }



    private String convert2DToString(String[][] maze2D) {
        StringBuilder mazeStr = new StringBuilder();

        for (String[] row : maze2D) {
            for (String cell : row) {
                mazeStr.append(cell);
            }
            mazeStr.append("\n");
        }

        return mazeStr.toString();
    }

    private String convert2D(String[][] maze2D) {
        StringBuilder maze = new StringBuilder();
        int size = maze2D.length;

        for (int columnIndex = 0; columnIndex < size; ++columnIndex) {
            for (int rowIndex = 0; rowIndex < size; ++rowIndex) {
                switch (maze2D[columnIndex][rowIndex]) {
                    case "+":
                        maze.append("+");
                        break;
                    case "-":
                        maze.append("---");
                        break;
                    case "|":
                        maze.append("|");
                        break;
                    case "#":
                        if (columnIndex % 2 == 1) {
                            if (rowIndex % 2 == 0) {
                                maze.append(" ");
                            } else {
                                maze.append("   ");
                            }
                        } else {
                            maze.append("   ");
                        }
                        break;
                    default:
                        if (!maze2D[columnIndex][rowIndex].equals("S") && !maze2D[columnIndex][rowIndex].equals("E")) {
                            if (maze2D[columnIndex][rowIndex].equals(" ")) {
                                if (columnIndex % 2 == 1 && rowIndex % 2 == 0) {
                                    maze.append(" ");
                                } else {
                                    maze.append("   ");
                                }
                            } else {
                                maze.append(" ").append(maze2D[columnIndex][rowIndex]).append(" ");
                            }
                        } else {
                            maze.append("   ");
                        }
                        break;
                }

                if (rowIndex == size - 1 && columnIndex != size - 1) {
                    maze.append(System.lineSeparator());
                }
            }
        }

        return maze.toString();
    }
}

