package offline_1;

import java.util.ArrayList;
import java.util.Scanner;

interface List<E>{
    void clear();   //removes all contents from the list
    void insert(E item);    //inserts an element at current position
    void append(E item);    //appends an element at the end of the list
    E remove();     //removes and returns the current element
    void moveToStart();     //sets current position to the start of the list
    void moveToEnd();       //sets current position to the end of the list
    void prev();    //moves current position one step left
    void next();    //moves current position one step right
    int length();   //returns current number of elements in the list
    int currPos();  //returns position of the current element
    void moveToPos(int pos);    //sets current position to "pos"
    E getValue();   //returns the current element
    int Search(E item);     //returns the position of the argument in the list; -1 if not found
}

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> Q = new ArrayList<>();       //Q -> function no.
        ArrayList<Integer> P = new ArrayList<>();       //P -> parameters
        int K, X;    // K -> initial number of elements, X -> initial maxSize
        int f, p;
        int returned_val = -1;

        Scanner scanner = new Scanner(System.in);

        K = scanner.nextInt();
        X = scanner.nextInt();

        AList<Integer> list = new AList<>(X);
        //LList<Integer> list = new LList<>();

        for (int i = 0; i < K; i++)
        {
            list.append(scanner.nextInt());
        }

        do{
            f = scanner.nextInt();
            p = scanner.nextInt();
            if(f == 0)  break;
            Q.add(f);
            P.add(p);
        } while(true);

        //printing the list
        int pos = list.currPos();
        System.out.print("<");
        for(list.moveToStart(); list.currPos() < list.length() ; list.next()){
            if(list.currPos() == pos) System.out.print("| ");
            System.out.print(list.getValue() + " ");
            if(list.currPos() == list.length() - 1)     break;
        }
        System.out.print(">\n");
        list.moveToPos(pos);

        for(int i = 0 ; i < Q.size() ; i++){

            //System.out.println();

            if(Q.get(i) == 1){                //clear()
                returned_val = -1;
                list.clear();
            }
            else if(Q.get(i) == 2){           //insert(item)
                returned_val = -1;
                list.insert(P.get(i));
            }
            else if(Q.get(i) == 3){           //append(item)
                returned_val = -1;
                list.append(P.get(i));
            }
            else if(Q.get(i) == 4){           //remove()
                returned_val = list.remove();
            }
            else if(Q.get(i) == 5){           //moveToStart()
                returned_val = -1;
                list.moveToStart();
            }
            else if(Q.get(i) == 6){           //moveToEnd()
                returned_val = -1;
                list.moveToEnd();
            }
            else if(Q.get(i) == 7){           //prev()
                returned_val = -1;
                list.prev();
            }
            else if(Q.get(i) == 8){           //next()
                returned_val = -1;
                list.next();
            }
            else if(Q.get(i) == 9){           //length()
                returned_val = list.length();
            }
            else if(Q.get(i) == 10){          //currPos()
                returned_val = list.currPos();
            }
            else if(Q.get(i) == 11){          //moveToPos(pos)
                returned_val = -1;
                list.moveToPos(P.get(i));
            }
            else if(Q.get(i) == 12){          //getValue()
                returned_val = list.getValue();
            }
            else if(Q.get(i) == 13){          //Search(item)
                returned_val = list.Search(P.get(i));
            }

            pos = list.currPos();
            System.out.print("<");
            for(list.moveToStart(); list.currPos() < list.length() ; list.next()){
                if(list.currPos() == pos) System.out.print("| ");
                System.out.print(list.getValue() + " ");
                if(list.currPos() == list.length() - 1)     break;
            }
            System.out.print(">");
            list.moveToPos(pos);

            System.out.println("\n" + returned_val);
        }
    }
}
