# include <iostream>
# include <cmath>
# include <bits/stdc++.h>
using namespace std;

int main(){
    int* price;
    int N, K;   //N -> number of plants, K -> number of friends
    cin >> N >> K;
    price = new int[N];
    for(int i = 0; i < N; i++)
        cin >> price[i];
    
    sort(price, price+N);
    int multiplier = (int)ceil((double)N / K);

    long long int cost = 0;
    for(int i = 0; i < N; i++){
        cost += price[i] * multiplier;
        if(multiplier > 1)  multiplier--;
    }
    cout << cost << endl;
    delete price;

    return 0;
}