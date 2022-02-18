package com.company;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int nval;
  private int trialsVal;
  private double[] percolationThreshold;
  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    nval = n;
    trialsVal = trials;
    percolationThreshold = new double[trials];

    int trialNum = 0;
    for (int i = 0; i < trials; i++) {
      Percolation perc = new Percolation(n);
      while (true) {
        // randomly open site
        int randomSite = StdRandom.uniform(1, (n * n) + 1);
        int remainder = randomSite % nval;
        int divResult = randomSite / nval;
        int col, row;
        if (remainder != 0) {
          row = divResult + 1;
          col = remainder;
        } else {
          row = divResult;
          col = nval;
        }
        perc.open(col, row);
        if (perc.percolates()) {
          break;
        }
      }
      percolationThreshold[i] = perc.numberOfOpenSites() / (double) (n * n);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(percolationThreshold);
  }
  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(percolationThreshold);
  }
  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(trialsVal));

  }
  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(trialsVal));
  }
  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.valueOf(args[0]);
    int trials = Integer.valueOf(args[1]);

    PercolationStats percStat = new PercolationStats(n, trials);
    System.out.println("mean = " + percStat.mean());
    System.out.println("stddev = " + percStat.stddev());
    System.out.println(
        "95% Confidence interval = ["
            + percStat.confidenceLo()
            + ","
            + percStat.confidenceHi()
            + "]");
  }
}
