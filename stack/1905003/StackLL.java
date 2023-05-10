package offline_2;

import java.util.ArrayList;
import java.util.Scanner;

class Link<E>{
    private E element;
    private Link<E> next;

    Link(E item, Link<E> nextVal){
        element = item;
        next = nextVal;
    }
    Link(Link<E> nextVal){
        next = nextVal;
    }

    public E getElement() {
        return element;
    }

    public Link<E> getNext() {
        return next;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Link<E> setNext(Link<E> next) {
        return this.next = next;
    }
}

class LL<E> implements Stack<E>{

    private Link<E> top;
    private int size;

    LL(){
        top = null;
        size = 0;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E item) {
        top = new Link<E>(item, top);
        size++;
    }

    @Override
    public E pop() {
        if (this.length() == 0)   return null;
        else {
            E item = top.getElement();
            top = top.getNext();
            size--;
            return item;
        }
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public E topValue() {
        if (this.length() == 0)   return null;
        else {
            return top.getElement();
        }
    }

    @Override
    public void setDirection(int direction) {}
}
