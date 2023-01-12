import java.lang.Math;
public class secant{
    public static void main(String[] args){
        double x0 = 0;
        double x1 = 3;  
        boolean valid = false;
        double error = 0.01;
        int iteration = 0;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("Iterations reached 100");
                valid = false;
                break;
            }
            double temp = x1;
            x1 = secantMethod1(x0, x1);
            x0 = temp;
            System.out.println("iteration: " + iteration);
            double calcError = Math.abs(x1-x0)/Math.abs(x1);
            System.out.println("calcError: " + calcError);
            if(calcError < error){
                System.out.println("Root is: " + x1);
                valid = false;
            }
        }
        //Function 2
        valid = true;
        x0 = 120;
        x1 = 130;
        while(valid){
            iteration++;
            System.out.println("Iteration: " + iteration);
            double temp = x1;
            x1 = secantMethod2(x0, x1);
            x0 = temp;
            double calcError = Math.abs(x1-x0)/Math.abs(x1);
            System.out.println("calcError: " + calcError);
            if(calcError < error){
                System.out.println("Root is: " + x1);
                valid = false;
            }
        }
    }
    public static double function1(double value){
        value = (2 * Math.pow(value,3)) - (11.7 * Math.pow(value,2)) + (17.7 * value) -5;
        return value;
    }

    public static double secantMethod1(double x0, double x1){
        double x2 = x1 - ((x0-x1)/(function1(x0)-function1(x1))) * function1(x1);
        return x2;
    }
    public static double secantMethod2(double x0, double x1){
        double x2 = x1 - ((x0-x1)/(function2(x0)-function2(x1))) * function2(x1);
        return x2;
    }
    public static double function2(double value){
        double hyperbolicCosine;
        if(value == 0){
            hyperbolicCosine = 0;
        }
        hyperbolicCosine = Math.cosh(50/value);
        value = value +10 - (value * hyperbolicCosine);
        return value;
    }
}