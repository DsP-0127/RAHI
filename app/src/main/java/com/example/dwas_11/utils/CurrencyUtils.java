package com.example.dwas_11.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class to handle currency operations throughout the app.
 * This centralizes currency conversion and formatting logic.
 */
public class CurrencyUtils {
    // Conversion rates (can be updated from an API in a real app)
    public static final double USD_TO_INR_RATE = 83.0;
    
    private static final NumberFormat inrFormatter;
    
    static {
        inrFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        inrFormatter.setMinimumFractionDigits(0);
        inrFormatter.setMaximumFractionDigits(0);
    }
    
    /**
     * Convert USD to INR
     * @param usdAmount Amount in USD
     * @return Equivalent amount in INR
     */
    public static double convertUsdToInr(double usdAmount) {
        return usdAmount * USD_TO_INR_RATE;
    }
    
    /**
     * Format a price in INR with the ₹ symbol
     * @param inrAmount Amount in INR
     * @return Formatted string with currency symbol
     */
    public static String formatInr(double inrAmount) {
        return inrFormatter.format(inrAmount);
    }
    
    /**
     * Convert USD to INR and format the result
     * @param usdAmount Amount in USD
     * @return Formatted string in INR with currency symbol
     */
    public static String convertAndFormatUsdToInr(double usdAmount) {
        return formatInr(convertUsdToInr(usdAmount));
    }
} 