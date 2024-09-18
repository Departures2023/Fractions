package edu.grinnell.csc207.util;

/**
 * Handles the register for calculator.
 */
public class BFRegisterSet {
  /**
   * Number of alphabets.
   */
  public static final int ALPHABETS = 26;

  /**
   * ASCII value of a.
   */
  public static final int ASCIIA = 97;

  /**
   * RegisterSet.
   */
  private final BigFraction[] arr = new BigFraction[ALPHABETS];

  /**
   * @param register register number
   * @param val value to store
   */
  public void store(char register, BigFraction val) {
    arr[(int) register - ASCIIA] = val;
  } //store

  /**
   * @param register Register number
   * @return the stored value
   */
  public BigFraction get(char register) {
    return arr[(int) register - ASCIIA];
  } //get

  /**
   * @param register char of array
   * @return whether the register is stored with value
   */
  public boolean isStored(String register) {
    if (!register.matches("[a-z]")) {
      return false;
    } else if (this.arr[register.charAt(register.length() - 1)] == null) {
      return false;
    } else {
      return true;
    } //If statement
  } // isStored
} //BFRegisterSet
