package com.example;

import java.util.Scanner;

public class Calculator{
    // testing for live build using ngrok
    // sqrt function: do square root of x
    // Ex: sqrt(4) = 2.0
    // Ex: sqrt(-1) = Error
    public static double sqrt(double x){
        if(x < 0){
            throw new IllegalArgumentException("Square root of negative number is not possible");
        }
        return Math.sqrt(x);
    }

    // fact function: do factorial of x
    // Ex: fact(3) = 6
    // Ex: fact(-1) = Error
    public static long fact(int x){
        if(x < 0){
            throw new IllegalArgumentException("Factorial of negative number is not possible");
        }
        long ans = 1;
        for(int i = 2; i <= x; i++){
            ans *= i;
        }
        return ans;
    }

    // log function: do natural log wrt to e for x
    // Ex: log(1) = 0
    // Ex: log(-1) = Error
    public static double log(double x){
        if(x <= 0){
            throw new IllegalArgumentException("Natural log of negative and zero not possible");
        }
        return Math.log(x);
    }

    // pow function: find pow of x to b
    // Ex: pow(2,2) = 4.0
    public static double pow(double x, double b){
        return Math.pow(x,b);
    }

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Menu driven interface for basic calculator: ");
            System.out.println("1. Square root");
            System.out.println("2. Factorial");
            System.out.println("3. Natural log");
            System.out.println("4. Power");
            System.out.println("5. Exit");
            System.out.println("Enter operaion number which you need to perform:");
            if (!sc.hasNextInt()) {
                System.out.println("No input available. Exiting.");
                break;
            }

            int ch = sc.nextInt();
            switch(ch){
                case 1:
                    try{
                        System.out.println("Enter value of x for square root");
                        double val = sc.nextDouble();
                        System.out.println("Ans: " + sqrt(val));
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        sc.nextLine();
                    }
                    break;
                
                case 2:
                    try{
                        System.out.println("Enter value of x for factorial");
                        int x = sc.nextInt();
                        System.out.println("Ans: " + fact(x));
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    try{
                        System.out.println("Enter value of x for natural log");
                        double value = sc.nextDouble();
                        System.out.println("Ans: " + log(value));
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    try{
                        System.out.println("Enter value of base for power");
                        double base = sc.nextDouble();
                        System.out.println("Enter value of exponent for power");
                        double exp = sc.nextDouble();
                        System.out.println("Ans: " + pow(base,exp));
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number.");
                        sc.nextLine();
                    }
                    break;

                case 5:
                    System.out.println("Exiting");
                    sc.close();
                    return;
                     
                default:
                    System.out.println("Invalid choice, Enter a valid choice");
            }
        }
    }
};
