package offline_dp;

import java.util.Scanner;

public class findLCS {

    static void LCS_length(String X, String Y, char[][] D, int[][] L){
        int m = X.length()-1;
        int n = Y.length()-1;
        for (int i = 1; i <= m; i++)
            L[i][0] = 0;
        for (int i = 0; i <= n; i++)
            L[0][i] = 0;

        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (X.charAt(i) == Y.charAt(j))
                {
                    L[i][j] = L[i - 1][j - 1] + 1;
                    D[i][j] = '\\';
                }
                else if (L[i - 1][j] >= L[i][j - 1])
                {
                    L[i][j] = L[i - 1][j];
                    D[i][j] = '|';
                }
                else
                {
                    L[i][j] = L[i][j - 1];
                    D[i][j] = '-';
                }
            }
        }
    }
    static String LCS(char[][] D, String X, int i, int j, String lcs){
        if (i == 0 || j == 0)
            return "";
        if (D[i][j] == '\\')
        {
            lcs = LCS(D, X, i - 1, j - 1, lcs);
            lcs += String.valueOf(X.charAt(i));
        }
        else if (D[i][j] == '|')
        {
            lcs = LCS(D, X, i - 1, j, lcs);
        }
        else
        {
            lcs = LCS(D, X, i, j - 1, lcs);
        }

        return lcs;
    }

    public static void main(String[] args)
    {
        String X = "0";
        String Y = "0";
        String lcs = "";
        char[][] D = new char[50][50];
        int[][] L = new int[50][50];
        Scanner scanner = new Scanner(System.in);
        X += scanner.nextLine();
        Y += scanner.nextLine();

        LCS_length(X, Y, D, L);
        lcs = LCS(D, X, X.length()-1, Y.length()-1, lcs);
        System.out.println("length of lcs = " + lcs.length());
        System.out.println("lcs = " + lcs);
    }

}
