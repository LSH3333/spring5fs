package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

public class MainProxy
{
    public static void main(String[] args)
    {
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
        // ImpeCalculator.factorial 실행
        System.out.println(ttCal1.factorial(20));

        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
        // RecCalculator.factorial 실행
        System.out.println(ttCal2.factorial(20));
    }
}
