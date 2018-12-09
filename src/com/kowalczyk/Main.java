package com.kowalczyk;

import java.util.ArrayList;
import java.util.List;

public class Main
{

    public static void main(String[] args)
    {

        Puzzle puzzle8Start = new Puzzle();
        Puzzle puzzle8Goal = new Puzzle(0,1,2,3,4,5,6,7,8);

       puzzle8Start.algorithmHillClimbing(puzzle8Start,puzzle8Goal);
    }
}
