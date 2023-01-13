public class naiveDaCMM{
    public static void main(String[] args){
        double[][] array1 = {{2,0,-1,6},{3,7,8,0},{-5,1,6,-2},{8,0,1,7}};
        double[][] array2 = {{0,1,6,3},{-2,8,7,1},{2,0,-1,0},{9,1,6,-2}};
        int n = array2.length;
        double[][] array4 = naiveMatrixMultiply(array1, array2, n);

        for(int h = 0; h < array4.length; h++){
            for(int l = 0; l < array4[0].length; l++){
                System.out.print(array4[h][l] + " ");
            }
            System.out.println("");
        }
    }

    public static double[][] naiveMatrixMultiply(double[][] array1, double[][] array2, int n){
        double[][] array3 = new double[n][n];
        if(n == 1){
            array3[0][0] = array1[0][0] * array2[0][0];
            System.out.println(array3[0][0]);
            return array3;
        }
        else{
                n = n/2;
                double[][] subMatrix1_1 = quad(array1, 0, 0, n);
                double[][] subMatrix1_2 = quad(array1, 0, n, n);
                double[][] subMatrix1_3 = quad(array1, n, 0, n);
                double[][] subMatrix1_4 = quad(array1, n, n, n);
        
                double[][] subMatrix2_1 = quad(array2, 0, 0, n);
                double[][] subMatrix2_2 = quad(array2, 0, n, n);
                double[][] subMatrix2_3 = quad(array2, n, 0, n);
                double[][] subMatrix2_4 = quad(array2, n, n, n);

                add(naiveMatrixMultiply(subMatrix1_1, subMatrix2_1, n), naiveMatrixMultiply(subMatrix1_2, subMatrix2_3, n), array3, n, 0, 0);
                add(naiveMatrixMultiply(subMatrix1_1, subMatrix2_2, n), naiveMatrixMultiply(subMatrix1_2, subMatrix2_4, n), array3, n, 0, n);
                add(naiveMatrixMultiply(subMatrix1_3, subMatrix2_1, n), naiveMatrixMultiply(subMatrix1_4, subMatrix2_3, n), array3, n, n, 0);
                add(naiveMatrixMultiply(subMatrix1_3, subMatrix2_2, n), naiveMatrixMultiply(subMatrix1_4, subMatrix2_4, n), array3, n, n, n);

            }

            /*
            array3[0][0] = naiveMatrixMultiply(array1[0][0], array2[0][0]) + naiveMatrixMultiply(array1[0][1], array2[1][0]);
            array3[0][1] = naiveMatrixMultiply(array1[0][0], array2[0][1]) + naiveMatrixMultiply(array1[0][1], array2[1][1]);
            array3[1][0] = naiveMatrixMultiply(array1[1][0], array2[0][0]) + naiveMatrixMultiply(array1[1][1], array2[1][0]);
            array3[1][1] = naiveMatrixMultiply(array1[1][0], array2[0][1]) + naiveMatrixMultiply(array1[1][1], array2[1][1]);
            */
        return array3;
    }

    public static void add(double[][] array1, double[][] array2, double[][] array3, int n, int row, int col){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                array3[i + row][j + col] = array1[i][j] + array2[i][j];
            }
        }
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

    /*
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
    */
}