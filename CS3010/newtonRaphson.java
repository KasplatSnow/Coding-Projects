import java.lang.Math;
public class newtonRaphson{
    public static void main(String[] args){
        double a = 3;
        double x;
        int iteration = 0;
        boolean valid = false;
        double error = 0.01;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("Iterations reached 100");
                valid = false;
                break;
            }
            System.out.println("iteration: " + iteration);
            x = a - (function1(a)/integratedFunction1(a));
            double calcError = Math.abs(x - a)/Math.abs(x);
            System.out.println("calcError: " + calcError);
            if(calcError < error){
                System.out.println("Found root for function1: " + x);
                valid = false;
            }
            a = x;
        }
        //Function 2
        iteration = 0;
        valid = true;
        a = 120;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("Iterations reached 100");
                valid = false;
                break;
            }
            System.out.println("iteration: " + iteration);
            x = a - (function2(a)/integratedFunction2(a));
            //System.out.println("x: " + x);
            double calcError = Math.abs(x - a)/Math.abs(x);
            System.out.println("calcError: " + calcError);
            if(calcError < error){
                System.out.println("Found root for function2: " + x);
                valid = false;
            }
            a = x;
        }
    }
    public static double function1(double value){
        value = (2 * Math.pow(value,3)) - (11.7 * Math.pow(value,2)) + (17.7 * value) - 5;
        return value;
    }

    public static double function2(double value){
        double hyperbolicCosine;
        if(value == 0){
            hyperbolicCosine = 0;
        }
        hyperbolicCosine = Math.cosh(50/value);
        value = value + 10 - (value * hyperbolicCosine);
        return value;
    }

    public static double integratedFunction1(double value){
        value = (6 * Math.pow(value,2)) - (23.4 * value) + 17.7;
        return value;
    }

    public static double integratedFunction2(double value){
        value =  ((50 * Math.sinh(50/value))/value) - Math.cosh(50/value) + 1;
        return value;
    }
}