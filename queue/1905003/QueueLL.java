package offline_3;

import java.util.ArrayList;
import java.util.Scanner;

class Link<E>{
    private E element;
    private Link<E> next;

    Link(E e, Link<E> n){
        element = e;
        next = n;
    }
    Link(Link<E> n){
        next = n;
    }

    public E getElement(){return element;}
    public Link<E> next(){return next;}
    public E setElement(E e){return element = e;}
    public Link<E> setNext(Link<E> n){return next=n;}
}

class LQueue<E> implements Queue<E>{

    private Link<E> front;
    private Link<E> rear;
    private int size;

    LQueue(){
        front = rear = new Link<>(null);
        size = 0;
    }

    @Override
    public void clear() {
        front = rear = new Link<>(null);
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        rear.setNext(new Link<E>(item, null));
        rear = rear.next();
        size++;
    }

    @Override
    public E dequeue() {
        if(this.length() == 0) return null;
        E it = front.next().getElement();
        front.setNext(front.next().next());
        if(front.next()==null)   rear = front.next();
        size--;
        return it;
    }

    @Override
    public E frontValue() {
        if(this.length() == 0) return null;
        return front.next().getElement();
    }

    @Override
    public E rearValue() {
        if(this.length() == 0) return null;
        return rear.getElement();
    }

    @Override
    public E leaveQueue() {
        if(this.length() == 0) return null;
        E item = rear.getElement();
        Link<E> temp = front;
        while(temp.next() != rear){
            temp = temp.next();
        }
        rear = temp;
        temp.setNext(null);
        size--;
        return item;
    }

    @Override
    public int length() {
        return size;
    }

}
