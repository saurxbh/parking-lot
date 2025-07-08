package dev.saurabh.parkinglot.controller;

import dev.saurabh.parkinglot.entity.Bill;
import dev.saurabh.parkinglot.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/generate/{ticketId}")
    public ResponseEntity<Bill> generateBill(@PathVariable Long ticketId) {
        Bill bill = billService.generateBill(ticketId);
        return ResponseEntity.ok(bill);
    }
}