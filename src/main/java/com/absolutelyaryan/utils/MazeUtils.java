package com.absolutelyaryan.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MazeUtils {

    public static List<String> getUnicodeMaze(String mazeData) {
        List<List<String>> mazeRoomData = divideRooms(mazeData.split("\n"));
        List<String> unicodeMaze = new ArrayList<>();
        for(List<String> row : mazeRoomData) {
            StringBuilder unicodeRow = new StringBuilder();
            for(String room : row) {
                unicodeRow.append(convertUnicode(room));
            }
            unicodeMaze.add(unicodeRow.toString());
        }
        return unicodeMaze;
    }


    private static List<List<String>> divideRooms(String[] mazeArray) {
        Iterator<String> iterator = Arrays.asList(mazeArray).iterator();

        List<List<String>> mazeRooms = new ArrayList<>();

        String lastRow = null;
        while (iterator.hasNext()) {
            List<String> roomList = new ArrayList<>();
            String sideData1;
            if(lastRow != null) {
                sideData1 = lastRow;
            } else {
                sideData1 = iterator.next();
            }

            String sideData2 = iterator.next();
            String sideData3 = iterator.next();
            lastRow = sideData3;

            List<String> sides1 = divideSides(sideData1);
            List<String> sides2 = divideSides(sideData2);
            List<String> sides3 = divideSides(sideData3);

            for(int i = 0; i < sides1.size(); i++) {
                String room = sides1.get(i) + sides2.get(i) + sides3.get(i);
                roomList.add(room);
            }
            mazeRooms.add(roomList);
        }

        return mazeRooms;
    }





    private static List<String> divideSides(String input) {
        List<String> inputList = Arrays.asList(input.split(""));
        Iterator<String> iterator = inputList.iterator();

        int current = 1;
        int lastStop = 5;
        int lastAdd = 6;

        List<String> mazeChars = new ArrayList<>();
        StringBuilder currentSet = new StringBuilder();
        char lastChar = '+';

        while (iterator.hasNext()) {
            if(shouldAdd(lastAdd, current) && current != 0) {
                currentSet.append(lastChar);
                lastAdd = current;
            }
            String currentChar = iterator.next();
            currentSet.append(currentChar);
            lastChar = currentChar.charAt(0);

            if(shouldStop(lastStop, current) && current != 0) {
                mazeChars.add(currentSet.toString());
                currentSet = new StringBuilder();
                lastStop = current;
            }
            current++;
        }

        return mazeChars;
    }

    private static String convertUnicode(String str) {
        String t1l = "+   +|    +---+";
        String t2l = "+   +    |+---+";
        String t3l = "+---+    |+   +";
        String t4l = "+---+|    +   +";
        String t1o = "+---+     +---+";
        String t2o = "+   +|   |+   +";
        String t1t = "+   +     +---+";
        String t2t = "+---+     +   +";
        String t3t = "+   +    |+   +";
        String t4t = "+   +|    +   +";
        String t1b = "+---+|    +---+";
        String t2b = "+---+    |+---+";
        String t3b = "+   +|   |+---+";
        String t4b = "+---+|   |+   +";
        if (str.equals(t1l)) {
            str = "╚";
        } else if (str.equals(t2l)) {
            str = "╝";
        } else if (str.equals(t3l)) {
            str = "╗";
        } else if (str.equals(t4l)) {
            str = "╔";
        }

        if (str.equals(t1o)) {
            str = "═";
        } else if (str.equals(t2o)) {
            str = "║";
        }

        if (str.equals(t1t)) {
            str = "╩";
        } else if (str.equals(t2t)) {
            str = "╦";
        } else if (str.equals(t3t)) {
            str = "╣";
        } else if (str.equals(t4t)) {
            str = "╠";
        }

        if (str.equals(t1b)) {
            str = "╞";
        } else if (str.equals(t2b)) {
            str = "╡";
        } else if (str.equals(t3b)) {
            str = "╨";
        } else if (str.equals(t4b)) {
            str = "╥";
        }

        return str;
    }

    private static boolean shouldStop(int lastStop, int current) {
        if(current == 5) {
            return true;
        }
        return lastStop + 4 == current;
    }
    private static boolean shouldAdd(int lastAdd, int current) {
        if(current == 6) {
            return true;
        }
        return lastAdd + 4 == current;
    }
}
