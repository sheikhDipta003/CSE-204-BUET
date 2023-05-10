package offline_3;

import java.util.ArrayList;
import java.util.Scanner;

class AQueue<E> implements Queue<E>{

    private static int defaultSize = 10;
    private int front;                                                                                 //0 <= front <= n
    private int rear;                                                                                   //0 <= rear <= n
    private int maxSize;
    private E[] listArray;

    AQueue(){
        this(defaultSize);
    }
    AQueue(int size){
        maxSize = size+1;
        listArray = (E[]) new Object[maxSize];
        front = 1;
        rear = 0;
    }
    AQueue(E[] arr){
        maxSize = arr.length+1;
        listArray = arr;
        front = 1;
        rear = 0;
    }

    @Override
    public void clear() {
        front = 1;
        rear = 0;
    }

    @Override
    public void enqueue(E item) {
        if((rear + 2) % maxSize == front) {                                                       //if the queue is full
            E[] temp = listArray;
            int f = this.front;
            maxSize *= 2;
            listArray = (E[]) new Object[maxSize];

            for(int i = front; (i-front+1) < (temp.length-1); i++){
                listArray[i] = temp[i];
            }
        }
        rear = (rear + 1) % maxSize;
        listArray[rear] = item;
    }

    @Override
    public E dequeue() {
        if(this.length() == 0)   return null;

        E item = listArray[front];
        front = (front + 1) % maxSize;
        return item;
    }

    @Override
    public E frontValue() {
        if(this.length() == 0)  return null;
        return listArray[front];
    }

    @Override
    public E rearValue() {
        if(this.length() == 0)  return null;
        return listArray[rear];
    }

    @Override
    public E leaveQueue() {
        if(this.length() == 0)   return null;
        E item = listArray[rear];
        rear = (rear - 1) % maxSize;
        return item;
    }

    @Override
    public int length() {
        return (rear - front + 1 + maxSize) % maxSize;
    }
}
