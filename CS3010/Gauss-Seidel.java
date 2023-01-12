import java.util.*;

public class Main{

    public static void main(String[] args) {
        double[][] matrix = {
                {15, 3, -5, -5, 36},
                {7, -15, -6, 1, -112},
                {-4, 7, 19, 6, 19},
                {3, 0, -5, 8, -23}
        };
        double eps = 0.002;
        int size = matrix.length;
        double[] previousVariableValues = new double[size];
        java.util.Arrays.fill(previousVariableValues, 0);

        int iterations = 0;

        while (true) {
            iterations++;
            double[] currentVariableValues = new double[size];

            for (int i = 0; i < size; i++) {
                currentVariableValues[i] = matrix[i][size];

                for (int j = 0; j < size; j++) {
//                    if (i != j) {
//                        currentVariableValues[i] -= matrix[i][j] * previousVariableValues[j];
//                    }
                    if(i > j) {
                        currentVariableValues[i] -= matrix[i][j] * currentVariableValues[j];
                    }

                    if (i < j) {
                        currentVariableValues[i] -= matrix[i][j] * previousVariableValues[j];
                    }
                }

                currentVariableValues[i] /= matrix[i][i];
            }

            double error = 0;

            for (double[] aMatrix : matrix) {
//                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
                double errorTemp = 0;
                for (int j = 0; j < size; j++) {
                    errorTemp += aMatrix[j] * currentVariableValues[j];
                }

                error += Math.abs(errorTemp - aMatrix[size]);
            }

            if (error < eps) {
                break;
            }

            previousVariableValues = currentVariableValues;

        }

        print(matrix);
        print(previousVariableValues);
        System.out.println(iterations);

        for (double[] aMatrix : matrix) {
            double errorTemp = 0;
            for (int j = 0; j < size; j++) {
                errorTemp += aMatrix[j] * previousVariableValues[j];
            }

            System.out.print(errorTemp + " ");
        }
    }

    private static void print(double[][] a) {
        for (double[] anA : a) {
            for (double anAnA : anA) {
                System.out.print(anAnA + " ");
            }
            System.out.print("\n\r");
        }
    }

    private static void print(double[] a) {
        for (double anA : a) {
            System.out.print(anA + " ");
        }
        System.out.print("\n\r");
    }
}