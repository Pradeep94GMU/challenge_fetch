package com.pradeep.receipt_processor.controller;

import com.pradeep.receipt_processor.model.Receipt;
import com.pradeep.receipt_processor.service.PointsCalculator;
import com.pradeep.receipt_processor.service.ReceiptStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptStorage receiptStorage;

    public ReceiptController(ReceiptStorage receiptStorage) {
        this.receiptStorage = receiptStorage;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptStorage.saveReceipt(receipt);
        return ResponseEntity.ok("{\"id\":\"" + id + "\"}");
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<String> getReceiptPoints(@PathVariable String id) {
        Receipt receipt = receiptStorage.getReceiptById(id);
        if (receipt == null) {
            return ResponseEntity.status(404).body("{\"error\":\"Receipt not found\"}");
        }

        // Calculate points
        int points = PointsCalculator.calculatePoints(receipt);
        return ResponseEntity.ok("{\"points\": " + points + "}");
    }
}