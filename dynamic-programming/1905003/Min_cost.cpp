# include <iostream>
using namespace std;

int dp_cost(int i, int n, int mask, int** A, int** dp);

int main(){
    int n;
    cin >> n;
    int** A = new int*[n+1];
    for(int i = 0; i <= n; i++){
        A[i] = new int[n+1];
        for (int j = 0; j <= n; j++)
        {
            if (i && j)
            {
            cin >> A[i][j];
            } else {
                A[i][j] = 0;
            }
        }
    }

    int **dp = new int*[n+1];
    for(int i = 0; i <= n; i++){
        dp[i] = new int[1<<(n+1)];
        for(int j = 0; j < 1<<(n+1); j++){
            dp[i][j] = -1;
        }
    }

    cout << dp_cost(1, n, (1 << n)-1, A, dp) << endl;

    return 0;
}

int dp_cost(int i, int n, int mask, int **A, int** dp){
    if(i == n+1)    return 0;
    if(dp[i][mask] != -1)   return dp[i][mask];
    int result = INT32_MAX;
    for(int j = 0; j < n; j++){
        if(mask & (1<<j)){
            int cost = A[i][i] + A[i][j+1];
            result = min(result, cost + dp_cost(i + 1, n, mask & (1 << j), A, dp));
        }
    }

    return dp[i][mask] = result;
}