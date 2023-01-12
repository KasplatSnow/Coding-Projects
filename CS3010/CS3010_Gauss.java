import java.util.*;
import java.io.*;
public class CS3010_Gauss{
    public static void main(String[] args){
        //Gets number of equations from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of equations: ");
        int numEquations = scanner.nextInt();

        //gets coefficients from user
        System.out.println("Enter coefficients and b-value: ");
        Scanner br = new Scanner(System.in);
        String lines = br.nextLine();    
        
        //Strs is given a string of the coefficients from the command line, breaks the numbers up and into its array
        String[] strs = lines.trim().split("\\s+");
        //2DArray coefficients holds the coefficient values from strs
        double[][] coefficients = new double[numEquations][strs.length-1];
        //array b holds the b values from strs
        double[] b = new double[numEquations];
        //puts coefficients into coefficients[] and b-values into b[]
        for (int k = 0; k <= strs.length-1; k++) {
            if(k == strs.length-1){
                b[0] = Integer.parseInt(strs[k]);
            }
            else{
                coefficients[0][k] = Integer.parseInt(strs[k]);
            }
        }
        //asks for the next set of coefficients and b-values, then puts them into coefficients[] and b[]
        for(int j = 1; j < numEquations; j++){
            System.out.println("Enter next coefficients and b-value: ");
            lines = br.nextLine();
            strs = lines.trim().split("\\s+");
            for (int i = 0; i < strs.length; i++) {
                if(i == strs.length-1){
                    b[j] = Integer.parseInt(strs[i]);
                }
                else{
                    coefficients[j][i] = Integer.parseInt(strs[i]);
                }
            }
        }

        //scaledFactorVector finds the scaled factor vectors of the equations
        double scaledFactorVector[] = scaledFactorVector(coefficients);
        //index of the next pivot
        int indexOfPivot;
        //an array to hold the final values
        double[] values = new double[numEquations];
        //an array to keep track of the index vectors and assigning values to them
        int[] l = new int[coefficients.length];
        for(int i = 0; i < l.length; i++){
            l[i] = i;
        }
        System.out.println("");
        //an array to hold the multipliers for the equations
        double[] multipliers = new double[coefficients.length];
        //nested-for loop to carry out the gaussian elimination with partial pivoting
        for(int i = 0; i < numEquations - 1; i++){
            indexOfPivot = pivot(coefficients, scaledFactorVector, i, l);
            shiftToLastIndex(l, indexOfPivot);
            multipliers = multipliers(coefficients, i, l);
            gauss(coefficients, b, l, multipliers, i);
            System.out.println("\nIteration: " + (i + 1));
            for(int k = 0; k < coefficients.length; k++){
                for(int j = 0; j < coefficients[i].length; j++){
                    System.out.print(coefficients[k][j] + " ");
                }
                System.out.print(b[k]);
                System.out.println();
            }
            System.out.println();
        }
        shiftToLastIndex(l, 0);
        //calculating the values using backSubsitution method
        values = backSubsitution(coefficients, b, l);

        //printing out the final values
        for(int p = 0; p < numEquations; p++){
            values[p] = Math.round(values[p] * 100.0)/ 100.0;
            System.out.println("X" + (p + 1) + " = " + (values[p]));
        }
    }

    public static double[] scaledFactorVector(double[][] array){
        double[] sfv = new double[array.length];
        //Finds the scale factor vector of each equation
        for(int i = 0; i < array.length; i++){
            sfv[i] = Math.abs(array[i][0]);
            for(int j = 1; j < array[i].length; j++){
                if(Math.abs(array[i][j]) >= sfv[i]){
                    sfv[i] = array[i][j];
                }
            }
        }
        return sfv;
    }

    //change sizes to fit changes in pivot
    public static int pivot(double[][] array, double[] sfv, int iteration, int[] l){
        //pivot holds the row of the pivot
        int pivot = 0;
        double check = 0;
        double[] ratios = new double[array.length];
        //Calculates the scaled ratios and determines the pivot
        for(int i = 0; i < array.length - iteration; i++){
            ratios[i] = Math.abs(array[l[i]][iteration]/sfv[l[i]]);
            System.out.println("Ratio: " + ratios[i]);
            if(ratios[i] > check){
                check = ratios[i];
                pivot = l[i];
            }
        }
        System.out.println("Pivot: " + pivot);
        return pivot;
    }

    //Calculates the multipliers for the pivot
    public static double[] multipliers(double[][] array, int iteration, int[] l){
        double[] multipliers = new double[array.length];
        for(int i = 0; i < array.length; i++){
            multipliers[l[i]] = array[l[i]][iteration]/array[l[l.length-1]][iteration];
        }
        return multipliers;
    }

    //shift the pivot to the back of the array
    public static void shiftToLastIndex(int[] l, int index){
        int temp = l[index];
        for(int i = index; i < l.length-1; i++){
            l[i] = l[i+1];
        }
        l[l.length-1] = temp;
    }

    public static void gauss(double[][] coefficients, double[] b, int[] l, double[] multipliers, int iteration){
        for(int i = 0; i < coefficients.length - 1 - iteration; i++){
            for(int j = 0; j < coefficients[i].length; j++){
                coefficients[l[i]][j] = coefficients[l[i]][j] - (coefficients[l[l.length-1]][j] * multipliers[l[i]]);
            }
            b[l[i]] = b[l[i]] - (b[l[l.length-1]]* multipliers[l[i]]);
        }
    }
    //Calculates the X's
    public static double[] backSubsitution(double[][] coefficients, double[] b, int[] l){
        double[] values = new double[coefficients.length];
        double sum = 0;
        for(int p = 0; p < values.length; p++){
            values[p] = 0;
        }
        for(int i = values.length-1; i >= 0; i--){
            for(int j = 0; j < coefficients[i].length; j++){
                if(j != i){
                    sum = sum + (values[j]*coefficients[l[i]][j]);
                }
            }
            values[i] = (b[l[i]] - sum)/coefficients[l[i]][i];
            sum = 0;
        }
        return values;
    }
}