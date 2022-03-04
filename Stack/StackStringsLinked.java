package com.company;

public class StackStringsLinked {
    private Node firstNode = null;
    private int size = 0;
    private class Node {
        String item; //current
        Node next; //reference to another node
    }

    public void push(String item) {
        Node oldNode = firstNode;
        firstNode = new Node();
        firstNode.item = item;
        firstNode.next = oldNode;
        size++;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public String pop(){
        String poppedItem = firstNode.item;
        firstNode = firstNode.next;
        size--;
        return poppedItem;
    }

    public int size(){
        return size;
    }
}
