package com.team3630.util;

import java.io.FileWriter;
import java.io.IOException;;

public class FileGenerator
{
    static FileWriter fw;

    public FileGenerator() throws IOException
    {
        fw = new FileWriter("/Users/dasevo/vscode-workspace/generated.txt");
    }

    public static void writeFileAll(double[] leftX, double[] leftY, double[] leftPos, double[] leftVel, double[] leftAcc, 
    double[] rightX, double[] rightY, double[] rightPos, double[] rightVel, double[] rightAcc) throws IOException
    {
        for(int i = 0; i<leftY.length; i++)
        {
            String a = Double.toString(leftX[i]);
            String b = Double.toString(leftY[i]);
            String c = Double.toString(leftPos[i]);
            String d = Double.toString(leftVel[i]);
            String e = Double.toString(leftAcc[i]);
            String f = Double.toString(rightX[i]);
            String g = Double.toString(rightY[i]);
            String h = Double.toString(rightPos[i]);
            String j = Double.toString(rightVel[i]);
            String k = Double.toString(rightAcc[i]);
            fw.write(a + ";" + b + ";" + c + ";" + d + ";" + e + ";" + f + ";" + g + ";" + h + ";" + j + ";" + k + "\n");
        }
    }

    public static void writeLine(String toPrint) throws IOException
    {
        fw.write(toPrint);
    }

    public static void writeArrays(double[] leftX, double[] leftY, double[] leftPos, double[] leftVel, double[] leftAcc, 
    double[] rightX, double[] rightY, double[] rightPos, double[] rightVel, double[] rightAcc) throws IOException
    {
        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(leftPos[i]) + ",");
        }
        fw.write("}; \n\n");

        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(leftVel[i]) + ",");
        }
        fw.write("}; \n\n");

        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(leftAcc[i]) + ",");
        }
        fw.write("}; \n\n");

        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(rightPos[i]) + ",");
        }
        fw.write("}; \n\n");

        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(rightVel[i]) + ",");
        }
        fw.write("}; \n\n");

        fw.write("{");
        for(int i = 0; i < leftY.length; i++)
        {
            fw.write(Double.toString(rightAcc[i]) + ",");
        }
        fw.write("}; \n\n");
    }

    public static void finished() throws IOException
    {
        fw.close();
    }
}