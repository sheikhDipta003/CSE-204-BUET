package offline_3;

//Two queues.
//People coming to the bank always take a position in the shortest queue.
//When a customer standing at the rear of a queue sees that he has n people in front of him and another queue has a total of (n-1)
// or fewer people at that time, he will switch to the other queue, i.e. leave the current queue and enter the other queue.
//In case a customer standing in a queue observes that both the queue and the service area of the other booth are empty,
// he enters the service area of the other booth.

//There is no delay between a customer entering the bank and taking a position in the queue.
//Queue switching takes no time.
//If a queue switching and a new customer taking position in a queue appear to happen simultaneously, prioritize the new customer.
//Prioritize dequeue over queue switching.
//When a new customer entering a bank sees that he can take service at that time (i.e. length of at least one queue is zero),
// he starts taking service.
//Other than these, ties can be broken arbitrarily.
//You can also assume that simulation starts at time 0.

import java.util.Scanner;

class Customer{
    private int id;
    private int entryTime;
    private int serviceTime;
    Customer(int id, int serviceTime, int e){this.id = id; this.serviceTime = serviceTime; entryTime = e;}
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public int getServiceTime(){return this.serviceTime;}
    public int getEntryTime(){return entryTime;}
}

public class bank {
    public static void main(String[] args) {
        int n;                                              //total number of customers
        int[] t, s;                                //t:time when the customer enters the bank, s:service time of that customer
        int teller1=0, teller2=0;                //calculates and stores the time at which current customer taking service will leave
        AQueue<Customer> q1 = new AQueue<>();
        AQueue<Customer> q2 = new AQueue<>();
        Customer[] Qcst;                         //array to store all required customer objects
        int remCst;                              //remaining customers to be pushed
        int currTime = 0;                                 //maintains a record of the current point of time
        boolean isEmptyB1 = true, isEmptyB2 = true;               //is the service area empty
        Customer cst;                                       //customer under consideration

        Scanner scanner = new Scanner(System.in);
        remCst = n = scanner.nextInt();
        Qcst = new Customer[n];
        t = new int[n];
        s = new int[n];
        for(int i = 0 ; i < n ; i++){
            t[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        //put all the customer objects in cst queue
        for(int i = 0; i < n; i++){
            Qcst[i] = new Customer(i+1, s[i], t[i]);
        }

        do{
            if(remCst > 0){
                for(int i = 0; i < n; i++){
                    cst = Qcst[i];

                    if(currTime == cst.getEntryTime()){
                        if(q1.length() <= q2.length()){
                            q1.enqueue(cst);
                            if(isEmptyB1) {
                                teller1 = cst.getServiceTime() + currTime;
                                isEmptyB1 = false;
                            }
                        } else {
                            q2.enqueue(cst);
                            if(isEmptyB2) {
                                teller2 = cst.getServiceTime() + currTime;
                                isEmptyB2 = false;
                            }
                        }
                        remCst--;
                    }
                }
            }

            if(!isEmptyB1 && currTime == teller1){
                q1.dequeue();
                isEmptyB1 = true;
                if(q1.length() == 0){
                    do {
                        if(q2.length() >= 3 && (q2.length() - 1) > q1.length()){
                            cst = q2.leaveQueue();
                            q1.enqueue(cst);
                            if(isEmptyB1) {
                                isEmptyB1 = false;
                                teller1 = currTime + cst.getServiceTime();
                            }
                        } else {
                            isEmptyB1 = true;
                            break;
                        }
                    }while (true);
                } else {
                    cst = q1.frontValue();
                    teller1 = currTime + cst.getServiceTime();
                    isEmptyB1 = false;

                    do {
                        if(q2.length() >= 3 && (q2.length() - 1) > q1.length()){
                            cst = q2.leaveQueue();
                            q1.enqueue(cst);
                        } else break;
                    }while (true);
                }
            }

            if(!isEmptyB2 && currTime == teller2){
                q2.dequeue();
                isEmptyB2 = true;
                if(q2.length() == 0){
                    do {
                        if(q1.length() >= 3 && (q1.length() - 1) > q2.length()){
                            cst = q1.leaveQueue();
                            q2.enqueue(cst);
                            if(isEmptyB2) {
                                isEmptyB2 = false;
                                teller2 = currTime + cst.getServiceTime();
                            }
                        } else {
                            isEmptyB2 = true;
                            break;
                        }
                    }while (true);
                } else {
                    cst = q2.frontValue();
                    teller2 = currTime + cst.getServiceTime();
                    isEmptyB2 = false;

                    do {
                        if(q1.length() >= 3 && (q1.length() - 1) > q2.length()){
                            cst = q1.leaveQueue();
                            q2.enqueue(cst);
                        } else break;
                    }while (true);
                }
            }

            currTime++;
        }while (q1.length() != 0 || q2.length() != 0 || remCst == n);

        System.out.println("Booth 1 finishes service at t=" + teller1);
        System.out.println("Booth 2 finishes service at t=" + teller2);
    }
}
