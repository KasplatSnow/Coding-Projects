public class strassen{
    public static void main(String[] args){
        double[][] array1 = {{1,2},{3,4}};
        double[][] array2 = {{5,6},{7,8}};
        int n = array1.length;
        double[][] array6 = strassen(array1, array2, n);

        for(int h = 0; h < array6.length; h++){
            for(int l = 0; l < array6[0].length; l++){
                System.out.print(array6[h][l] + " ");
            }
            System.out.println("");
        }
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

        double[][] s1 = sub(subMatrix2_2, subMatrix2_4);
        double[][] s2 = add(subMatrix1_1, subMatrix1_2);
        double[][] s3 = add(subMatrix1_3, subMatrix1_4);
        double[][] s4 = sub(subMatrix2_3, subMatrix2_1);
        double[][] s5 = add(subMatrix1_1, subMatrix1_4);
        double[][] s6 = add(subMatrix2_1, subMatrix2_4);
        double[][] s7 = sub(subMatrix1_2, subMatrix1_4);
        double[][] s8 = add(subMatrix2_1, subMatrix2_4);
        double[][] s9 = sub(subMatrix1_1, subMatrix1_3);
        double[][] s10 = add(subMatrix2_1, subMatrix2_2);

        double[][]  p1 = multiply(subMatrix1_1, s1);
        double[][]  p2 = multiply(s2, subMatrix2_4);
        double[][]  p3 = multiply(s3, subMatrix2_1);
        double[][]  p4 = multiply(subMatrix1_4, s4);
        double[][]  p5 = multiply(s5, s6);
        double[][]  p6 = multiply(s7, s8);
        double[][]  p7 = multiply(s9, s10);

        double[][] c11 = sub(add(p5, p4), add(p2, p6)); 
        double[][] c12 = add(p1, p2);
        double[][] c21 = add(p3, p4);
        double[][] c22 = sub(add(p1, p5), sub(p3, p7));
        array3 = combine(c11, c12, c21, c22);

        return array3;
    }

    public static double[][] sub(double[][] array1, double[][] array2){
        double[][] array3 = new double[array1.length][array1[0].length];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1[0].length; j++){
                array3[i][j] = array1[i][j] + array2[i][j];
            }
        }
        return array3;
    }
    public static double[][] add(double[][] array1, double[][] array2){
        double[][] array3 = new double[array1.length][array1[0].length];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1[0].length; j++){
                array3[i][j] = array1[i][j] - array2[i][j];
            }
        }
        return array3;
    }

    public static double[][] multiply(double[][] array1, double[][] array2){
        double[][] array3 = new double[array1.length][array1[0].length];
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array1[0].length; j++){
                array3[i][j] = array1[i][j] * array2[i][j];
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
                array5[i][j + array2.length - 1] = array2[i][j];
            }
        }
        for(int i = 0; i < array3.length; i++){
            for(int j = 0; j < array3.length; j++){
                array5[i + array3.length - 1][j] = array3[i][j];
            }
        }
        for(int i = 0; i < array4.length; i++){
            for(int j = 0; j < array4.length; j++){
                array5[i + array4.length - 1][j + array4.length - 1] = array1[i][j];
            }
        }
        return array5;
    }
}