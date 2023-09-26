package com.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.utility.nullability.NeverNull;
@Entity
public class Meter {
	 @Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	   // private Long id;
	    private String Meter_No;
	    private String Cust_Full_Name;
	    private String Meter_Location;
	    private String Meter_type;
	    private String Bill_type;
	    // Constructors
	    public Meter() {
	    }
		public String getMeter_No() {
			return Meter_No;
		}
		public void setMeter_No(String meter_No) {
			Meter_No = meter_No;
		}
		public String getCust_Full_Name() {
			return Cust_Full_Name;
		}
		public void setCust_Full_Name(String cust_Full_Name) {
			Cust_Full_Name = cust_Full_Name;
		}
		public String getMeter_Location() {
			return Meter_Location;
		}
		public void setMeter_Location(String meter_Location) {
			Meter_Location = meter_Location;
		}
		public String getMeter_type() {
			return Meter_type;
		}
		public void setMeter_type(String meter_type) {
			Meter_type = meter_type;
		}
		public String getBill_type() {
			return Bill_type;
		}
		public void setBill_type(String bill_type) {
			Bill_type = bill_type;
		}
}
		