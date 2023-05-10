#include <bits/stdc++.h>
# include <iostream>
# include <chrono>
# include <random>
using namespace std;
using namespace std::chrono;

void Merge(int* A, int p, int q, int r);
void MergeSort(int* A, int p, int r);
void QuickSort(int* A, int p, int r);
int partition(int* A, int p, int r);
void swap(int* A, int i, int j);
void QuickSort_rand(int *A, int p, int r);
int partition_rand(int *A, int p, int r);
void copyOfRange(int *A, int start, int end, int* B);
void insertionSort(int *A, int p, int r);

int main()
{
    int n[6] = {5, 10, 100, 1000, 10000, 100000};
    double data[6][7] = {};
    int A[100];

    for(int j = 0; j < 6; j++){
        srand(n[j]);

        //(a)
        for(int k = 0; k < 20; k++){
            for (int i = 0; i < n[2]; i++)
                A[i] = rand();
            auto start = system_clock::now().time_since_epoch().count();
            MergeSort(A, 0, 100-1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][0] += (double)duration;
        }

        for (int k = 0; k < 20; k++)
        {
            for (int i = 0; i < n[2]; i++)
                A[i] = rand();
            auto start = system_clock::now().time_since_epoch().count();
            QuickSort(A, 0, 100 - 1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][1] += (double)duration;
        }

        for (int k = 0; k < 20; k++)
        {
            for (int i = 0; i < n[2]; i++)
                A[i] = rand();
            auto start = system_clock::now().time_since_epoch().count();
            QuickSort_rand(A, 0, 100 - 1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][2] += (double)duration;
        }

        for (int k = 0; k < 20; k++)
        {
            for (int i = 0; i < n[2]; i++)
                A[i] = rand();
            auto start = system_clock::now().time_since_epoch().count();
            insertionSort(A, 0, 100 - 1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][3] += (double)duration;
        }
        //

        //(b)
        for (int k = 0; k < 20; k++)
        {
            auto start = system_clock::now().time_since_epoch().count();
            QuickSort(A, 0, 100 - 1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][4] += (double)duration;
        }

        for (int k = 0; k < 20; k++)
        {
            auto start = system_clock::now().time_since_epoch().count();
            QuickSort_rand(A, 0, 100 - 1);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][5] += (double)duration;
        }
        //

        //(c)
        for (int k = 0; k < 20; k++)
        {
            for (int i = 0; i < n[2]; i++)
                A[i] = rand();
            auto start = system_clock::now().time_since_epoch().count();
            sort(A, A + 100);
            auto stop = system_clock::now().time_since_epoch().count();
            auto duration = stop - start;
            data[j][6] += (double)duration;
        }
        //

        for(int i = 0; i < 7; i++){
            data[j][i] /= 20;
        }
    }

    fstream fout;
    
    fout.open("Formatted_Report_for_DnC_Offline.csv", ios::out | ios::app);

    fout << "Time required in ms\n";
    fout << "n" << "," << "Merge Sort" << "," << "Quicksort" << "," << "Randomized Quicksort" << "," << "insertion Sort" << "," << "Quicksort with Sorted Input" << "," << "Randomized Quicksort with Sorted Input" << "STL sort() function\n";
    for(int i = 0; i < 6; i++){
        fout << n[i] << ",";
        for(int j = 0; j < 7; j++){
            fout << data[i][j] << ",";
        }
        fout << "\n";
    }

    return 0;
}

void Merge(int* A, int p, int q, int r)
{
    //it is assumed that A[p...q] and A[q+1...r] are already sorted
    int n1 = q - p + 1;
    int n2 = r - (q + 1) + 1;
    int* L = new int[n1];
    int* R = new int[n2];

    copyOfRange(A, p, q + 1, L);
    copyOfRange(A, q + 1, r + 1, R);
    int i = 0, j = 0, k;

    for (k = p; k <= r; k++)
    {
        if (L[i] <= R[j])
        {
            A[k] = L[i];
            i++;
            if (i >= n1)
                break;
        }
        else
        {
            A[k] = R[j];
            j++;
            if (j >= n2)
                break;
        }
    }
    while (i < n1)
    {
        A[++k] = L[i++];
    }
    while (j < n2)
    {
        A[++k] = R[j++];
    }
    //Big-theta(n1+n2) = Big-theta(n), where n = r-p+1
}

//sorts the subarray A[p...r]
void MergeSort(int* A, int p, int r)
{
    if (p < r)
    {
        int q = (p + r) / 2;
        MergeSort(A, p, q);
        MergeSort(A, q + 1, r);
        Merge(A, p, q, r);
    }
    //T(n) = {Big-theta(1) when n=1, 2T(n/2) + Big-theta(n) when n > 1
    //T(n) = Big-theta(nlogn)
}

//sorts the subarray A[p...r] in place
void QuickSort(int* A, int p, int r)
{
    if (p < r)
    {
        int q = partition(A, p, r);
        QuickSort(A, p, q - 1);
        QuickSort(A, q + 1, r);
    }
}

//returns the index of the pivot element in the subarray A[p...r] and places the pivot in the correct position
//all elements to the left of pivot are smaller and all elements to the right are larger
int partition(int* A, int p, int r)
{
    int i = p - 1, j, x = A[r];
    for (j = p; j <= r - 1; j++)
    {
        if (A[j] <= x)
        {
            i++;
            swap(A, i, j);
        }
    }
    swap(A, i + 1, r);
    return (i + 1);
}

void swap(int* A, int i, int j)
{
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}

void QuickSort_rand(int *A, int p, int r){
    if (p < r)
    {
        int q = partition_rand(A, p, r);
        QuickSort_rand(A, p, q - 1);
        QuickSort_rand(A, q + 1, r);
    }
}

int partition_rand(int *A, int p, int r){
    std::random_device rd;
    std::default_random_engine e1(rd());
    std::uniform_int_distribution<int> uniform_dist(p, r);
    int i = uniform_dist(e1);

    int temp = A[i];
    A[i] = A[r];
    A[r] = temp;

    return partition(A, p, r);
}

void copyOfRange(int *A, int start, int end, int *B)
{
    int j = 0;
    for(int i = start; i < end; i++){
        B[j++] = A[i]; 
    }
}

void insertionSort(int *A, int p, int r){
    int i, key;
    for(int j = p+1; j <= r; j++){
        key = A[j];
        i = j - 1;
        while(i >= p && A[i] > key){
            A[i+1] = A[i];
            i--;
        }
        A[i+1] = key;
    }
}
