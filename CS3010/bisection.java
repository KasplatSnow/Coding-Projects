import java.util.*;
import java.lang.Math;
public class bisection{
    public static void main(String[] args){
        int iteration = 0;
        double a = 0;
        double b = 4;
        double t;
        double error = 0.01;
        boolean valid = true;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("After 100 iterrations, the solution is divergent or slowly converging at " + b);
                valid = false;
            }
            System.out.println("Iteration: " + iteration);
            if(function1(a) * function1(b) >= 0){
                System.out.println("Does not exist on this domain");
                valid = false;
                break;
            }

            t = (a+b)/2;
            if(function1(t) == 0){
                System.out.println("Root is: " + t);
                valid = false; 
            }
            if(function1(t)*function1(a)<0){
                b = t;
            }
            else{
                a = t;
            }
            double calcError = Math.abs(b-a)/Math.abs(b);
            System.out.println("calcError: " + calcError);
            if(calcError < error){
                System.out.println("The approximate answer is " + t);
                valid = false;
            }
        }
        //Function 2
        iteration = 0;
        a = 120;
        b = 130;
        valid = true;
        while(valid){
            iteration++;
            if(iteration == 101){
                System.out.println("After 100 iterrations, the solution is divergent or slowly converging at " + b);
                valid = false;
            }
            System.out.println("Iteration: " + iteration);
            if(function2(a) * function2(b) >= 0){
                System.out.println("Does not exist on this domain");
                valid = false;
            }

            t = (a+b)/2;
            //System.out.println("T: " + t);
            if(function2(t)*function2(a)<0){
                b = t;
            }
            else{
                a = t;
            }
            double calcError = Math.abs(b-a)/Math.abs(b);
            System.out.println("calcError: " +calcError);
            if(calcError < error){
                System.out.println("The approximate answer is " + t);
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
}