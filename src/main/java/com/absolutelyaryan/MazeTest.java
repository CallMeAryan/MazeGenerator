package com.absolutelyaryan;

import com.absolutelyaryan.utils.MazeUtils;

import java.util.Scanner;

public class MazeTest {

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of maze: ");
        boolean gotCorrectInput = false;
        int mazeSize = 0;

        while (!gotCorrectInput) {
            try{
                mazeSize = Integer.parseInt(scanner.nextLine());
                gotCorrectInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Integer! Try again...");
            }
        }

        String maze = mazeGenerator.generateMaze(mazeSize);
        System.out.println("Raw Maze: ");
        System.out.println(maze);
        System.out.println(" ");
        System.out.println("Unicode Maze: ");
        MazeUtils.getUnicodeMaze(maze).forEach(System.out::println);

    }
}
