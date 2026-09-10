package com.lseg.paymentservice;

public class Payment {

    private int id;
    private double amount;
    private String currency;
    private String status;
    private double commission;


    public Payment() {
    }

    public Payment(int id,  String currency, String status, double commission,double amount) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.commission=commission;
    }

    public int getId() {
        return id;
    }

  public double getAmount() {
       return amount;
    }

    public double getCommission() {
        return commission;
    }


    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }
}