class Heap
{
private:
    int *heap;
    int maxSize;
    int n;

public:
    Heap(int size)
    {
        maxSize = size;
        heap = new int[maxSize];
        n = 0;
    }
    Heap(std::vector<int> &v)
    {
        maxSize = v.size();
        int count = 0;
        heap = new int[maxSize];
        for (auto i = v.begin(); i != v.end(); ++i)
        {
            heap[count++] = (int)*i;
            n++;
        }
        buildHeap();
    }
    ~Heap()
    {
        delete heap;
    }
    int size() { return n; }
    void insert(int elem)
    {
        heap[n++] = elem;
        int i = n - 1;
        while (i > 0)
        {
            if (heap[parent(i)] < heap[i])
            {
                swapElem(i, parent(i));
                i = parent(i);
            }
            else
                break;
        }
    }
    int getMax()
    {
        return heap[0];
    }
    void deleteKey()
    {
        swapElem(0, n - 1);
        n--;
        heapify(0);
    }
    void buildHeap()
    {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(i);
        }
    }
    void heapify(int pos)
    {
        int lc = left(pos);
        int rc = right(pos);
        int largest;
        if (lc < n && heap[lc] > heap[pos])
        {
            largest = lc;
        }
        else
            largest = pos;
        if (rc < n && heap[rc] > heap[largest])
        {
            largest = rc;
        }
        if (largest != pos)
        {
            swapElem(pos, largest);
            heapify(largest);
        }
    }
    int left(int i)
    {
        return (i << 1) + 1;
    }
    int right(int i)
    {
        return (i << 1) + 2;
    }
    int parent(int i)
    {
        return (i - 1) >> 2;
    }
    void swapElem(int i, int j)
    {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    friend void heapsort(std::vector<int> &v);
};

void heapsort(std::vector<int> &v)
{
    Heap h1(v);
    v.clear();
    int n = h1.size();
    for (int i = n - 1; i >= 0; i--)
    {
        h1.swapElem(0, i);
        (h1.n)--;
        v.push_back(h1.heap[i]);
        h1.heapify(0);
    }
}
