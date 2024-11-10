package com.pradeep.receipt_processor.service;

import com.pradeep.receipt_processor.model.Receipt;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReceiptStorage {
    private final Map<String, Receipt> receiptMap = new HashMap<>();

    public String saveReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptMap.put(id, receipt);
        return id;
    }

    public Receipt getReceiptById(String id) {
        //System.out.println(receiptMap);
        return receiptMap.get(id);
    }
}
