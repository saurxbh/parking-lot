package dev.saurabh.parkinglot.service;

import dev.saurabh.parkinglot.entity.Bill;

public interface BillService {
    Bill generateBill(Long ticketId);
}