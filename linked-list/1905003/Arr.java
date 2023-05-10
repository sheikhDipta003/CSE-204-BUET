package offline_1;

import java.util.ArrayList;
import java.util.Scanner;

class AList<E> implements List<E>{
    private int maxSize;
    private int listSize;
    private int curr;
    private E[] listArray;
    private int increment_maxSize;

    AList(int size){
        increment_maxSize = maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[maxSize];
    }

    AList(E[] another_list, int size){      //Y -> number of elements in another_list, Y < size
        increment_maxSize = maxSize = size;
        listArray = (E[]) new Object[maxSize];
        listSize = curr = 0;

        for(int i = 0 ; i < another_list.length ; i++){
            this.append(another_list[i]);
        }
    }

    @Override
    public void clear() {
        listSize = curr = 0;
    }

    @Override
    public void insert(E item) {
        if(listSize == maxSize){
            E[] temp = listArray;
            maxSize += increment_maxSize;
            listArray = (E[])new Object[maxSize];
            for(int i = 0 ; i < temp.length ; i++)
                listArray[i] = temp[i];
        }
        for(int i = listSize; i > curr ; i--)
            listArray[i] = listArray[i-1];
        listArray[curr] = item;
        listSize++;
    }

    @Override
    public void append(E item) {
        if(listSize == maxSize){
            E[] temp = listArray;
            maxSize += increment_maxSize;
            listArray = (E[])new Object[maxSize];
            for(int i = 0 ; i < temp.length ; i++)
                listArray[i] = temp[i];
        }
        listArray[listSize++] = item;
    }

    @Override
    public E remove() {
        if(curr < 0 || curr >= listSize)    return null;
        E key = listArray[curr];
        for(int i = curr ; i < listSize - 1 ; i++){
            listArray[i] = listArray[i+1];
        }
        listSize--;
        return key;
    }

    @Override
    public void moveToStart() {
        curr = 0;
    }

    @Override
    public void moveToEnd() {
        curr = listSize - 1;
    }

    @Override
    public void prev() {
        if(curr != 0)   curr--;
    }

    @Override
    public void next() {
        if(curr < listSize - 1)     curr++;
    }

    @Override
    public int length() {
        return listSize;
    }

    @Override
    public int currPos() {
        return curr;
    }

    @Override
    public void moveToPos(int pos) {
        if(pos < 0 || pos >= listSize)  return;
        curr = pos;
    }

    @Override
    public E getValue() {
        if(listSize == 0)   return null;
        return listArray[curr];
    }

    @Override
    public int Search(E item) {
        int keyPos = -1;
        for(int i = 0 ; i < listSize ; i++){
            if(item == listArray[i]){
                keyPos = i;
                break;
            }
        }
        return keyPos;
    }
}


