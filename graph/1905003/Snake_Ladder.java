package offline_6;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

enum COLOR{WHITE, GRAY, BLACK}

interface Graph{
    void Init(int n);   //initializes the graph, n-> number of vertices
    int n();    //returns total number of vertices
    int e();    //returns total number of edges
    int first(int v);   //returns v's first neighbour
    int next(int v, int w);     //returns v's next neighbour after w
    void setEdge(int i, int j, int w);  //set an edge, having weight w, between vertices i and j
    void delEdge(int i, int j);     //delete an edge between i and j
    boolean isEdge(int i, int j);   //is there an edge between vertices i and j
    int weight(int i, int j);   //returns the weight of the edge between vertices i and j
    void setMark(int v, int val);   //sets mark of a vertex, i.e. whether it is a starting point of a snake or ladder
    int getMark(int v);
}

class vertex{
    public int vertex_no;
    public COLOR color;
    public vertex parent;
}

//Matrix implementation
class MatGraph implements Graph{
    private int[][] matrix;
    private int numEdge;
    private int[] Mark;

    MatGraph(){}
    MatGraph(int n){
        Init(n);
    }

    @Override
    public void Init(int n) {
        numEdge = 0;
        Mark = new int[n];
        matrix = new int[n][n];
    }

    @Override
    public int n() {
        return matrix.length;
    }

    @Override
    public int e() {
        return numEdge;
    }

    @Override
    public int first(int v) {
        for(int i = 0; i < n(); i++){
            if(matrix[v-1][i] != 0){
                return (i+1);
            }
        }
        return n()+1;
    }

    @Override
    public int next(int v, int w) {
        for(int j = w-1+1; j < n(); j++){
            if(matrix[v-1][j] != 0){
                return (j+1);
            }
        }
        return n()+1;
    }

    @Override
    public void setEdge(int i, int j, int w) {
        if(w > 0) {
            if(matrix[i-1][j-1] == 0)    numEdge++;
            matrix[i-1][j-1] = w;
        }
    }

    @Override
    public void delEdge(int i, int j) {
        if(matrix[i-1][j-1] != 0)    numEdge--;
        matrix[i-1][j-1] = 0;
    }

    @Override
    public boolean isEdge(int i, int j) {
        return matrix[i-1][j-1] != 0;
    }

    @Override
    public int weight(int i, int j) {
        return matrix[i-1][j-1];
    }

    public void setMark(int v, int val) { Mark[v-1] = val; }
    public int getMark(int v) { return Mark[v-1]; }
}

