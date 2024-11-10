package com.pradeep.receipt_processor.service;

import com.pradeep.receipt_processor.model.Item;
import com.pradeep.receipt_processor.model.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class PointsCalculator {

    public static int calculatePoints(Receipt receipt) {
        int points = 0;

        // 1. Retailer Name Points
        points += calculateRetailerPoints(receipt.getRetailer());

        // 2. Total Amount Points
        points += calculateTotalPoints(receipt.getTotal());

        // 3. Item Description Points
        points += calculateItemDescriptionPoints(receipt.getItems());

        // 4. Item Price Points
        points += calculateItemPricePoints(receipt.getItems());

        // 5. Purchase Date Points
        points += calculatePurchaseDatePoints(receipt.getPurchaseDate());

        // 6. Purchase Time Points
        points += calculatePurchaseTimePoints(receipt.getPurchaseTime());

        return points;
    }

    private static int calculateRetailerPoints(String retailer) {
        return (int) retailer.chars().filter(Character::isLetterOrDigit).count();
    }

    private static int calculateTotalPoints(String total) {
        BigDecimal totalAmount = new BigDecimal(total);
        int points = 0;

        // Round number check
        if (totalAmount.scale() == 0) {
            points += 50;
        }

        // Multiple of 0.25 check
        if (totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }

        return points;
    }

    private static int calculateItemDescriptionPoints(java.util.List<Item> items) {
        return (items.size() / 2) * 5;
    }

    private static int calculateItemPricePoints(java.util.List<Item> items) {
        int points = 0;

        for (Item item : items) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                points += price.multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue();
            }
        }

        return points;
    }

    private static int calculatePurchaseDatePoints(String purchaseDate) {
        LocalDate date = LocalDate.parse(purchaseDate);
        return (date.getDayOfMonth() % 2 != 0) ? 6 : 0;
    }

    private static int calculatePurchaseTimePoints(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        LocalTime start = LocalTime.of(14, 0); // 2:00 PM
        LocalTime end = LocalTime.of(16, 0);   // 4:00 PM

        return (time.isAfter(start) && time.isBefore(end)) ? 10 : 0;
    }
}
