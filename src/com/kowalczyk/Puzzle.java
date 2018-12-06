package com.kowalczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Puzzle
{
    int[][] puzzle8 = new int[3][3];

    public Puzzle()
    {
        randomPuzzle();
    }

    public void randomPuzzle()
    {
        List<Integer> temp = new ArrayList<>(9);

        for (int i = 0; i <= 8; i++)
            temp.add(i);

        Random generator = new Random();

        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3 ;j++)
            {
                int remove = generator.nextInt(temp.size());
                puzzle8[i][j] = temp.get(remove);

                temp.remove(remove);

            }
        }

    }


    @Override
    public String toString() {
        StringBuilder drawPuzzle = new StringBuilder("-------------\n");

        for (int i = 0; i < 3; i++)
        {
            drawPuzzle.append("| ");
            for (int j = 0; j < 3; j++)
            {
                drawPuzzle.append(puzzle8[i][j]);
                drawPuzzle.append(" | ");
            }
            drawPuzzle.append("\n-------------\n");
        }

        return drawPuzzle.toString();
    }
}
