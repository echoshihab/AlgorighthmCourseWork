package com.company;

public class SelfWeightedQuickUnionUF {
    private int[] structure;
    private int[] size;

    public SelfWeightedQuickUnionUF(int n) {

        structure = new int[n];
        size = new int[n];
        // initialize so that all is independent (set to own index)
        for (int i = 0; i < n; i++) {
            structure[i] = i;
            size[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if(size[rootQ] < size[rootP]) {
            structure[rootQ] = rootP;
            size[rootP]+= size[rootQ];
        } else {
            structure[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

    }

    public int findRoot(int el) {
        while (structure[el] != el) {
            el = structure[el];
        }
        return el;
    }

    public static void main(String[] args) {
        SelfWeightedQuickUnionUF quickUn = new SelfWeightedQuickUnionUF(10);
        quickUn.union(3, 4);
        quickUn.union(3, 8);
        quickUn.union(5, 6);
        quickUn.union(9, 4);
        quickUn.union(1, 2);
        quickUn.union(5, 0);
        quickUn.union(7, 2);
        quickUn.union(1, 6);
        quickUn.union(7, 3);

        String test = "";
        for(int i=0; i < quickUn.structure.length; i++) {
            test += " | " + quickUn.structure[i] + " | ";
        }
        System.out.println(test);
    }
}
