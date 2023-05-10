package offline_3;

import java.util.ArrayList;
import java.util.Scanner;

interface Queue<E> {
    void clear();                                                                                //clear the whole queue
    void enqueue(E item);                                                      //insert an item at the rear of the queue
    E dequeue();                                                  //remove and return the item at the front of the queue
    int length();                                                       //return current number of elements in the queue
    E frontValue();                                                        //return, but don't remove, the front element
    E rearValue();                                                                             //return the rear element
    E leaveQueue();                                                   //return the rear element which has left the queue
}

public class Main{
    public static void main(String[] args) {
        ArrayList<Integer> F = new ArrayList<>();       //F -> function no.
        ArrayList<Integer> P = new ArrayList<>();       //P -> parameters
        int K;                                        // K -> initial number of elements
        int f, p;
        int returned_val = -1;

        Scanner scanner = new Scanner(System.in);

        K = scanner.nextInt();

        LQueue<Integer> queue = new LQueue<>();
        LQueue<Integer> temp = new LQueue<>();
        //AQueue<Integer> queue = new AQueue<>(K);
        //AQueue<Integer> temp = new AQueue<>(K);

        for (int i = 0; i < K; i++)
        {
            queue.enqueue(scanner.nextInt());
        }

        do{
            f = scanner.nextInt();
            p = scanner.nextInt();
            if(f == 0)  break;
            F.add(f);
            P.add(p);
        } while(true);

        //printing the queue
        temp = queue;
        queue = new LQueue<>();
        //queue = new AQueue<>(temp.length());
        System.out.print("< ");
        while(temp.length() > 0){
            Integer val = temp.dequeue();
            if(temp.length() != 0)  System.out.print(val + ", ");
            else    System.out.print(val + " ");
            queue.enqueue(val);
        }
        System.out.print(">\n");

        for(int i = 0 ; i < F.size() ; i++){

            if(F.get(i) == 1){                //clear()
                returned_val = -1;
                queue.clear();
            }
            else if(F.get(i) == 2){           //enqueue(item)
                returned_val = -1;
                queue.enqueue(P.get(i));
            }
            else if(F.get(i) == 3){           //dequeue()
                returned_val = queue.dequeue();
            }
            else if(F.get(i) == 4){           //length()
                returned_val = queue.length();
            }
            else if(F.get(i) == 5){           //frontValue()
                returned_val = queue.frontValue();
            }
            else if(F.get(i) == 6){           //rearValue()
                returned_val = queue.rearValue();
            }
            else if(F.get(i) == 7){           //leaveQueue()
                returned_val = queue.leaveQueue();
            }

            //printing the queue
            temp = queue;
            queue = new LQueue<>();
            //queue = new AQueue<>(temp.length());
            System.out.print("< ");
            while(temp.length() > 0){
                Integer val = temp.dequeue();
                if(temp.length() != 0)  System.out.print(val + ", ");
                else    System.out.print(val + " ");
                queue.enqueue(val);
            }
            System.out.print(">\n");
            System.out.println(returned_val);
        }
    }
}

