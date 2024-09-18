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
   * @throws Exception
   */
  private static BigFraction readFraction(String element, BFRegisterSet registerSet)
      throws Exception {
    if (!registerSet.isStored(element)) {
      throw new Exception("*** ERROR [Invalid expression] ***");
    } else if (element.matches("[a-z]")) {
      return registerSet.get(element.charAt(0));
    } else {
      String[] nums = element.split("/");
      int numerator = Integer.parseInt(nums[0]);
      int denominator = nums.length > 1 ? Integer.parseInt(nums[1]) : 1;
      return new BigFraction(numerator, denominator);
    } //If statement
  } //Method

  /**
   * @param input The input string
   * @param calculator Instance of BFCalculator type
   * @param registerSet Instance of BFRegisterSet type
   * @return The result of calculation
   * @throws Exception
   */
  public static BigFraction calculate(String input,
      BFCalculator calculator, BFRegisterSet registerSet) throws Exception {
    String[] elements = input.split(" ");
    if (elements.length % 2 == 0) {
      throw new Exception("*** ERROR [Invalid expression] ***");
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
        throw new Exception("*** ERROR [Invalid expression] ***");
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
        if (!registerSet.isStored(input)) {
          System.err.println("*** ERROR [Invalid expression] ***");
          break;
        } else {
          registerSet.store(input.charAt(STOREINDEX), calculator.get());
          pen.println("STORED");
        } //if statement
      } else {
        pen.println(calculate(input, calculator, registerSet));
      } //If statement
    } //For loop
  } //Main
} //Interactive Calculator
