package graph;

import java.io.*;
import java.util.*;
import java.io.IOException;

public class Graph implements GraphInterface {
    int noOfNodes;
    ArrayList<int[]> nodeInfo;
    ArrayList<Integer> weights;
    ArrayList<String> words = new ArrayList<>();;


    public  Graph(String txtFile) throws IOException{
        buildGraph(txtFile);
    }

    public int convertBinaryToDec(String line){
        int biNum = Integer.parseInt(line);
        int dec = 0;

        if (biNum == 0){
            return 0;
        }
        else {
            for (int i = 0; i < line.length(); i++) {
                if (biNum % 10 == 1) {
                    dec += Math.pow(2, i);
                    biNum /= 10;
                }
                else biNum /= 10;
            }
//        System.out.println(dec);
            return dec;
        }
    }


//    calculate number of letters in string
    public int calcWeights(String text){

        List<String[]> numsList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        String[] tokensVal;
        int tokensValLen;

        if (text.contains("(")){
            tokensVal = text.split("\\(|\\)");
            tokensValLen = tokensVal.length;

//          put numbers from () to list of arrays numsList and text to list strList
            for (int i = 0; i < tokensValLen; i++){
                if (tokensVal[i].matches("\\d+.*")){
                    String[] nums = tokensVal[i].split("x");
                    numsList.add(nums);
                }
                else strList.add(tokensVal[i]);
            }
        }
        else {
            return text.length();
        }

        String resStr = "", subStr;
        int first, second;

        String makeSub;
        for (int i = 0; i < numsList.size(); i++){

            first = Integer.parseInt(numsList.get(i)[0]);
            second = Integer.parseInt(numsList.get(i)[1]);

            makeSub = resStr + strList.get(i);
            int ind = makeSub.length() - first;

            resStr = makeSub.substring(0, ind);

            subStr = makeSub.substring(ind);
            for (int j = 0; j < second; j++){
                resStr += subStr;
            }
        }
//        unchangeable last string element
        if (strList.size() > numsList.size()){
            String l = strList.get(strList.size() - 1);
            resStr += l;
        }
        words.add(resStr);
        System.out.println(resStr);
        return resStr.length();
/*
//      print map
        for (String key : map.keySet()){
            System.out.print(key + " : ");
            for (String val : map.get(key)) {
                System.out.print(val);
            }
            System.out.println();
        }
*/
    }

    public void buildGraph(String name) throws IOException{
        FileReader fileReader = new FileReader(name);

        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            int lineNo = 1;
            List<String> lines = new ArrayList<>();

            String firstLine = br.readLine();
            noOfNodes = convertBinaryToDec(firstLine);
            System.out.println("number of nodes is " + noOfNodes);
            lines.add(firstLine);

            while((line = br.readLine()) != null) {
                lines.add(line);
                lineNo += 1;
            }
//            print all lines
//            for (String l : lines){
//                System.out.println(l);
//            }

//            list of arrays: node, noOfLeafs, leafNo
            nodeInfo = new ArrayList<>();

            for (int l = 1; l < noOfNodes + 1; l++){
                String[] biStrings = lines.get(l).split("\t");
                int[] decInts = new int[biStrings.length];
                for (int i = 0; i < biStrings.length; i++){
                    decInts[i] = convertBinaryToDec(biStrings[i]);
                    System.out.print(decInts[i] + " ");
                }
                nodeInfo.add(decInts);
                System.out.println();
            }

//          calc number of lines + 1 => text => calcWeights()
            weights = new ArrayList<>();
            for (int i = noOfNodes + 1; i < lineNo; i++){
                weights.add(calcWeights(lines.get(i)));
            }
//            print weights
            for (int i : weights){
                System.out.print(i + " ");
            }
            System.out.println();
//            System.out.println("number of lines in the file is " + lineNo);
        }
    }
}

//  \t	The tab character
//  \n	The newline character

