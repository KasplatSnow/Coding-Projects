import java.util.*;
import java.io.*;
public class JacobiGaussSeidel{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of equations: ");
        int numEquations = scanner.nextInt();

        System.out.println("Enter coefficeints: ");
        Scanner br = new Scanner(System.in);
        String  lines = br.nextLine();     
        String [] strs = lines.trim().split("\\s+");
        double[][] matrix = new double[numEquations][strs.length];
        for (int k = 0; k < strs.length; k++) {
            matrix[0][k] = Double.parseDouble(strs[k]);
       }
       for(int j = 1; j < numEquations; j++){
            System.out.println("Enter next coefficients: ");
            lines = br.nextLine();
            strs = lines.trim().split("\\s+");
            for (int i = 0; i < strs.length; i++) {
                matrix[j][i] = Double.parseDouble(strs[i]);
            }
        }

        double[] solutions = new double[matrix.length];
        System.out.println("Enter your starting solution: ");
        lines = br.nextLine();
        strs = lines.trim().split("\\s+");
        for(int i = 0; i < strs.length; i++){
            solutions[i] = Double.parseDouble(strs[i]);
        }

        System.out.println("Enter Error: ");
        double eps = br.nextDouble();

        System.out.println("Jacobi[1] or Gauss[2]?");
        int method = br.nextInt();

        int size = matrix.length;
        int iterations = 0;

        if(matrix[0][size] < (matrix[0][size] - matrix[0][0]) && matrix[1][size] < (matrix[1][size] - matrix[1][1]) && matrix[1][size] < (matrix[1][size] - matrix[1][1]))
        {
            System.out.println("Not Diagonally Dominant");
            System.exit(0);
        }
        while (true) {
            iterations++;
            double[] currentValues = new double[size];
            for (int i = 0; i < size; i++) {
                currentValues[i] = matrix[i][size];
                for (int j = 0; j < size; j++) {
                    if(i > j) {
                        if(method == 2){
                        currentValues[i] -= matrix[i][j] * currentValues[j];
                        }
                        else{
                            currentValues[i] -= matrix[i][j] * solutions[j];
                        }
                    }
                    if (i < j) {
                        currentValues[i] -= matrix[i][j] * solutions[j];
                    }
                }
                if(method == 1 && iterations == 1){
                    currentValues[i] = matrix[i][size]/matrix[i][i]; 
                }
                else{
                    currentValues[i] /= matrix[i][i];
                }
            }
            double error = 0;

            for(int i = 0; i < size; i++){
                error += ((currentValues[i] - solutions[i])/currentValues[i]) * ((currentValues[i] - solutions[i])/currentValues[i]);
            }
            error = Math.sqrt(error);
            if (error < eps) {
                break;
            }
            if(iterations == 50)
            {
                System.out.println("Error was not met in 50 iterations");
                break;
            }
            solutions = currentValues;
            System.out.println("Iteration: " + iterations);
            System.out.print("[ ");
            print(solutions);
            System.out.print("]\n");
        }
    }

    private static void print(double[] a) {
        for (double anA : a) {
            System.out.printf("%.4f", anA);
            System.out.print(" ");
        }
    }
}