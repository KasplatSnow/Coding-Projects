import java.util.*;
import java.io.*;
public class naiveMatrixMultiplication{
    public static void main(String[] args){
        double[][] array1 = {{1,2,3,5},{2,4,5,8},{6,3,3,6},{10,2,5,8}};
        double[][] array2 = {{1,2},{3,4},{5,6},{7,8}};
        double[][] array3 = new double[array1.length][array2[0].length];

        for(int i = 0; i < array3.length; i++){
            for(int j = 0; j < array3[0].length; j++){
                for(int k = 0; k < array2.length; k++){
                    array3[i][j] += array1[i][k] * array2[k][j];
                }
            }
        }

        for(int l = 0; l < array3.length; l++){
            for(int p = 0; p < array3[0].length; p++){
                System.out.print(array3[l][p] + " ");
            }
            System.out.println("");
        }
    }
}