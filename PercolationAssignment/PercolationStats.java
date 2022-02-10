package com.company;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int nval;
    private int trialsVal;
    private int[] openSitesAtPerc;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if(n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }
        nval = n;
        trialsVal = trials;
        openSitesAtPerc = new int[trials];
        Percolation perc = new Percolation(n);

        int trialNum = 0;
        for(int i=0; i<trials; i++) {
            while(!perc.percolates() ){
                //randomly open site
                int randomSite = StdRandom.uniform(n*n);
                int reminder =  randomSite % nval;
                int divResult = randomSite / nval;
                int col, row;
                if(reminder != 0) {
                    row = divResult + 1;
                    col = reminder;
                }  else {
                    row = divResult;
                    col = nval;
                }
                perc.open(col,row);
            }
            openSitesAtPerc[i] = perc.numberOfOpenSites();
        }

    }

    // sample mean of percolation threshold
    public double mean()
    {
        throw new IllegalArgumentException();
    }
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        throw new IllegalArgumentException();
    }
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        throw new IllegalArgumentException();
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        throw new IllegalArgumentException();
    }
    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);


    }
}
