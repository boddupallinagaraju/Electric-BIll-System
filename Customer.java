package com.authentication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
			 @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		    private String Cust_Full_Name;
		    private String Meter_No;
		    private String address;
		    private String City;
		    private String State;
		    private String phoneNumber;
		    // Constructors
		    public Customer() {
		    }
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getCust_Full_Name() {
				return Cust_Full_Name;
			}
			public void setCust_Full_Name(String cust_Full_Name) {
				Cust_Full_Name = cust_Full_Name;
			}
			public String getMeter_No() {
				return Meter_No;
			}
			public void setMeter_No(String meter_No) {
				Meter_No = meter_No;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getCity() {
				return City;
			}
			public void setCity(String city) {
				City = city;
			}
			public String getState() {
				return State;
			}
			public void setState(String state) {
				State = state;
			}
			public String getPhoneNumber() {
				return phoneNumber;
			}
			public void setPhoneNumber(String phoneNumber) {
				this.phoneNumber = phoneNumber;
			}

			}
