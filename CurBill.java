package com.authentication;
import java.util.Scanner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CurBill {
	@Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Meter_No;
    private Long Cons_Units;
    private double BillAmount;
    private String MonthName;
   /* private String Status;
    public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}*/
	public String getMeter_No() {
		return Meter_No;
	}
	public void setMeter_No(String meter_No) {
		Meter_No = meter_No;
	}
	public Long getCons_Units() {
		return Cons_Units;
	}
	public void setCons_Units(Long cons_Units) {
		Cons_Units = cons_Units;
	}
	public double getBillAmount() {
		return BillAmount;
	}
	public void setBillAmount(double billAmount) {
		BillAmount = billAmount;
	}
	public String getMonthName() {
		return MonthName;
	}
	public void setMonthName(String monthName) {
		MonthName = monthName;
	}
	}