package is333;

import java.io.*;

public class index {
    public static void main(String[]args) throws IOException {
        int[][] edges = new int[5000][5000];
        // ArrayList edges = new ArrayList();

        String[] names = new String[109];

        File file = new File("/Users/allis/Desktop/friendship.txt");
        int count = 0;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while (count==0) {
            line = (br.readLine());
            names = line.split(", ", 0);
            ++count;
        }
        int leftNode = 0;
        int rightNode = 1;
        int i = 1;
        while ((line = br.readLine()) != null) {
            String [] tempArray = line.split(",", 0);
            int [] tempArray2 = new int [109];
            for (int k=0; k<tempArray.length-1; ++k) {
                tempArray2[k] = Integer.parseInt(tempArray[k+1]);
            }
            edges[leftNode][leftNode] = i;

            for (int f = 0; f <= tempArray2.length - 1; ++f) {
                if (tempArray2[f] == 1) {
                    boolean duplicate = false;
                    for (int l=0; l < edges.length; ++l) {
                        if (edges[l][l] == 1+1 && edges[l][l+1] == i) {
                            duplicate = true;
                        }
                    }
                    if (duplicate == false ) {
                        edges[leftNode][leftNode] = i;
                        edges[leftNode][rightNode] = f+i;
                        ++leftNode;
                        ++rightNode;
                    }
                    else {
                        continue;
                    }
                }
            }
            ++i;
        }
        br.close();

        File file2 = new File("/Users/allis/Desktop/execute.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

        bw.write("*Verticies"  + " " + names.length);
        bw.newLine();
        System.out.println("*Verticies");

        for (int x=0; x<names.length-1; ++x) {
            bw.write(names[x]);
            bw.newLine();
            System.out.println(names[x]);
        }

        bw.write("*Edges" + " " + edges.length);
        bw.newLine();
        System.out.println("*Edges" );

        for(int y=0; y<edges.length-1; ++y) {
            if (edges[y][y] != 0 && edges[y][y+1] != 0) {
                bw.write(edges[y][y] + " " + edges[y][y+1] + "\n");
                bw.newLine();
                System.out.println(edges[y][y] + " " + edges[y][y+1]);
            }
        }
        bw.close();
    }
}