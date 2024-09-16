package edu.grinnell.csc207.util;

/**
 * calculate BigFraction and temporily store the result.
 */
public class BFCalculator {
  /**
   * Store the last value.
   */
  private BigFraction lastVal;

  /**
   * Initialize lastVal to 0.
   */
  public BFCalculator() {
    lastVal = new BigFraction(0, 1);
  } //BFCalculator

  /**
   * @return lastVal
   */
  public BigFraction get() {
    return lastVal;
  } //get

  /**
   * @param val fraction for addition
   */
  public void add(BigFraction val) {
    lastVal = lastVal.add(val);
  } //add

  /**
   * @param val fraction for subtraction
   */
  public void subtract(BigFraction val) {
    lastVal = lastVal.subtract(val);
  } //subtract

  /**
   * @param val fraction for multiplication
   */
  public void multiply(BigFraction val) {
    lastVal = lastVal.multiply(val);
  } //multiply

  /**
   * @param val fraction for division
   */
  public void divide(BigFraction val) {
    lastVal = lastVal.divide(val);
  } //divide

  /**
   * Reset lastVal to 0.
   */
  public void clear() {
    lastVal = new BigFraction(0, 1);
  } //clear
} //BFCalculator
