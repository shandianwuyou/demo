package com.my.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by zhaopeng on 2018/2/24.
 */

public class PrintDemo {

    public static void main(String[] args) {

        try {
            File file = new File("C:\\temp\\printstream.txt");

            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(file)), true));

            System.out.println("你好");
            System.out.println("好开心");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
