package offline_1;

import java.util.Scanner;

public class TNL {
    public static void main(String[] args) {
        int K, L, M;  //K -> RS, L -> BS, M -> TS
        int T;      //task no.

        Scanner scanner = new Scanner(System.in);
        K = scanner.nextInt();

        L = scanner.nextInt();
        //AList<Integer> B = new AList<>(L);
        LList<Integer> B = new LList<>();
        for(int i = 0; i < L ; i++)
            B.append(scanner.nextInt());

        M = scanner.nextInt();
        //AList<Integer> Tr = new AList<>(M);
        LList<Integer> Tr = new LList<>();
        for(int i = 0; i < M ; i++)
            Tr.append(scanner.nextInt());

        T = scanner.nextInt();

        int temp = 0;
        if(T == 1){
            for(int i = 0 ; i < K ; i++) {
                System.out.print(i);
                if (i != K - 1) System.out.print(",");
            }

            System.out.println();
            for(B.moveToStart(); B.currPos() < B.length() ; B.next()){
                if(B.currPos()!=0){
                    for(int i = 0 ; i < (B.getValue() - temp) ; i++)
                        System.out.print(",");
                }
                temp = B.getValue();
                System.out.print(temp);
            }

            System.out.println();
            for(Tr.moveToStart(); Tr.currPos() < Tr.length() ; Tr.next()){
                if(Tr.currPos()!=0){
                    for(int i = 0 ; i < (Tr.getValue() - temp) ; i++)
                        System.out.print(",");
                }
                temp = Tr.getValue();
                System.out.print(temp);
            }
        }
    }
}
