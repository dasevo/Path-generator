package com.team3630.util;

import java.io.IOException;

import com.team3630.splineGenerator.Trajectory;

public class ArrayGeneration
{

    static Trajectory left, right;
    static double[] leftPos, leftVel, leftAcc, leftHead, leftX, leftY, rightPos, rightVel, rightAcc, rightHead, rightX, rightY;
    static FileGenerator generator;

    public ArrayGeneration() throws IOException
    {
        generator = new FileGenerator();
    }

    public ArrayGeneration(Trajectory leftIn, Trajectory rightIn) throws IOException
    {
        generator = new FileGenerator();
        left = leftIn;
        right = rightIn;
        leftPos = new double[leftIn.getLength()];
        leftVel = new double[leftIn.getLength()];
        leftAcc = new double[leftIn.getLength()];
        leftHead = new double[leftIn.getLength()];
        leftX = new double[leftIn.getLength()];
        leftY = new double[leftIn.getLength()];
        rightPos = new double[rightIn.getLength()];
        rightVel = new double[rightIn.getLength()];
        rightAcc = new double[rightIn.getLength()];
        rightHead = new double[rightIn.getLength()];
        rightX = new double[rightIn.getLength()];
        rightY = new double[rightIn.getLength()];
    }

    public static void setArrays()
    {
        for (int i = 0; i < leftPos.length; i++)
        {
            leftPos[i] = left.getSegment(i).pos;
            leftVel[i] = left.getSegment(i).vel;
            leftAcc[i] = left.getSegment(i).acc;
            leftHead[i] = left.getSegment(i).heading;
            leftX[i] = left.getSegment(i).x;
            leftY[i] = left.getSegment(i).y;
        }

        for(int i = 0; i < rightPos.length; i++)
        {
            rightPos[i] = right.getSegment(i).pos;
            rightVel[i] = right.getSegment(i).vel;
            rightAcc[i] = right.getSegment(i).acc;
            rightHead[i] = right.getSegment(i).heading;
            rightX[i] = right.getSegment(i).x;
            rightY[i] = right.getSegment(i).y;
        }

        //System.out.println(leftX[200] + "   " + rightX[200]);

    }

    public static void printArrays()
    {
        System.out.println("leftPos");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftPos[i] + ",");
        }
        System.out.println("leftVel");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftVel[i] + ",");
        }
        System.out.println("leftAcc");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftAcc[i] + ",");
        }
        System.out.println("leftHead");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftHead[i] + ",");
        }
        System.out.println("leftX");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftX[i] + ",");
        }
        System.out.println("leftY");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(leftY[i] + ",");
        }
        System.out.println("rightPos");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(rightPos[i] + ",");
        }
        System.out.println("rightVel");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(rightVel[i] + ",");
        }
        System.out.println("rightAcc");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(rightAcc[i] + ",");
        }
        System.out.println("rightHead");
        System.out.println();
        for(int i = 0; i < left.getLength(); i++)
        {
            System.out.print(rightHead[i] + ",");
        }

    }

    public static void writeData() throws IOException
    {
        
        //generator.writeFileAll(leftX, leftY, leftPos, leftVel, leftAcc, rightX, rightY, rightPos, rightVel, rightAcc);

        generator.writeArrays(leftX, leftY, leftPos, leftVel, leftAcc, rightX, rightY, rightPos, rightVel, rightAcc);

        generator.finished();
    }

    public static void writeLine(String print) throws IOException
    {
        generator.writeLine(print);
        
    }
    


}