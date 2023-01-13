public class strassenMM{
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
        int n = array1.length;
        double[][] array6 = strassen(array1, array2, n);

        /*
        for(int h = 0; h < array6.length; h++){
            for(int l = 0; l < array6[0].length; l++){
                System.out.print(array6[h][l] + " ");
            }
            System.out.println("");
        }
        */
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

    }

    public static double[][] strassen(double[][] array1, double[][] array2, int n){
        double[][] array3 = new double[n][n];
        if(n==1){
            array3[0][0] = array1[0][0] * array2[0][0];
            return array3;
        }
        n = n/2;
        double[][] subMatrix1_1 = quad(array1, 0, 0, n);
        double[][] subMatrix1_2 = quad(array1, 0, n, n);
        double[][] subMatrix1_3 = quad(array1, n, 0, n);
        double[][] subMatrix1_4 = quad(array1, n, n, n);

        double[][] subMatrix2_1 = quad(array2, 0, 0, n);
        double[][] subMatrix2_2 = quad(array2, 0, n, n);
        double[][] subMatrix2_3 = quad(array2, n, 0, n);
        double[][] subMatrix2_4 = quad(array2, n, n, n);

        double[][]  p1 = strassen(subMatrix1_1, sub(subMatrix2_2, subMatrix2_4), n);
        double[][]  p2 = strassen(add(subMatrix1_1, subMatrix1_2), subMatrix2_4, n);
        double[][]  p3 = strassen(add(subMatrix1_3, subMatrix1_4), subMatrix2_1, n);
        double[][]  p4 = strassen(subMatrix1_4, sub(subMatrix2_3, subMatrix2_1), n);
        double[][]  p5 = strassen(add(subMatrix1_1, subMatrix1_4), add(subMatrix2_1, subMatrix2_4), n);
        double[][]  p6 = strassen(sub(subMatrix1_2, subMatrix1_4), add(subMatrix2_3, subMatrix2_4), n);
        double[][]  p7 = strassen(sub(subMatrix1_1, subMatrix1_3), add(subMatrix2_1, subMatrix2_2), n);

        double[][] c11 = add(sub(p4, p2), add(p6, p5)); 
        double[][] c12 = add(p1, p2);
        double[][] c21 = add(p3, p4);
        double[][] c22 = add(sub(p1, p3), sub(p5, p7));
        array3 = combine(c11, c12, c21, c22);

        return array3;
    }

    public static double[][] sub(double[][] array1, double[][] array2){
        double[][] array3 = new double[array1.length][array1[0].length];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1[0].length; j++){
                array3[i][j] = array1[i][j] - array2[i][j];
            }
        }
        return array3;
    }
    public static double[][] add(double[][] array1, double[][] array2){
        double[][] array3 = new double[array1.length][array1[0].length];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1[0].length; j++){
                array3[i][j] = array1[i][j] + array2[i][j];
            }
        }
        return array3;
    }

    public static double[][] quad(double[][] array, int row, int col, int size){
        double[][] arrayResult = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arrayResult[i][j] = array[i + row][j + col];
            }
        } 
        return arrayResult;
    }

    public static double[][] combine(double[][] array1, double[][] array2, double[][] array3, double[][] array4){
        double[][] array5 = new double[array1.length * 2][array1.length * 2];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1.length; j++){
                array5[i][j] = array1[i][j];
            }
        }

        for(int i = 0; i < array2.length; i++){
            for(int j = 0; j < array2.length; j++){
                array5[i][j + array2.length] = array2[i][j];
            }
        }

        for(int i = 0; i < array3.length; i++){
            for(int j = 0; j < array3.length; j++){
                array5[i + array3.length][j] = array3[i][j];
            }
        }

        for(int i = 0; i < array4.length; i++){
            for(int j = 0; j < array4.length; j++){
                array5[i + array4.length][j + array4.length] = array4[i][j];
            }
        }
        return array5;
    }
}