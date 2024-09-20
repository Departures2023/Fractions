package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Take calculation command one by one.
 */
public class InteractiveCalculator {

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
    if (nums[0].equals("")) {
      System.err.println("FAILED [Invalid expression]");
      System.exit(1);
    } else if (nums[0].startsWith("-")) {
      return new BigFraction(element);
    } else if (nums.length > 1 && nums[1].startsWith("-")) {
      return new BigFraction("-" + nums[0] + "/" + nums[1].substring(1));
    } else if (Character.isDigit(nums[0].charAt(0))) {
      int numerator = Integer.parseInt(nums[0]);
      int denominator = nums.length > 1 ? Integer.parseInt(nums[1]) : 1;
      return new BigFraction(numerator, denominator);
    } else if (!registerSet.isStored(element)) {
      System.err.println("*** ERROR [Invalid expression] ***");
      System.exit(1);
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
    if (elements.length % 2 == 0) {
      System.err.println("*** ERROR [Invalid expression] ***");
      System.exit(1);
    } //If statement
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
        System.err.println("*** ERROR [Invalid expression] ***");
        System.exit(1);
      } //If statement
    } //For loop
    return calculator.get();
  } //Method

  /**
   * @param args string for calculation
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eye = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    String input;

    while (true) {
      pen.print("> ");
      pen.flush();
      input = eye.nextLine();
      if (input.equals("QUIT")) {
        break;
      } else if (input.startsWith("STORE")) {
        if (Character.isLowerCase(input.charAt(STOREINDEX))) {
          registerSet.store(input.charAt(STOREINDEX), calculator.get());
          pen.println("STORED");
        } else {
          System.err.println("*** ERROR [STORE command received invalid register] ***");
          break;
        } //if statement
      } else {
        pen.println(calculate(input, calculator, registerSet));
      } //If statement
    } //For loop
  } //Main
} //Interactive Calculator
