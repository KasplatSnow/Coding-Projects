import java.util.*;
public class GaussJacobi{
    public static void main(String []args){
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

        double[] solutions = new solutions[numEquations];
        System.out.println("Enter your starting solution: ");
        lines = br.nextLine();
        strs = lines.trim().split("\\s+");
        for(int i = 0; i < strs.length; i++){
            solutions[i] = Double.parseDouble(strs[i]);
        }

        System.out.println("Enter Error: ");
        double error = br.nextDouble();

        System.out.println("Jacobi[1] or Gauss[2]");
        int answer = br.nextInt();

        
    }
}