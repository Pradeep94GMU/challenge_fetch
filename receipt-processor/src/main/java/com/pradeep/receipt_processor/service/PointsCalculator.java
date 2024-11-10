package com.pradeep.receipt_processor.service;

import com.pradeep.receipt_processor.model.Item;
import com.pradeep.receipt_processor.model.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PointsCalculator {

    public static int calculatePoints(Receipt receipt) {
        int points = 0;

        System.out.println("Starting Points Calculation...");

        // 1. Retailer Name Points
        int retailerPoints = calculateRetailerPoints(receipt.getRetailer());
        System.out.println("Retailer Name Points: " + retailerPoints);
        points += retailerPoints;

        // 2. Total Amount Points
        int totalPoints = calculateTotalPoints(receipt.getTotal());
        System.out.println("Total Points from Amount: " + totalPoints);
        points += totalPoints;

        // 3. Item Description Points
        int itemDescriptionPoints = calculateItemDescriptionPoints(receipt.getItems());
        System.out.println("Items Points: " + itemDescriptionPoints + " (" + receipt.getItems().size() + " items give " + itemDescriptionPoints + " points)");
        points += itemDescriptionPoints;

        // 4. Item Price Points
        int itemPricePoints = calculateItemPricePoints(receipt.getItems());
        System.out.println("Item Price Points: " + itemPricePoints);
        points += itemPricePoints;

        // 5. Purchase Date Points
        int purchaseDatePoints = calculatePurchaseDatePoints(receipt.getPurchaseDate());
        System.out.println("Purchase Date Points: " + purchaseDatePoints + " (" + receipt.getPurchaseDate() + " is an " + (purchaseDatePoints > 0 ? "odd" : "even") + " day)");
        points += purchaseDatePoints;

        // 6. Purchase Time Points
        int purchaseTimePoints = calculatePurchaseTimePoints(receipt.getPurchaseTime());
        System.out.println("Purchase Time Points: " + purchaseTimePoints + " (time is " + (purchaseTimePoints > 0 ? "between 2 PM and 4 PM" : "not between 2 PM and 4 PM") + ")");
        points += purchaseTimePoints;

        // Final points summary
        System.out.println("Total Points: " + points);
        return points;
    }

    private static int calculateRetailerPoints(String retailer) {
        int points = (int) retailer.chars().filter(Character::isLetterOrDigit).count();
        return points;
    }

    private static int calculateTotalPoints(String total) {
        BigDecimal totalAmount = new BigDecimal(total);
        int points = 0;

        // Check if the total amount is a round dollar amount
        if (totalAmount.stripTrailingZeros().scale() <= 0) {
            points += 50;
            System.out.println("Round Dollar Total: 50 (round dollar amount)");
        } else {
            System.out.println("Round Dollar Total: 0 (not round)");
        }

        // Check if the total amount is a multiple of 0.25
        if (totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
            System.out.println("Multiple of 0.25: 25 (multiple of 0.25)");
        } else {
            System.out.println("Multiple of 0.25: 0 (not a multiple of 0.25)");
        }

        return points;
    }

    private static int calculateItemDescriptionPoints(List<Item> items) {
        int points = (items.size() / 2) * 5;
        return points;
    }

    private static int calculateItemPricePoints(List<Item> items) {
        int points = 0;
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                int pricePoints = price.multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue();
                points += pricePoints;
                System.out.println("Item: \"" + description + "\" Price: " + item.getPrice() +
                        " - Points: " + pricePoints + " (description length multiple of 3)");
            } else {
                System.out.println("Item: \"" + description + "\" Price: " + item.getPrice() +
                        " - Points: 0 (description length not multiple of 3)");
            }
        }
        return points;
    }

    private static int calculatePurchaseDatePoints(String purchaseDate) {
        LocalDate date = LocalDate.parse(purchaseDate);
        int points = (date.getDayOfMonth() % 2 != 0) ? 6 : 0;
        return points;
    }

    private static int calculatePurchaseTimePoints(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        LocalTime start = LocalTime.of(14, 0); // 2:00 PM
        LocalTime end = LocalTime.of(16, 0);   // 4:00 PM
        int points = (time.isAfter(start) && time.isBefore(end)) ? 10 : 0;
        return points;
    }
}
