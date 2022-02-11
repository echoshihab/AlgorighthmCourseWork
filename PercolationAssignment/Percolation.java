package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF quf;
  private final int[] parallelStrucure;
  private final int virtualTopSiteIndex;
  private final int virtualBottomSiteIndex;
  private int openSites;
  private final int size;
  private final int qufSize;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    size = n;
    // add 2 for two additional virtual site
    qufSize = (n * n) + 2;
    virtualTopSiteIndex = qufSize - 1 - 1;
    virtualBottomSiteIndex = qufSize - 1;

    // this parallel structure will be our representation of the grid
    parallelStrucure = new int[n * n];
    int parrallelStructureLength = n * n;

    // lets set all slots to -1 to mark them as closed
    for (int i = 0; i < parrallelStructureLength; i++) {
      parallelStrucure[i] = -1;
    }
    quf = new WeightedQuickUnionUF(qufSize);
  }

  public void open(int row, int col) {

    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;
    // only open site if the site is closed
    if (parallelStrucure[siteIndexValue] == -1) {
      // open site
      parallelStrucure[siteIndexValue] = siteIndexValue;
      openSites += 1;
      // if this is on top row connect them to virtual site
      if (site <= size) {
        quf.union(virtualTopSiteIndex, siteIndexValue);
      }
      // if this is bottom row, connect it to virtual bottom site
      if (site > (size * size) - size) {
        quf.union(virtualBottomSiteIndex, siteIndexValue);
      }
    }

    // lets check if adjacent sites exist
    boolean rightSideExists = site % size != 0;
    boolean leftSideExists = site % size != 1;
    boolean topSideExists = site > size;
    boolean bottomSideExists = site <= (size * size - size);

    // connect if right side is open
    if (rightSideExists && parallelStrucure[siteIndexValue + 1] != -1) {
      quf.union(siteIndexValue, siteIndexValue + 1);
    }
    // connect if left side is open
    if (leftSideExists && parallelStrucure[siteIndexValue - 1] != -1) {
      quf.union(siteIndexValue, siteIndexValue - 1);
    }
    // connect if top side is open
    if (topSideExists && parallelStrucure[siteIndexValue - size] != -1) {
      quf.union(siteIndexValue, siteIndexValue - size);
    }
    // connect if bottom side is open
    if (bottomSideExists && parallelStrucure[siteIndexValue + size] != -1) {
      quf.union(siteIndexValue, siteIndexValue + size);
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;
    return parallelStrucure[siteIndexValue] != -1;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    int site = (size * (row - 1)) + col;
    int siteIndexValue = site - 1;

    // A full site is an open site that can be
    // connected to an open site in the top
    // row via a chain of neighboring (left, right, up, down)
    // open sites.
    return quf.find(siteIndexValue) == quf.find(virtualTopSiteIndex);
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
