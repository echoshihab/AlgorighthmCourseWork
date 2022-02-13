package com.company;

// self attempt at quick find uf implementation
public class SelfQuickFindUF {
  private int[] structure;

  public SelfQuickFindUF(int n) {

    // initialize so that all is independent (set to own index)
    for (int i = 0; i < n; i++) {
      structure[i] = i;
    }
  }

  public boolean connected(int p, int q) {
    return structure[p] == structure[q];
  }

  public void union(int p, int q) {
    if (structure[p] != structure[q]) {
      int pVal = structure[p];
      int qVal = structure[q];
      for (int i = 0; i < structure.length; i++) {
        if (structure[i] == pVal) {
          structure[i] = qVal;
        }
      }
    }
  }
}
