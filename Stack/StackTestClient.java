package com.company;

import edu.princeton.cs.algs4.StdIn;

import java.util.Scanner;

public class StackTestClient {
    public static void main(String[] args){
        StackStringsLinked stack = new StackStringsLinked();
        int i = 0;
        while(i < args.length) {
            String s = args[i];
            if(s.equals("-")) {
                System.out.println(stack.pop());
            } else {
                stack.push(s);
            }
            i++;
        }
    }
}

