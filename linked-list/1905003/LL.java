package offline_1;

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

class LList<E> implements List<E>{
    private Link<E> head;
    private Link<E> tail;
    private Link<E> curr;
    private int cnt;

    LList(){
        curr = tail = head = new Link<E>(null);
        cnt = 0;
    }
    LList(E[] another_list){     //Y -> number of elements in another_list
        curr = tail = head = new Link<E>(null);
        cnt = 0;

        for(int i = 0 ; i < another_list.length ; i++){
            this.append(another_list[i]);
        }
    }

    @Override
    public void clear() {
        head.setNext(null);
        curr = tail = head = new Link<E>(null);
        cnt = 0;
    }

    @Override
    public void insert(E item) {
        curr.setNext(new Link<E>(item, curr.getNext()));
        if(curr == tail)    tail = curr.getNext();
        cnt++;
    }

    @Override
    public void append(E item) {
        tail = tail.setNext(new Link<E>(item, null));
        cnt++;
    }

    @Override
    public E remove() {
        if(curr.getNext() == null)    return null;  //no element
        E key = curr.getNext().getElement();
        if(tail == curr.getNext())  tail = curr;
        curr.setNext(curr.getNext().getNext());
        cnt--;
        return key;
    }

    @Override
    public void moveToStart() {
        curr = head;
    }

    @Override
    public void moveToEnd() {
        Link<E> temp = head;
        while(temp.getNext() != tail)   temp = temp.getNext();
        curr = temp;
        //curr = tail;
    }

    @Override
    public void prev() {
        if(curr == head)    return;
        Link<E> temp = head;
        while(temp.getNext() != curr)   temp = temp.getNext();
        curr = temp;
    }

    @Override
    public void next() {
        if(curr.getNext() != tail)    curr = curr.getNext();
    }

    @Override
    public int length() {
        return cnt;
    }

    @Override
    public int currPos() {
        int pos;
        Link<E> temp = head;
        for(pos = 0 ; curr != temp; pos++)
            temp = temp.getNext();
        return pos;
    }

    @Override
    public void moveToPos(int pos) {
        if(pos < 0 || pos >= cnt)   return;
        curr = head;
        for(int i = 0 ; i < pos ; i++){
            curr = curr.getNext();
        }
    }

    @Override
    public E getValue() {
        if(curr.getNext() == null)    return null;    //no element
        return curr.getNext().getElement();
    }

    @Override
    public int Search(E item) {
        int keyPos = -1;
        curr = head;
        for(int i = 0 ; curr != tail ; i++){
            curr = curr.getNext();
            if(curr.getNext().getElement() == item){
                keyPos = i;
                break;
            }
        }
        return keyPos;
    }
}

