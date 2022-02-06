package com.company;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF quf;
    private int[] parallelStrucure;
    private int virtualTopSite;
    private int openSites;
    private int size;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        int qufSize = (n * n) +1;

        //this parallel structure will be our representation of the grid
        parallelStrucure  = new int[n * n];

        // lets set all slots to -1 to mark them as closed
        for (int i=0; i < size; i++){
            parallelStrucure[i] = -1;
        }
        quf = new WeightedQuickUnionUF(qufSize);

    }

    public void open(int row, int col) {

        int site = row * col;
        int siteIndexValue = site -1;
        //only open site if the site is closed
        if(parallelStrucure[siteIndexValue] == -1) {
            //open site
            parallelStrucure[siteIndexValue] = siteIndexValue;
            openSites +=1;
            //if this is on top row connect them to virtual site
            if(site <= size) {
               //To do
            }


            //if there are any adjacent open site we need to connect them

            //lets check if adjacent sites exist
            boolean rightSideExists = (site + 1) % size  != 0;
            boolean leftSideExists =  (site -1 ) % size  != 0;
            boolean topSideExists = (site - size) % size > 0;
            boolean bottomSideExists = (site + size) % size > 0 && (site + size)/ size <= size;

            //connect if right side is open
            if(rightSideExists && parallelStrucure[siteIndexValue + 1] != -1 ) {
                quf.union(siteIndexValue, siteIndexValue + 1);
            }
            //connect if left side is open
            if(leftSideExists && parallelStrucure[siteIndexValue - 1] != -1 ) {
                quf.union(siteIndexValue, siteIndexValue - 1);
            }
            //connect if top side is open
            if (topSideExists && parallelStrucure[siteIndexValue + size] != -1) {
                quf.union(siteIndexValue, siteIndexValue+size);
            }
            //connect if bottom side is open
            if(bottomSideExists && parallelStrucure[siteIndexValue + size] != -1) {
                quf.union(siteIndexValue, siteIndexValue+size);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
       if(parallelStrucure[(row * col) -1] != -1) {
           return true;
       }
       return false;

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int site = row * col;
        int siteIndexValue = site -1;

        //A full site is an open site that can be
        // connected to an open site in the top
        // row via a chain of neighboring (left, right, up, down)
        // open sites.


    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {

        throw new InvalidOpenTypeException();
    }

    // test client (optional)
    public static void main(String[] args) {
        throw new InvalidOpenTypeException();
    }

}
