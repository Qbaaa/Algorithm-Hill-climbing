package com.kowalczyk;

public class Main
{

    public static void main(String[] args)
    {

        Puzzle puzzle8Start = new Puzzle();
        //Puzzle puzzle8Start = new Puzzle(1,2,3,4,8,0,7,6,5);
        Puzzle puzzle8Goal = new Puzzle(0,1,2,3,4,5,6,7,8);

        //Puzzle puzzle8Goal = new Puzzle(1,2,3,4,5,6,7,8,0);

        puzzle8Start.algorithmHillClimbing(puzzle8Start,puzzle8Goal);

        //System.out.println(puzzle8Start.calcUp(puzzle8Start, puzzle8Goal));


      //  Puzzle puzzle8StartTest = new Puzzle(0,1,2,3,4,5,6,7,8);
      //  Puzzle puzzle8GooalTest = new Puzzle(3,0,2,1,4,5,6,7,8);

        // System.out.println("Start\n" + puzzle8StartTest);
       // System.out.println("Goal\n" + puzzle8GooalTest);

        //ManhattanDistance d = new ManhattanDistance();
        //d.calculationDistanceManhattan(puzzle8StartTest,puzzle8GooalTest);

        //System.out.println("Dis: " + d.distance);

        //Puzzle puzzle8StartTest2 = new Puzzle(1,2,3,4,0,6,7,5,8);

        //System.out.println("Test\n" + puzzle8StartTest2);

        //puzzle8StartTest2.runRight(puzzle8StartTest2);

        //System.out.println("R\n" + puzzle8StartTest2);

    }
}
