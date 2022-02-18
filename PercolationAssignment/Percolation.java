package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF quf;
  private final WeightedQuickUnionUF qufWithoutBackwash;
  private final boolean[] parallelStructure;
  private final int virtualTopSiteIndex;
  private final int virtualTopSiteIndexNonBackwash;
  private final int virtualBottomSiteIndex;
  private int openSites;
  private final int size;
  private final int qufSize;
  private final int qufSizeWithoutBackwash;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    size = n;
    // add 2 for two additional virtual site
    qufSize = (n * n) + 2;
    virtualTopSiteIndex = qufSize - 2;
    virtualBottomSiteIndex = qufSize - 1;

    // add 1 for one additional virtual site
    qufSizeWithoutBackwash = (n * n) + 1;
    virtualTopSiteIndexNonBackwash = qufSizeWithoutBackwash - 1;

    // this parallel structure will be our representation of the grid
    //use boolean array here to take advantage of default initialization value of false;
    parallelStructure = new boolean[n * n];

    quf = new WeightedQuickUnionUF(qufSize);
    qufWithoutBackwash = new WeightedQuickUnionUF(n * n + 1);
  }

  public void open(int row, int col) {

    if (row < 1 || col < 1 || row > size || col > size) throw new IllegalArgumentException();

    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;
    // only open site if the site is closed
    if (!parallelStructure[siteIndexValue]) {
      // open site
      parallelStructure[siteIndexValue] = true;
      openSites += 1;
      // if this is on top row connect them to virtual site
      if (site <= size) {
        quf.union(virtualTopSiteIndex, siteIndexValue);
        qufWithoutBackwash.union(virtualTopSiteIndexNonBackwash, siteIndexValue);
      }
      // if this is bottom row, connect it to virtual bottom site
      // only for one with backwash
      if (site > (size * size) - size) {
        quf.union(virtualBottomSiteIndex, siteIndexValue);
      }
    }

    // lets check if adjacent sites exist
    boolean rightSideExists = (site % size != 0) && size > 1;
    boolean leftSideExists = (site % size != 1) && size > 1;
    boolean topSideExists = site > size && size >  1 ;
    boolean bottomSideExists = site <= (size * size - size) && size > 1;

    // connect if right side is open
    if (rightSideExists && parallelStructure[siteIndexValue + 1]) {
      quf.union(siteIndexValue, siteIndexValue + 1);
      qufWithoutBackwash.union(siteIndexValue, siteIndexValue + 1);
    }
    // connect if left side is open
    if (leftSideExists && parallelStructure[siteIndexValue - 1]) {
      quf.union(siteIndexValue, siteIndexValue - 1);
      qufWithoutBackwash.union(siteIndexValue, siteIndexValue - 1);
    }
    // connect if top side is open
    if (topSideExists && parallelStructure[siteIndexValue - size]) {
      quf.union(siteIndexValue, siteIndexValue - size);
      qufWithoutBackwash.union(siteIndexValue, siteIndexValue - size);
    }
    // connect if bottom side is open
    if (bottomSideExists && parallelStructure[siteIndexValue + size]) {
      quf.union(siteIndexValue, siteIndexValue + size);
      qufWithoutBackwash.union(siteIndexValue, siteIndexValue + size);
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {

    if (row < 1 || col < 1 || row > size || col > size) throw new IllegalArgumentException();

    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;
    return parallelStructure[siteIndexValue];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {

    if (row < 1 || col < 1 || row > size || col > size) throw new IllegalArgumentException();

    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;

    // A full site is an open site that can be
    // connected to an open site in the top
    // row via a chain of neighboring (left, right, up, down)
    // open sites.
    return qufWithoutBackwash.find(siteIndexValue) == qufWithoutBackwash.find(virtualTopSiteIndexNonBackwash);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return quf.find(virtualTopSiteIndex) == quf.find(virtualBottomSiteIndex);
  }

  // test client (optional)
  public static void main(String[] args) {
    PercolationVisualizer.main(args);
  }
}
