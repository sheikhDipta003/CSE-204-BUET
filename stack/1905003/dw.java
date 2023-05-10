package offline_2;

import java.util.Arrays;
import java.util.Scanner;

class Dish{
    private final int size;                                                              //time needed to wash this dish

    Dish(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }
}

public class dw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, x;                                          //n -> total number of invitees, x -> total number of courses
        int k, t, s;                                                 //k -> which friend has pushed a dish,  1 <= k <= n
                                          //t -> point of time of pushing the dish (starts from 0, strictly increasing),
                                                                  //s -> which course this dish is used for, 1 <= s <= x
        LL<Dish> dirtyStack = new LL<>();
        LL<Dish> cleanStack = new LL<>();
        //Arr<Dish> dirtyStack = new Arr<>();
        //Arr<Dish> cleanStack = new Arr<>();
//        Arr<Dish> dish_stack = new Arr<>();
//        dish_stack.setDirection(-1);

        int A = 0;                               //the end time when all the disks have been pushed onto the clean stack
        LL<Integer> washingEndTime = new LL<>();
        LL<Integer> temp = new LL<>();                                               //temp list used to print the stack
        LL<Integer> ID = new LL<>();                                          //ID's of the friends that had a full meal

        n = scanner.nextInt();
        int[] f = new int[n];                                     //f[i] -> number of courses that i-th friend has eaten
        Arrays.fill(f, 0);

        x = scanner.nextInt();
        int[] a = new int[x];                       //a[i] -> the time required to wash the dish designated for course i

        for(int i = 0 ; i < x ; i++){
            a[i] = scanner.nextInt();
        }

        do{
            k = scanner.nextInt();
            t = scanner.nextInt();
            s = scanner.nextInt();

            if(k == 0){
                break;
            }

//            dish_stack.setDirection(-1);
//            dish_stack.push(new Dish(a[s-1]));
//            Dish item = dish_stack.pop();
//            dish_stack.setDirection(1);
//            dish_stack.push(item);

            dirtyStack.push(new Dish(a[s-1]));
            Dish item = dirtyStack.pop();
            cleanStack.push(item);

            if(washingEndTime.length() == 0){
                A += a[s-1];                                                                     //since s starts from 1
            } else {
                int del_t = (A >= t)? 0 : (t - A);
                A += a[s-1] + del_t;
            }
            washingEndTime.push(A);
            f[k-1]++;                                                                            //since k starts from 1

            if(f[k-1] == x){
                ID.push(k);
            }
        }while(true);

        System.out.println(A);

        //showing the washingEndTime stack
        while(washingEndTime.length() != 0){
            temp.push(washingEndTime.pop());
        }
        while(temp.length() != 0){
            Integer item = temp.pop();
            System.out.print(item + " ");
            washingEndTime.push(item);
        }
        System.out.println();
        //

        if(sumArr(f) == n*x){
            System.out.println("Y");
        } else {
            System.out.println("N");
        }

        while(ID.length() != 0){
            System.out.print(ID.pop() + ",");
        }
    }

    public static int sumArr(int[] Arr){
        int sum = 0;
        for (int j : Arr) {
            sum += j;
        }
        return sum;
    }
}
