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
        BigFraction result = InteractiveCalculator.calculate(arg, calculator, registerSet);
        pen.println(arg + " -> " + result);
      } //If statement
    } //For loop
  } //Main
} //Class
