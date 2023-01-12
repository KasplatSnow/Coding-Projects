import java.lang.Math;
public class falsePosition{
    public static void main(String[] args){
        double a = 2;
        double b = 4;  
        double x = 0;
        boolean valid = true;
        int iteration = 0;
        double error = 0.01;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("After 100 iterrations, the solution is divergent or slowly converging at " + x);
                valid = false;
                break;
            }
            System.out.println("Iteration: " + iteration);
            x = falsePositionMethod1(a, b);
            if(Math.signum(function1(a)) == Math.signum(function1(x))){
                a = x;
            }
            else{
                b = x;
            }
            double calcError = Math.abs(b-a)/Math.abs(b);
            System.out.println("calcError: " + calcError);
            if(calcError < error || function1(x) == 0){
                System.out.println("The root is: " + x);
                valid = false;
            }
        }
        //Function 2
        a = 120;
        b = 130;  
        x = 0;
        valid = true;
        iteration = 0;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("After 100 iterrations, the solution is divergent or slowly converging at " + x);
                valid = false;
                break;
            }
            System.out.println("Iteration: " + iteration);
            x = falsePositionMethod2(a, b);
            if(Math.signum(function2(a)) == Math.signum(function2(x))){
                a = x;
            }
            else{
                b = x;
            }
            double calcError = Math.abs(b-a)/Math.abs(b);
            System.out.println("calcError: " + calcError);
            if(calcError < error || function2(x) == 0){
                System.out.println("The root is: " + x);
                valid = false;
            }
        }
    }
    public static double function1(double value){
        value = (2 * Math.pow(value,3)) - (11.7 * Math.pow(value,2)) + (17.7 * value) -5;
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

    public static double falsePositionMethod1(double a, double b){
        double resultant = ((a*function1(b)) - (b * function1(a)))/(function1(b) - function1(a));
        return resultant;
    }

    public static double falsePositionMethod2(double a, double b){
        double resultant = ((a*function2(b)) - (b * function2(a)))/(function2(b) - function2(a));
        return resultant;
    }
}