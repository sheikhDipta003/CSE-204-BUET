package offline_2;

import java.util.ArrayList;
import java.util.Scanner;

class Arr<E> implements Stack<E>{

    private static final int defaultSize = 10;
    private int maxSize;
    private int increment_maxSize;
    private int top_1, top_2;
    private E[] listArray;
    private int direction = 1;  //grows upward from the first position of the allocated array, downward if -1

    Arr(){
        this(defaultSize);
    }
    Arr(int size){
        increment_maxSize = maxSize = size;
        top_1 = 0;
        top_2 = maxSize - 1;
        listArray = (E[]) new Object[maxSize];
    }
    Arr(E[] b, int direction){
        listArray = b;
        increment_maxSize = maxSize = b.length;
        top_1 = 0;
        top_2 = maxSize - 1;
        this.direction = direction;
    }

    @Override
    public void clear() {
        if(this.direction == 1) top_1 = 0;
        else    top_2 = maxSize - 1;
    }

    @Override
    public void push(E item) {
        if(top_1 >= top_2){
            E[] temp = listArray;
            int len = temp.length;

            maxSize += increment_maxSize;
            listArray = (E[]) new Object[maxSize];

            for(int i = 0 ; i < top_1 ; i++){
                listArray[i] = temp[i];
            }
            for(int i = top_2+1; i < len ; i++){
                listArray[len+i] = temp[i];
            }

            top_2 = maxSize - (len - top_2 - 1);
            // len - top_2 - 1 => indicates how many elements were filled from the end of the array
            // we need to update the top_2 pointer as we increase the size of the array
        }
        if(this.direction == 1) listArray[top_1++] = item;
        else{
            listArray[top_2] = item;
            top_2--;
        }
    }

    @Override
    public E pop() {
        if(this.length() == 0){
            return null;
        }
        else{
            if(this.direction == 1){
                return listArray[--top_1];
            } else {
                return listArray[++top_2];
            }
        }
    }

    @Override
    public int length() {
        if(this.direction == 1) return top_1;
        else    return (maxSize - top_2 - 1);
    }

    @Override
    public E topValue() {
        if(this.length() == 0)  return null;
        else{
            if(this.direction == 1) return listArray[top_1-1];
            else    return listArray[top_2+1];
        }
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }
}

