public class matrixMultiplication{
    public static void main(String[] args){
        int arraySize = 1024;
        double[][] array1 = new double[arraySize][arraySize];
        double[][] array2 = new double[arraySize][arraySize];

        for(int k = 0; k < arraySize; k++){
            for(int r = 0; r< arraySize; r++){
                array1[k][r] = 1;
                array2[k][r] = 1;
            }
        }
        long startTime = System.nanoTime();
  
        double[][] array3 = new double[array1.length][array2[0].length];

        for(int i = 0; i < array3.length; i++){
            for(int j = 0; j < array3[0].length; j++){
                for(int k = 0; k < array2.length; k++){
                    array3[i][j] += array1[i][k] * array2[k][j];
                }
            }
        }

        /*
        for(int l = 0; l < array3.length; l++){
            for(int p = 0; p < array3[0].length; p++){
                System.out.print(array3[l][p] + " ");
            }
            System.out.println("");
        }
        */
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);
    }
}