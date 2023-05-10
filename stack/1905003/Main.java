package offline_2;

import java.util.ArrayList;
import java.util.Scanner;

interface Stack<E> {
    void clear();
    void push(E item);
    E pop();
    int length();
    E topValue();
    void setDirection(int direction);
}

public class Main {
    public static void main(String[] args) {
        int K;                  //initial length of the array
        int Q, P;               //Q->function no., P->parameter
        Arr<Integer> temp = new Arr<>();
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        int returned_val = -1;
        int assigned_dir = 1;

        Scanner scanner = new Scanner(System.in);
        K = scanner.nextInt();
        Arr<Integer> stack = new Arr<>();
        //LL<Integer> stack = new LL<>();
        for(int i = 0 ; i < K ; i++){
            stack.push(scanner.nextInt());
        }

        do {
            Q = scanner.nextInt();
            P = scanner.nextInt();

            if(Q == 0){
                break;
            }

            q.add(Q);
            p.add(P);
        }while (true);

        //printing the stack (assuming upward growth)
        System.out.print("<");
        while(stack.length() != 0){
            temp.push(stack.pop());
        }
        while(temp.length() != 0){
            Integer item = temp.pop();
            System.out.print(item + " ");
            stack.push(item);
        }
        System.out.print(">");
        System.out.println();
        //

        for(int i = 0 ; i < q.size() ; i++){
            if(q.get(i) == 1){
                returned_val = -1;
                stack.clear();
            } else if(q.get(i) == 2){
                returned_val = -1;
                stack.push(p.get(i));
            } else if(q.get(i) == 3){
                returned_val = stack.pop();
            } else if(q.get(i) == 4){
                returned_val = stack.length();
            } else if(q.get(i) == 5){
                returned_val = stack.topValue();
            } else if(q.get(i) == 6){
                if(stack.length() == 0) {
                    returned_val = -1;
                    assigned_dir = p.get(i);
                    stack.setDirection(assigned_dir);
                    temp.setDirection(assigned_dir);
                }
            }

            //printing the stack
            System.out.print("<");
            while(stack.length() != 0){
                Integer item = stack.pop();
                if(assigned_dir == -1)  System.out.print(item + " ");
                temp.push(item);
            }
            while(temp.length() != 0){
                Integer item = temp.pop();
                if(assigned_dir == 1)  System.out.print(item + " ");
                stack.push(item);
            }
            System.out.print(">");
            System.out.println();
            //

            System.out.println(returned_val);
        }

    }
}
