package com.kowalczyk;

import java.util.*;

class Puzzle {
    int[][] puzzle8;
    Punkt whereIsZero;  // wspolrzedne zera w tablicy puzzle8

    public Puzzle() {
        puzzle8 = new int[3][3];
        whereIsZero = new Punkt();
        randomPuzzle();
    }

    public Puzzle(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9) {
        puzzle8 = new int[3][3];
        puzzle8[0][0] = p1;
        puzzle8[0][1] = p2;
        puzzle8[0][2] = p3;
        puzzle8[1][0] = p4;
        puzzle8[1][1] = p5;
        puzzle8[1][2] = p6;
        puzzle8[2][0] = p7;
        puzzle8[2][1] = p8;
        puzzle8[2][2] = p9;

        whereIsZero = new Punkt();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle8[i][j] == 0) {
                    whereIsZero.x = j;
                    whereIsZero.y = i;
                }
            }
        }


    }


    public Puzzle(Puzzle oryginal) {
        puzzle8 = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle8[i][j] = oryginal.puzzle8[i][j];
            }
        }

        whereIsZero = new Punkt(oryginal.whereIsZero.x, oryginal.whereIsZero.y);
    }


    public void randomPuzzle() {
        List<Integer> temp = new ArrayList<>(9);

        for (int i = 0; i <= 8; i++)
            temp.add(i);

        Random generator = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int remove = generator.nextInt(temp.size());
                puzzle8[i][j] = temp.get(remove);

                if (temp.get(remove) == 0) {
                    whereIsZero.x = j;
                    whereIsZero.y = i;
                }

                temp.remove(remove);

            }
        }

    }


    public void algorithmHillClimbing(Puzzle start ,Puzzle goal)
    {
        String[] tempRun = new String[4];
        tempRun[0] = "calcUp";
        tempRun[1] = "calcDown";
        tempRun[2] = "calcLeft";
        tempRun[3] = "calcRight";

        ManhattanDistance distance = new ManhattanDistance();
        distance.calculationDistanceManhattan(start, goal);

        int min = distance.distance;
        boolean looprun = true;

        System.out.print("Start 8-puzzle:\n" + start);
        System.out.println("Manhattan distance: " + distance.distance);

        do
            {
                Map<String,Integer> candidates = new HashMap<>();
                String run = "start";

                candidates.put("calcUp", start.calcUp(start,goal));
                System.out.print("\n\nKoszt przesunięcia w górę: " + start.calcUp(start,goal));
                candidates.put("calcDown", start.calcDown(start,goal));
                System.out.print(" ;w dół: " + start.calcDown(start,goal));
                candidates.put("calcLeft", start.calcLeft(start,goal));
                System.out.print(" ;w lewo: " + start.calcLeft(start,goal));
                candidates.put("calcRight", start.calcRight(start,goal));
                System.out.print(" ;w prawo: " + start.calcRight(start,goal));

                for (int i =0; i < 4; i++)
                {
                    if(candidates.get(tempRun[i])  != -1)
                    {
                        if (min > candidates.get(tempRun[i]))
                        {
                            min = candidates.get(tempRun[i]);
                            run = tempRun[i];
                        }
                    }
                }

                if(run.equals("start"))
                {
                    System.out.println("\n\nAlgorithm Hill-climbing nie rozwiąże już problemu 8-puzzle,\nponieważ znalazł minimum lokalne.");
                    looprun = false;
                }
                else
                {
                    if (run.equals("calcUp"))
                    {
                        start.runUp(start);
                        System.out.print("\nPrzesunięcie w górę; \n" + start);
                    }
                    else if(run.equals("calcDown"))
                    {
                        start.runDown(start);
                        System.out.print("\nPrzesunięcie w dół; \n" + start);
                    }
                    else if(run.equals("calcLeft"))
                    {
                        start.runLeft(start);
                        System.out.print("\nPrzesunięcie w lewo; \n" + start);
                    }
                    else
                    {
                        start.runRight(start);
                        System.out.print("\nPrzesunięcie w prawo; \n" + start);
                    }

                    ManhattanDistance d = new ManhattanDistance();
                    d.calculationDistanceManhattan(start, goal);
                    System.out.println("Manhattan distance: " + d.distance);

                    if (d.distance == 0)
                    {
                        System.out.println("\nAlgorytm Hill-climbing rozwiazał problem 8-puzzle.");
                        looprun = false;
                    }


                }

            } while (looprun);
    }



    public int calcUp(Puzzle start, Puzzle goal)     // calcUp zwraca -1 jeśli ruch w górę jest niemożliwy, a jeśli jest mozliwy robi ruch w górę i oblicza dla niego ocenę sumy odległości na Manhattanie
    {
        Puzzle tempStart = new Puzzle(start);
        int temp = tempStart.whereIsZero.y - 1;

        if(verificationRun(temp))
        {
            tempStart.runUp(tempStart);
            ManhattanDistance distance = new ManhattanDistance();
            distance.calculationDistanceManhattan(tempStart, goal);
            return distance.distance;
        }
        else
            return -1;
    }

    public int calcDown(Puzzle start, Puzzle goal)     // calcDown zwraca -1 jeśli ruch w dół jest niemożliwy, a jeśli jest mozliwy robi ruch w dół i oblicza dla niego ocenę sumy odległości na Manhattanie
    {
        Puzzle tempStart = new Puzzle(start);
        int temp = tempStart.whereIsZero.y + 1;

        if(verificationRun(temp))
        {
            tempStart.runDown(tempStart);
            ManhattanDistance distance = new ManhattanDistance();
            distance.calculationDistanceManhattan(tempStart, goal);
            return distance.distance;
        }
        else
            return -1;
    }

    public int calcLeft(Puzzle start, Puzzle goal)     // calcLeft zwraca -1 jeśli ruch w lewo jest niemożliwy, a jeśli jest mozliwy robi ruch w lewo i oblicza dla niego ocenę sumy odległości na Manhattanie
    {
        Puzzle tempStart = new Puzzle(start);

        int temp = tempStart.whereIsZero.x - 1;

        if(verificationRun(temp))
        {
            tempStart.runLeft(tempStart);
            ManhattanDistance distance = new ManhattanDistance();
            distance.calculationDistanceManhattan(tempStart, goal);
            return distance.distance;
        }
        else
            return -1;
    }

    public int calcRight(Puzzle start, Puzzle goal)     // calcRight zwraca -1 jeśli ruch w prawo jest niemożliwy, a jeśli jest mozliwy robi ruch w prawo i oblicza dla niego ocenę sumy odległości na Manhattanie
    {
        Puzzle tempStart = new Puzzle(start);
        int temp = tempStart.whereIsZero.x + 1;

        if(verificationRun(temp))
        {
            tempStart.runRight(tempStart);
            ManhattanDistance distance = new ManhattanDistance();
            distance.calculationDistanceManhattan(tempStart, goal);
            return distance.distance;
        }
        else
            return -1;
    }

    public void runUp(Puzzle s)
    {
        int temp = s.puzzle8[s.whereIsZero.y][s.whereIsZero.x];

        s.puzzle8[whereIsZero.y][s.whereIsZero.x] = s.puzzle8[whereIsZero.y - 1][s.whereIsZero.x];
        s.puzzle8[whereIsZero.y - 1][s.whereIsZero.x] = temp;
        s.whereIsZero.y--;
    }

    public void runDown(Puzzle s)
    {
        int temp = s.puzzle8[s.whereIsZero.y][s.whereIsZero.x];

        s.puzzle8[whereIsZero.y][s.whereIsZero.x] = s.puzzle8[whereIsZero.y + 1][s.whereIsZero.x];
        s.puzzle8[whereIsZero.y + 1][s.whereIsZero.x] = temp;
        s.whereIsZero.y++;
    }

    public void runLeft(Puzzle s)
    {
        int temp = s.puzzle8[s.whereIsZero.y][s.whereIsZero.x];

        s.puzzle8[whereIsZero.y][s.whereIsZero.x] = s.puzzle8[whereIsZero.y][s.whereIsZero.x - 1];
        s.puzzle8[whereIsZero.y][s.whereIsZero.x - 1] = temp;
        s.whereIsZero.x--;
    }

    public void runRight(Puzzle s)
    {
        int temp = s.puzzle8[s.whereIsZero.y][s.whereIsZero.x];

        s.puzzle8[whereIsZero.y][s.whereIsZero.x] = s.puzzle8[whereIsZero.y][s.whereIsZero.x + 1];
        s.puzzle8[whereIsZero.y][s.whereIsZero.x + 1] = temp;
        s.whereIsZero.x++;
    }

    public boolean verificationRun(int xy)  // zwraca true jeśli ruch jest możliwy, a jesli jest niemożliwy zwraca false
    {
        if (0 <= xy && xy <= 2)
            return true;
         else
            return false;

    }

    @Override
    public String toString() {
        StringBuilder drawPuzzle = new StringBuilder("-------------\n");

        for (int i = 0; i < 3; i++) {
            drawPuzzle.append("| ");
            for (int j = 0; j < 3; j++) {

                if (puzzle8[i][j] != 0)
                    drawPuzzle.append(puzzle8[i][j]);
                else
                    drawPuzzle.append(" ");

                drawPuzzle.append(" | ");
            }
            drawPuzzle.append("\n-------------\n");
        }

        return drawPuzzle.toString();
    }

}
