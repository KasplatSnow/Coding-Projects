import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
public class interpolation{
    public static void main(String[] args) throws Exception{
        String documents = System.getProperty ("user.home") + "/Documents/CPP/CS3010/";
        File file = new File (documents + "data-1.txt");        
        Scanner sc = new Scanner(file);
        String nextLine = sc.nextLine();
        String[] Values = nextLine.trim().split("\\s+");
        double[] xValues = new double[Values.length]; 
        double[][] yValues = new double[Values.length][Values.length];
        for(int k = 0; k < Values.length; k++){
            xValues[k] = Double.parseDouble(Values[k]);
        }
        nextLine = sc.nextLine();
        Values = nextLine.trim().split("\\s+");
        for(int l = 0; l < Values.length; l++){
            yValues[l][0] = Double.parseDouble(Values[l]);
        }

        for(int j = 1; j < xValues.length; j++){
            for(int i = 0; i < xValues.length-j; i++){
                yValues[i][j] = (yValues[i+1][j-1] - yValues[i][j-1])/(xValues[i+j]-xValues[i]);
            }
        }

        System.out.println("Divide Difference Table:");
        divideTablePrint(xValues, yValues);
        System.out.println("Newton:");
        newton(xValues, yValues);
        System.out.println("Lagrange:");
        lagrange(xValues,yValues);
        System.out.println("Simplified:");
        simplified(xValues, yValues);
    }  

    public static void newton(double[] x, double[][] y){
        DecimalFormat ft = new DecimalFormat("#.##");
        for(int i = 0; i < y[0].length; i++){
            System.out.print(ft.format(y[0][i]));
            for(int j = 0; j < i; j++){
                if(x[j] == 0){
                    System.out.print("x");
                }
                else{
                    System.out.printf("(x - " + ft.format(x[j]) + ")");
                }
            }
            if(i != y[0].length-1){
                System.out.print(" + ");
            }
        }
        System.out.println();
    }

    public static void lagrange(double[] x, double[][] y){
        DecimalFormat ft = new DecimalFormat("#.##");
        for(int i = 0; i < x.length; i++){
            double total = 1;
            for(int k = 0; k < x.length; k++){
                if(k != i){
                    total *= (x[i] - x[k]);
                }
            }
            System.out.print(ft.format(y[i][0] * (1/total)));
            for(int j = 0; j < x.length; j++){
                if(j != i){
                    if(x[j] == 0)
                    {
                        System.out.print("x");
                    }
                    else{
                        System.out.print("(x - " + ft.format(x[j]) + ")");
                    }
                }

            }
            if(i != x.length - 1){
                System.out.print(" + ");
            }
        }
        System.out.println();
    }

    public static void simplified(double[] x, double[][] y){
        DecimalFormat ft = new DecimalFormat("#.##");
        double[] temp = new double[x.length];
        double[] result = new double[x.length];
        for(int i = 0; i < y[0].length - 1 ; i++){
            double[] poly = new double[x.length];
                poly[0] = 1;
                poly[1] = -x[0];
                for(int j = 1; j < x.length - 1 - i; j++){
                    double[] poly2 = {1, -x[j]};
                    poly = polynomialCalc(poly, poly2);
                }
                for(int k = 0; k < temp.length; k++){
                    temp[k] = poly[k] * y[0][x.length - 1 - i];
                }
                for(int p = 0; p < result.length-1; p++){
                    if(i == 0){
                        for(int e = 0; e < result.length; e++){
                            result[e] = temp[e];
                        }
                    }
                    else{
                       if((p+i) != result.length){
                        result[p + i] = result[p + i] + temp[p];
                       }
                    }
                }
        }
        result[result.length-1] = y[0][0];
        for(int r = 0; r < result.length; r++){
            if(r != result.length-1){
                System.out.print(ft.format(result[r]) + "x^" + (result.length - 1 - r) + " + ");
            }
            else{
                System.out.print(ft.format(result[r]));
            }
        }
        System.out.println();
    }

    public static void divideTablePrint(double[] x, double[][] y){
        DecimalFormat ft = new DecimalFormat("#.##");
        System.out.print("x\tf[]\t");
        for(int i = 0; i < x.length-1; i++){
            System.out.print("f[");
            for(int j = 0; j < x.length-1; j++){
                System.out.print(",");
            }
            System.out.print("]\t");
        }
        System.out.println();
        for(int a = 0; a < y.length; a++){
            System.out.printf(ft.format(x[a]) + "\t");
            for(int b = 0; b < y.length-a; b++){
                System.out.printf(ft.format(y[a][b]) + "\t");
            }
            System.out.println();
        }
    }

    public static double[] polynomialCalc(double[] polynomial1, double[] polynomial2){
        double[] prod = new double[polynomial1.length + polynomial2.length - 1];
        for (int i = 0; i < polynomial1.length + polynomial2.length - 1; i++) 
        {
            prod[i] = 0;
        }
        for (int i = 0; i < polynomial1.length; i++) 
        {
            for (int j = 0; j < polynomial2.length; j++) 
            {
                prod[i + j] += polynomial1[i] * polynomial2[j];
            }
        }
        return prod;
    }
}