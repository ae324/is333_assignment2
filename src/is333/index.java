package is333;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.util.ArrayList;


public class index {
    private void writeData(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        //write information begin
        for (int i = 0; i < 10; i++) {
            bw.write(i + "\n");
        }
        //write information end
        bw.close();
    }

    private void readData(String filename) throws FileNotFoundException, IOException {
        ArrayList information = new ArrayList();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //read information begin
        while ((line = br.readLine()) != null) {
            information.add(line);
            System.out.println(information.get(information.size() - 1));
        }
        //read information end
    }

    private void loadInformation(String filename) throws FileNotFoundException, IOException {
        ArrayList nodeName = new ArrayList();
        int[][] relationship = new int[1][1];

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        Boolean getNodeInformation = false;
        boolean getRelationshipInformation = false;

        //begin read information
        while ((line = br.readLine()) != null) {
            if (line.startsWith("*")) {// change the control parameter
                if (line.startsWith("*Vertices")) {
                    getNodeInformation = true;
                    getRelationshipInformation = false;
                }
                if (line.startsWith("*Edges")) {
                    getNodeInformation = false;
                    getRelationshipInformation = true;
                    relationship =
                            new int[nodeName.size()][nodeName.size()];
                }
            } else {//update the related parameter
                if (getNodeInformation) {
                    String name =
                            line.substring(line.indexOf(" ") + 1);
                    nodeName.add(name);
                }
                if (getRelationshipInformation) {
                    String[] index = line.split(" ");
                    relationship[Integer.parseInt(index[0]) - 1]
                            [Integer.parseInt(index[1]) - 1] = 1;
                    relationship[Integer.parseInt(index[1]) - 1]
                            [Integer.parseInt(index[0]) - 1] = 1;
                }
            }
        }
        //end read information

        for (int i = 0; i < nodeName.size(); i++) {
            System.out.print("," + nodeName.get(i));
        }
        System.out.print("\n");

        for (int i = 0; i < nodeName.size(); i++) {
            System.out.print(nodeName.get(i).toString());
            for (int j = 0; j < nodeName.size(); j++) {
                System.out.print("," + relationship[i][j]);
            }
            System.out.print("\n");
        }
    }
}

