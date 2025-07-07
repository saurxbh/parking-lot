package dev.saurabh.parkinglot.entity;

public interface BillCalculationStrategy {
    double calculateBill(Ticket ticket);
}
