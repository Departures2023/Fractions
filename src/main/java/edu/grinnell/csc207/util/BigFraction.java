package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Handles Fraction calculations.
 */
public class BigFraction {

  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    BigInteger gcd = numerator.gcd(denominator);
    this.num = numerator.divide(gcd);
    this.denom = denominator.divide(gcd);
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this (BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
  } // BigFraction(int, int)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @param string string of fraction
   */
  public BigFraction(String string) {
    String[] nums = string.split("/");
    BigInteger numerator = BigInteger.valueOf(Integer.parseInt(nums[0]));
    BigInteger denominator = BigInteger.valueOf(nums.length > 1 ? Integer.parseInt(nums[1]) : 1);
    BigInteger gcd = numerator.gcd(denominator);
    this.num = numerator.divide(gcd);
    this.denom = denominator.divide(gcd);
  } // BigFraction(String)

/**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    resultNumerator = (this.num.multiply(addend.denom)).add(this.denom.multiply(addend.num));
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * @param subend fraction to substract
   * @return The substracted fraction
   */
  public BigFraction subtract(BigFraction subend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(subend.denom);
    resultNumerator = (this.num.multiply(subend.denom)).subtract(this.denom.multiply(subend.num));
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * @param multiplier fraction to multiply
   * @return The multiplied fraction
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger resultNumerator = this.num.multiply(multiplier.num);
    BigInteger resultDenominator = this.denom.multiply(multiplier.denom);
    return new BigFraction(resultNumerator, resultDenominator);
  } //multiply

  /**
   * @param divisor fraction to divide
   * @return The divided fraction
   */
  public BigFraction divide(BigFraction divisor) {
    // if (divisor.num.equals(BigInteger.ZERO)) {
    //   System.err.println("Cannot divide by zero.");
    // }
    BigInteger resultNumerator = this.num.multiply(divisor.denom);
    BigInteger resultDenominator = this.denom.multiply(divisor.num);
    return new BigFraction(resultNumerator, resultDenominator);
  } //divide


  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  @Override
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } else if (this.denom.equals(BigInteger.ONE)) {
      return this.num + "";
    } //If statement
    return this.num + "/" + this.denom;
  } // toString()

} //BigFraction
