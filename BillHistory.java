package com.authentication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BillHistory {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String MonthName;
   private String Meter_No;
   private int Bill_Amount;
    // Constructors
    public BillHistory() {
    }
	public String getMonthName() {
		return MonthName;
	}
	public void setMonthName(String monthName) {
		MonthName = monthName;
	}
	public String getMeter_No() {
		return Meter_No;
	}
	public void setMeter_No(String meter_No) {
		Meter_No = meter_No;
	}
	public int getBill_Amount() {
		return Bill_Amount;
	}
	public void setBill_Amount(int bill_Amount) {
		Bill_Amount = bill_Amount;
	}

}