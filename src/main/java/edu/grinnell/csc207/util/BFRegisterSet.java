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
} //BFRegisterSet
