package com.company;

public class SelfQuickUnionUF {
  private int[] structure;

  public SelfQuickUnionUF(int n) {

     structure = new int[n];
    // initialize so that all is independent (set to own index)
    for (int i = 0; i < n; i++) {
      structure[i] = i;
    }
  }

  public boolean connected(int p, int q) {
    return findRoot(p) == findRoot(q);
  }

  public void union(int p, int q) {
    int rootP = findRoot(p);
    int rootQ = findRoot(q);

    structure[rootP] = rootQ;
  }

  public int findRoot(int el) {
    while (structure[el] != el) {
      el = structure[el];
    }
    return el;
  }

  public static void main(String[] args) {
      SelfQuickUnionUF quickUn = new SelfQuickUnionUF(10);
      quickUn.union(4, 3);
      quickUn.union(3, 8);
      quickUn.union(6, 5);
      quickUn.union(9, 4);
      quickUn.union(2, 1);
      quickUn.union(5, 0);
      quickUn.union(7, 2);
      quickUn.union(6, 1);
      quickUn.union(7, 3);

      String test = "";
      for(int i=0; i < quickUn.structure.length; i++) {
        test += " | " + quickUn.structure[i] + " | ";
      }
    System.out.println(test);
  }
}
