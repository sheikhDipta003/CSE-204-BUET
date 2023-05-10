package offline_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class BSTNode<E>{
    private E element;
    private BSTNode<E> left;
    private BSTNode<E> right;

    BSTNode(){
        left = right = null;
    }
    BSTNode(E val){
        element = val;
        left = right = null;
    }

    public E getElement() {
        return element;
    }
    public void setElement(E val) {
        element = val;
    }
    public BSTNode<E> left() {
        return left;
    }
    public void setLeft(BSTNode<E> l){
        left = l;
    }
    public BSTNode<E> right() {
        return right;
    }
    public void setRight(BSTNode<E> r){
        right = r;
    }
    public boolean isLeaf() {
        return (left == null && right == null);
    }
}

class BST<E extends Comparable<? super E>>{

    private BSTNode<E> root;       //root of the BST
    private int nodeCount;              //number of nodes in the BST

    BST(){
        root = null;
        nodeCount = 0;
    }

    public void clear() {
        root = null;
        nodeCount = 0;
    }

    public void insert(E e) {
        root = inserthelp(root, e);
        nodeCount++;
        printhelp(root);
        System.out.println();
    }

    public E remove(E e) {
        E temp = findhelp(root, e);
        if (temp != null) {
            root = removehelp(root, e);
            nodeCount--;
        }
        printhelp(root);
        System.out.println();
        return temp;
    }

    public E find(E e) {
        return findhelp(root, e);
    }

    public int size() {
        return nodeCount;
    }

    //helper function to facilitate find method
    private E findhelp(BSTNode<E> rt, E e){
        if(rt == null)  return null;

        if(rt.getElement().compareTo(e) > 0){
            return findhelp(rt.left(), e);
        } else if(rt.getElement().compareTo(e) == 0)    return rt.getElement();
        else {
            return findhelp(rt.right(), e);
        }
    }

    //helper function to facilitate insert() method
    private BSTNode<E> inserthelp(BSTNode<E> rt, E e){
        if(rt == null)  return new BSTNode<E>(e);
        if(rt.getElement().compareTo(e) > 0){
            rt.setLeft(inserthelp(rt.left(), e));
        } else {
            rt.setRight(inserthelp(rt.right(), e));
        }
        return rt;
    }

    //helper function for remove() method
    private BSTNode<E> removehelp(BSTNode<E> rt, E e){
        if(rt.getElement().compareTo(e) > 0){
            rt.setLeft(removehelp(rt.left(), e));
        } else if(rt.getElement().compareTo(e) < 0){
            rt.setRight(removehelp(rt.right(), e));
        } else {
            if(rt.left() == null)   return rt.right();
            else if(rt.right() == null)     return rt.left();
            else {
                BSTNode<E> temp = getMax(rt.left());
                rt.setElement(temp.getElement());
                rt.setLeft(deleteMax(rt.left()));
            }
        }
        return rt;
    }

    //helper function to find the maximum element of a BST
    private BSTNode<E> getMax(BSTNode<E> rt){
        if(rt.right() == null)   return rt;
        return getMax(rt.right());
    }

    //helper function to delete maximum element of a BST
    private BSTNode<E> deleteMax(BSTNode<E> rt){
        if(rt.right() == null)   return rt.left();
        rt.setRight(deleteMax(rt.right()));
        return rt;
    }

    //helper function to help print the BST
    private void printhelp(BSTNode<E> rt){
        if(rt != root)  System.out.print("(" + rt.getElement());
        else    System.out.print(rt.getElement());

        if(rt.left() != null)   printhelp(rt.left());
        else {
            if(!rt.isLeaf())    System.out.print("()");
        }

        if(rt.right() != null)   printhelp(rt.right());
        else {
            if(!rt.isLeaf())    System.out.print("()");
        }

        if(rt != root)  System.out.print(")");
    }

    //preorder traversal
    public void printPreorder(){
        traversePreorder(root);
        System.out.println();
    }
    private void traversePreorder(BSTNode<E> rt){
        if(rt == null){
            return;
        }
        System.out.print(rt.getElement() + " ");
        traversePreorder(rt.left());
        traversePreorder(rt.right());
    }

    //inorder traversal
    public void printInorder(){
        traverseInorder(root);
        System.out.println();
    }
    private void traverseInorder(BSTNode<E> rt){
        if(rt == null){
            return;
        }
        traverseInorder(rt.left());
        System.out.print(rt.getElement() + " ");
        traverseInorder(rt.right());
    }

    //postorder traversal
    public void printPostorder(){
        traversePostorder(root);
        System.out.println();
    }
    private void traversePostorder(BSTNode<E> rt){
        if(rt == null){
            return;
        }
        traversePostorder(rt.left());
        traversePostorder(rt.right());
        System.out.print(rt.getElement() + " ");
    }
}

public class BST_implementation {
    public static void main(String[] args) throws IOException {
        BST<Integer> bst = new BST<>();

        Reader fr = new FileReader("inputBST.txt");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s == null) break;

            String cmd = s.split(" ")[0];
            String param = s.split(" ")[1];

            if(cmd.equals("D")){
                if(bst.remove(Integer.valueOf(param)) == null){
                    System.out.println("Invalid Operation");
                }
            }
            else if(cmd.equals("F")){
                if(bst.find(Integer.valueOf(param)) != null)    System.out.println("True");
                else    System.out.println("False");
            }
            else if(cmd.equals("I")){
                bst.insert(Integer.valueOf(param));
            }
            else if(cmd.equals("T")){
                if(param.equals("In")){
                    bst.printInorder();
                } else if(param.equals("Pre")){
                    bst.printPreorder();
                } else if(param.equals("Post")){
                    bst.printPostorder();
                }
            }
        }
        br.close();
        fr.close();
    }
}
