package com.kowalczyk;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

class ManhattanDistance
{
    int distance;

    public ManhattanDistance()
    {
        this.distance=0;
    }

    void calculationDistanceManhattan(Puzzle start, Puzzle goal)
    {
        Map<Integer,Punkt> s = new HashMap<>();
        Map<Integer,Punkt> g = new HashMap<>();

        for (int k = 1; k <= 8; k++)
        {
            for (int i = -1; i >= -3; i--)
            {
                for (int j = 1; j <= 3; j++)
                {
                    if (start.puzzle8[abs(i)-1][j-1] == k)
                    {
                        s.put(k,new Punkt(j,i));
                    }

                    if (goal.puzzle8[abs(i)-1][j-1] == k)
                    {
                        g.put(k,new Punkt(j,i));
                    }
                }
            }
        }

        for (int i = 1; i <= 8; i++)
        {
            int tempx = abs(s.get(i).x - g.get(i).x);
            int tempy = abs(s.get(i).y - g.get(i).y);
            int tempsum = tempx + tempy;
            distance = distance + tempsum;
        }
    }

}