public class Snake_Ladder {
    public static void main(String[] args) throws IOException {
        int[] n = new int[16];
        int[] X = new int[16];
        int[] l = new int[16];
        int[] s = new int[16];
        String[][] lPoints = new String[50][10];
        String[][] sPoints = new String[50][10];
        int t, count = 0;
        String line1, line2;
        String[] result;
        Reader fr = new FileReader("inputGraph.txt");
        BufferedReader br = new BufferedReader(fr);
        Writer fw = new FileWriter("outputGraph.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        t = Integer.parseInt(br.readLine());
        while (true) {
            line1 = br.readLine();
            if(line1 == null)   break;
            line2 = br.readLine();
            n[count] = Integer.parseInt(line1.split(" ")[0]);
            X[count] = Integer.parseInt(line1.split(" ")[1]);
            l[count] = Integer.parseInt(line2);
            for(int i = 0; i < l[count]; i++){
                lPoints[count][i] = br.readLine();
            }
            line2 = br.readLine();
            s[count] = Integer.parseInt(line2);
            for(int i = 0; i < s[count]; i++){
                sPoints[count][i] = br.readLine();
            }
            count++;
        }

        for(int k = 0; k < t ; k++) {
            Graph G = new MatGraph(X[k]);
            for (int i = 0; i < l[k]; i++) {
                int start = Integer.parseInt(lPoints[k][i].split(" ")[0]);
                int end = Integer.parseInt(lPoints[k][i].split(" ")[1]);
                G.setEdge(start, end, 1);
                G.setMark(start, 1);
            }
            for (int i = 0; i < s[k]; i++) {
                int start = Integer.parseInt(sPoints[k][i].split(" ")[0]);
                int end = Integer.parseInt(sPoints[k][i].split(" ")[1]);
                G.setEdge(start, end, 1);
                G.setMark(start, 1);
            }

            for (int i = 1; i <= X[k] - n[k]; i++) {
                for (int j = i + 1; j <= i + n[k]; j++) {
                    if(!isIn(lPoints[k], l[k], i) && !isIn(sPoints[k], s[k], i)) {
                        G.setEdge(i, j, 1);
                    }
                }
            }
            for (int i = X[k] - n[k] + 1; i <= X[k]; i++) {
                for (int j = i + 1; j <= X[k]; j++) {
                    if(!isIn(lPoints[k], l[k], i) && !isIn(sPoints[k], s[k], i)) {
                        G.setEdge(i, j, 1);
                    }
                }
            }

            result = BFS(G, 1, X[k]);
            bw.write(result[0] + "\n");
            bw.write(result[1] + "\n");
            if(result[2].length() == 0){
                bw.write("All reachable\n");
            } else {
                String[] temp = result[2].split(" ");
                for(int i = 0; i < temp.length ; i++){
                    bw.write(temp[i] + " ");
                }
            }
            bw.write("\n");
        }

        br.close();
        fr.close();
        bw.close();
        fw.close();
    }

    public static String[] BFS(Graph G, int Source, int Dest){
        vertex [] v_arr = new vertex[G.n()+1];
        String temp = "";
        String[] result = new String[3];
        int N = 0;

        for(int i = 1; i <= G.n(); i++){
            v_arr[i] = new vertex();
            v_arr[i].vertex_no = i;
            if(i != Source) {
                v_arr[i].color = COLOR.WHITE;
            } else {
                v_arr[i].color = COLOR.GRAY;
            }
            v_arr[i].parent = null;
        }

        AQueue<vertex> Q = new AQueue<>(G.n());
        Q.enqueue(v_arr[Source]);
        while (Q.length() > 0){
            vertex u = Q.dequeue();
            for(int i = G.first(u.vertex_no); i <= G.n(); i=G.next(u.vertex_no, i)){
                if (v_arr[i].color == COLOR.WHITE) {
                    v_arr[i].color = COLOR.GRAY;
                    v_arr[i].parent = u;
                    Q.enqueue(v_arr[i]);
                }
            }
            u.color = COLOR.BLACK;
        }

        //create a list of all white squares
        String untraversed = "";
        for(int i = 1; i <= G.n(); i++){
            if(v_arr[i].color == COLOR.WHITE){
                untraversed = untraversed.concat(i + " ");
            }
        }

        for(int i = Dest; i != Source; i = v_arr[i].parent.vertex_no){
            if(v_arr[i].parent == null) {
                temp = "No solution";
                N = -1;
                break;
            }
            temp = temp.concat(i +" ");
            if(G.getMark(i) != 1)   N++;
        }
        if(N != -1) {
            temp = temp.concat(String.valueOf(Source));
            N++;
        }

        if(N != -1) {
            result[0] = String.valueOf(N - 1);
            result[1] = rev(temp);
        } else {
            result[0] = "-1";
            result[1] = temp;
        }
        result[2] = untraversed;
        return result;
    }

    public static String rev(String str){
        String[] words = str.split(" ");
        Collections.reverse(Arrays.asList(words));
        String reversed = "";
        for(int i = 0; i < words.length; i++){
            reversed = reversed.concat(words[i]);
            if(i != words.length-1) reversed = reversed.concat("->");
        }
        return reversed;
    }

    public static boolean isIn(String[] S, int len, int v){
        boolean isFound = false;
        for(int i = 0; i < len ; i++){
            if(Integer.parseInt(S[i].split(" ")[0]) == v){
                isFound = true;
            }
        }
        return isFound;
    }
}
