package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Quickcalculator mode.
 */
public class QuickCalculator {
  /**
   * Index of register needs to be stored.
   */
  public static final int STOREINDEX = 6;

  /**
   * @param element one element split by " "
   * @param registerSet take the array of registerSet
   * @return the input element in the form of BigFraction
   */
  public static BigFraction readFraction(String element, BFRegisterSet registerSet) {
    String[] nums = element.split("/");
    if (nums[0].startsWith("-")) {
      return new BigFraction(element);
    } else if (nums.length > 1 && nums[1].startsWith("-")) {
      return new BigFraction("-" + nums[0] + "/" + nums[1].substring(1));
    } else if (Character.isDigit(nums[0].charAt(0))) {
      int numerator = Integer.parseInt(nums[0]);
      int denominator = nums.length > 1 ? Integer.parseInt(nums[1]) : 1;
      return new BigFraction(numerator, denominator);
    } else if (!registerSet.isStored(element)) {
      System.err.println(element + ": FAILED [Invalid expression]");
    } //If statement
    return registerSet.get(element.charAt(0));
  } //Method

    /**
   * @param input The input string
   * @param calculator Instance of BFCalculator type
   * @param registerSet Instance of BFRegisterSet type
   * @return The result of calculation
   * @throws Exception
   */
  public static BigFraction calculate(String input,
      BFCalculator calculator, BFRegisterSet registerSet) {
    String[] elements = input.split(" ");
    calculator.clear();
    calculator.add(readFraction(elements[0], registerSet));
    for (int i = 1; i < elements.length; i += 2) {
      if (elements[i].equals("+")) {
        calculator.add(readFraction(elements[i + 1], registerSet));
      } else if (elements[i].equals("-")) {
        calculator.subtract(readFraction(elements[i + 1], registerSet));
      } else if (elements[i].equals("*")) {
        calculator.multiply(readFraction(elements[i + 1], registerSet));
      } else if (elements[i].equals("/")) {
        calculator.divide(readFraction(elements[i + 1], registerSet));
      } else {
        System.err.println(input + ": FAILED [Invalid expression]");
      } //If statement
    } //For loop
    return calculator.get();
  } //Method


  /**
   * @param args take all of your calculations at the same time and do it in order.
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    for (String arg : args) {
      if (arg.equals("QUIT")) {
        break;
      } else if (arg.startsWith("STORE")) {
        registerSet.store(arg.charAt(STOREINDEX), calculator.get());
        pen.println("STORE " + arg.charAt(STOREINDEX) + " -> " + "STORED");
      } else {
        String[] elements = arg.split(" ");
        if (elements.length % 2 == 0) {
          System.err.println(arg + ": FAILED [Invalid expression]");
        } else if (arg.equals("")) {
          System.err.println("FAILED [Invalid expression]");
        } else {
          boolean flag = true;
          for (int i = 0; i < elements.length; i += 2) {
            char check = elements[i].charAt(0);
            if (!(Character.isDigit(check) || Character.isLowerCase(check)
                || elements[i].length() > 1)) {
              flag = false;
            } //If statement
          } //For Loop
          if (!flag) {
            System.err.println(arg + " FAILED [Invalid expression]");
          } else {
            BigFraction result = calculate(arg, calculator, registerSet);
            pen.println(arg + " -> " + result);
          } //If Statement
        } //If statement
      } //If statement
    } //For loop
  } //Main
} //Class
