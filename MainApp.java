package com.authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = buildSessionFactory();

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (checkLogin(username, password, sessionFactory)) {
                System.out.println("Login successful!");
                loggedIn = true;
            } else {
                System.out.println("Invalid credentials. Do you want to retry? (y/n)");
                char choice = scanner.next().charAt(0);
                if (choice == 'y' || choice == 'Y') {
                    // Continue to the next iteration of the loop
                    scanner.nextLine(); // Clear the newline character
                    continue;
                } else {
                    System.out.println("Exiting program.");
                    System.exit(0);
                }
            }
        }

        // After successful login, display the menu options
        displayMenu(scanner, sessionFactory);

        sessionFactory.close();
        scanner.close();
    }

    private static void displayMenu(Scanner scanner, SessionFactory sessionFactory) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Meter Details");
            System.out.println("2. Customer Details");
            System.out.println("3. Current Month Bill");
            System.out.println("4. Bill History");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                	MeterDetails(sessionFactory, scanner);
                    break;
                case 2:
                	CustomerDetails(sessionFactory, scanner);
                    break;
               case 3:
                	CurrentMonthBill(sessionFactory, scanner);
                	                    break; 
                case 4:
                	BillHistoryDetails(sessionFactory, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

 
	private static void MeterDetails(SessionFactory sessionFactory, Scanner scanner) {
        // Implement Meter management here...
    
    	    boolean exit = false;
      	    while (!exit) {
    	        System.out.println("\nElectric Billing  System:");
    	        System.out.println("1. Add Meter_Info");
    	        System.out.println("2. View Meter_Info");
    	        System.out.println("3. Go back to main menu");
    	        System.out.print("Enter your choice: ");
    	        int choice = scanner.nextInt();
    	        scanner.nextLine(); // Clear the newline character
    	        switch (choice) {
    	            case 1:
    	                addMeter(sessionFactory, scanner);
    	                break;
    	            case 2:
    	                viewMeter(sessionFactory);
    	                break;
    	            case 3:
    	                exit = true;
    	                System.out.println("Returning to main menu.");
    	                break;
    	            default:
    	                System.out.println("Invalid choice. Please select a valid option.");
    	        }
    	    }
    }
    	private static void addMeter(SessionFactory sessionFactory, Scanner scanner) {
    	    // Implement adding a new Customer here...
    		 try (Session session = sessionFactory.openSession()) {
    		        Transaction transaction = session.beginTransaction();

    		        System.out.println("Enter Meter details:");
    		        System.out.print("Meter_no: ");
    		        String Meter_No = scanner.nextLine();
          		
    		        System.out.print("CustomerFullname: ");
    		        String Cust_Full_Name = scanner.nextLine();
       		        System.out.print("Address: ");
    		        String Meter_Location = scanner.nextLine();
    		        System.out.print("Meter_type: ");
    		        String Meter_type = scanner.nextLine();
    		        System.out.print("Bill_type: ");
    		        String Bill_type = scanner.nextLine();
    		       Meter meter = new Meter();
    		       meter.setMeter_No(Meter_No);
    		        meter.setCust_Full_Name(Cust_Full_Name);
    		        meter.setMeter_Location(Meter_Location);
    		        meter.setMeter_type(Meter_type);
    		        meter.setBill_type(Bill_type);

    		        session.save(meter);
    		        transaction.commit();

    		        System.out.println("Meter Details added successfully.");
    		    } catch (Exception e) {
    		        e.printStackTrace();
    		    }
    	}

    	private static void viewMeter(SessionFactory sessionFactory) {
    	    try (Session session = sessionFactory.openSession()) {
    	        String hql = "FROM Meter";
    	        Query<Meter> query = session.createQuery(hql, Meter.class);
    	        List<Meter> meters = query.list();

    	        if (!meters.isEmpty()) {
    	            System.out.println("List of Meters:");
    	            for (Meter meter : meters) {
    	                System.out.println("Meter_No: " + meter.getMeter_No());
    	                System.out.println("Cust_Full_Name: " + meter.getCust_Full_Name());
    	                System.out.println("Address: " + meter.getMeter_Location());
    	                System.out.println("Meter_type: " + meter.getMeter_type());
    	                System.out.println("Bill_type: " + meter.getBill_type());
    	                System.out.println("--------------------");
    	            }
    	        } else {
    	            System.out.println("No Meter Details found.");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("An error occurred while viewing Meter Details.");
    	    }
    	}
  
   
  
    private static void CustomerDetails(SessionFactory sessionFactory, Scanner scanner) {
        // Implement Customer management here...
    
    	    boolean exit = false;
    	    
    	    while (!exit) {
    	        System.out.println("\nElectric Billing  System:");
    	        System.out.println("1. Add Customer");
    	        System.out.println("2. View Customer");
    	        System.out.println("3. Update Customer Details");
    	        System.out.println("4. Delete Customer");
    	        System.out.println("5. Go back to main menu");

    	        System.out.print("Enter your choice: ");
    	        int choice = scanner.nextInt();
    	        scanner.nextLine(); // Clear the newline character

    	        switch (choice) {
    	            case 1:
    	                addCustomer(sessionFactory, scanner);
    	                break;
    	            case 2:
    	                viewCustomer(sessionFactory);
    	                break;
    	            case 3:
    	                updateCustomerDetails(sessionFactory, scanner);
    	                break;
    	            case 4:
    	                deleteCustomer(sessionFactory, scanner);
    	                break;
    	            case 5:
    	                exit = true;
    	                System.out.println("Returning to main menu.");
    	                break;
    	            default:
    	                System.out.println("Invalid choice. Please select a valid option.");
    	        }
    	    }
    }

    	private static void addCustomer(SessionFactory sessionFactory, Scanner scanner) {
    	    // Implement adding a new Customer here...
    		 try (Session session = sessionFactory.openSession()) {
    		        Transaction transaction = session.beginTransaction();

    		        System.out.println("Enter Customer details:");
    		        System.out.print("Full Name: ");
    		        String Cust_Full_Name = scanner.nextLine();
    		        System.out.print("Meter_No: ");
    		        String Meter_No = scanner.nextLine();
    		        System.out.print("Address: ");
    		        String address = scanner.nextLine();
    		        System.out.print("City: ");
    		        String City = scanner.nextLine();
      		        System.out.print("State: ");
    		        String State = scanner.nextLine();
    		        System.out.print("Phone Number: ");       
    		        String phoneNumber = scanner.nextLine();
                    String phoneNumberRegex = "^[0-9]{10}$"; // 10-digit number
    		        Pattern pattern = Pattern.compile(phoneNumberRegex);
    		        Matcher matcher = pattern.matcher(phoneNumber);

    		        if (!matcher.matches()) {
    		            System.out.println("Invalid phone number format. Please enter a 10-digit number.");
    		            return;
    		        }
      		        Customer customer = new Customer();
    		        customer.setCust_Full_Name(Cust_Full_Name);
    		        customer.setMeter_No(Meter_No);
    		        customer.setAddress(address);
    		        customer.setCity(City);
    		        customer.setState(State);
    		        customer.setPhoneNumber(phoneNumber);
    		        
    		        session.save(customer);
    		        transaction.commit();

    		        System.out.println("customer added successfully.");
    		    } catch (Exception e) {
    		        e.printStackTrace();
    		    }
    	}

    	private static void viewCustomer(SessionFactory sessionFactory) {
    	    try (Session session = sessionFactory.openSession()) {
    	        String hql = "FROM Customer";
    	        Query<Customer> query = session.createQuery(hql, Customer.class);
    	        List<Customer> customers = query.list();

    	        if (!customers.isEmpty()) {
    	            System.out.println("List of customers:");
    	            for (Customer customer : customers) {
    	                System.out.println("Customer ID: " + customer.getId());
    	                System.out.println("Customer Name: " + customer.getCust_Full_Name());
    	                System.out.println("Meter_No: " + customer.getMeter_No());
    	                System.out.println("Address: " + customer.getAddress());
    	                System.out.println("City: " + customer.getCity());
    	                System.out.println("State: " + customer.getState());
    	                System.out.println("Phone Number: " + customer.getPhoneNumber());
       	                System.out.println("--------------------");
    	            }
    	        } else {
    	            System.out.println("No customers found.");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("An error occurred while viewing customers.");
    	    }
    	}


    	private static void updateCustomerDetails(SessionFactory sessionFactory, Scanner scanner) {
    	    // Implement updating customer details here...
    		 try (Session session = sessionFactory.openSession()) {
    		        Transaction transaction = session.beginTransaction();

    		        System.out.print("Enter customer ID to update: ");
    		        long customertId = scanner.nextLong();
    		        scanner.nextLine(); // Clear the newline character

    		        Customer customer = session.get(Customer.class, customertId);

    		        if (customer == null) {
    		            System.out.println("customer not found.");
    		        } else {
    		        	boolean exit = false;
    		    	    
    		    	    while (!exit) {
    		    	        System.out.println("\nCustomer Menu:");
    		    	        System.out.println("1. Full Name");
    		    	        System.out.println("2. Meter Num");
    		    	        System.out.println("3. Address");
    		    	        System.out.println("4. City");
    		    	        System.out.println("5. State");
    		    	        System.out.println("6. Phone Number");
    		    	        System.out.println("7. exit");
    		    	        System.out.print("Enter your choice: ");
    		    	        int choice = scanner.nextInt();
    		    	        scanner.nextLine(); // Clear the newline character

    		    	        switch (choice) {
    		    	            case 1:
    		    	            	System.out.print("Full Name: ");
    		    		            customer.setCust_Full_Name(scanner.nextLine());
    		    	                break;
    		    	            case 2:
    		    	            	System.out.print("Meter_No: ");
    		    		            customer.setMeter_No(scanner.nextLine());
    		    	                break;
    		    	            case 3:
    		    	            	System.out.print("Address: ");
    		    		            customer.setAddress(scanner.nextLine());
    		    	                break;
    		    	            case 4:
    		    	            	System.out.print("City: ");
    		    		            customer.setCity(scanner.nextLine());
    		    	                break;
    		    	            case 5:
    		    	                System.out.print("State: ");
    		    		            customer.setState(scanner.nextLine());
    		    		            break;
    		    	            case 6:
    		    	            	 System.out.print("Phone Number: ");
    		     		            customer.setPhoneNumber(scanner.nextLine());
    		    		            break;
    		    	            case 7:
    		    	                exit = true;
    		    	                System.out.println("Returning to main menu.");
    		    	                break;
    		    	            default:
    		    	                System.out.println("Invalid choice. Please select a valid option.");
    		    	        
    		             }
    		    	        } session.update(customer);
    		            transaction.commit();

    		            System.out.println("customer details updated successfully.");
    		    	    }
    		        }
    		        
    		     catch (Exception e) {
    		        e.printStackTrace();
    		    }
    	}
    		 
    	private static void deleteCustomer(SessionFactory sessionFactory, Scanner scanner) {
    	    // Implement deleting a customer here...
    		 try (Session session = sessionFactory.openSession()) {
    		        Transaction transaction = session.beginTransaction();

    		        System.out.print("Enter customer ID to delete: ");
    		        long customerId = scanner.nextLong();
    		        scanner.nextLine(); // Clear the newline character

    		        Customer customer = session.get(Customer.class, customerId);

    		        if (customer == null) {
    		            System.out.println("customer not found.");
    		        } else {
    		            session.delete(customer);
    		            transaction.commit();
    		        	System.out.println("customer deleted successfully.");
    		        }
    		    } catch (Exception e) {
    		        e.printStackTrace();
    		    }
    	}
    	  	
       	private static void CurrentMonthBill(SessionFactory sessionFactory, Scanner scanner) {
            // Implement Bill management here...
        
        	    boolean exit = false;
        	    
        	    while (!exit) {
        	        System.out.println("\nElectric Billing  System:");
        	        System.out.println("1. Read Input Details");
        	        System.out.println("2. View BillAmount");
        	        System.out.println("3. Go back to main menu");

        	        System.out.print("Enter your choice: ");
        	        int choice = scanner.nextInt();
        	        scanner.nextLine(); // Clear the newline character

        	        switch (choice) {
        	            case 1:
        	                addCurBill(sessionFactory, scanner);
        	                break;
        	           case 2:
        	                viewCurBill(sessionFactory);
        	                break;
        	            case 3:
        	                exit = true;
        	                System.out.println("Returning to main menu.");
        	                break;
        	            default:
        	                System.out.println("Invalid choice. Please select a valid option.");
        	        }
        	    }
        }

        	private static void addCurBill(SessionFactory sessionFactory, Scanner scanner) {
        	    // Implement  BillAmount here...
        		 try (Session session = sessionFactory.openSession()) {
        		        Transaction transaction = session.beginTransaction();

        		        System.out.println("Enter Meter_Units details:");
        		        System.out.print("Meter_no: ");
        		        String Meter_No = scanner.nextLine();
        		        System.out.print("Month: ");
          		       String MonthName = scanner.nextLine();
          		        System.out.print("units: ");
        		        Long Cons_Units = scanner.nextLong();        		       
        		       System.out.print("BillAmount: ");
       		        
    			        
    	          // variable to calculate electricity bill to pay  
    	        double billTopay = 0;  
    	        // check whether units are less than 100  
    	        if(Cons_Units < 100)  
    	        {  
    	            billTopay = Cons_Units * 1.80;  
    	        }  
    	        // check whether the units are less than 200  
    	        else if(Cons_Units <= 200){  
    	        	billTopay = 100 * 1.80 + (Cons_Units - 100) * 2;  
    	        }  
    	        // check whether the units are greater than 200  
    	        else if(Cons_Units > 200)  
    	        {  
    	        	billTopay = 100 * 1.80 + 200 * 2 + (Cons_Units - 200) * 3;  
    	        }  
    	        System.out.println("The electricity bill for " +Cons_Units+ "  units is : " + billTopay);
    	      
        		       CurBill billamount = new CurBill();
        		        billamount.setMeter_No(Meter_No);
        		        billamount.setCons_Units(Cons_Units);
        		        billamount.setMonthName(MonthName);        		          		        
        		        billamount.setBillAmount(billTopay);

        		        session.save(billamount);
        		        transaction.commit();

        		        System.out.println("Billing Details added successfully.");
        		    } catch (Exception e) {
        		        e.printStackTrace();
        		    }
        	}
        	private static void viewCurBill(SessionFactory sessionFactory) {
        	    try (Session session = sessionFactory.openSession()) {
        	        String hql = "FROM CurBill";
        	        Query<CurBill> query = session.createQuery(hql, CurBill.class);
        	        List<CurBill> curbills = query.list();

        	        if (!curbills.isEmpty()) {
        	            System.out.println("List of Meters:");
        	            for (CurBill curbill : curbills) {
        	                System.out.println("Meter_No: " + curbill.getMeter_No());
        	                System.out.println("Cons_Units: " + curbill.getCons_Units());
        	                System.out.println("Month: " + curbill.getMonthName());
        	                System.out.println("BillAmount: " + curbill.getBillAmount());
        	                System.out.println("--------------------");
        	            }
        	        } else {
        	            System.out.println("No BILL Details found.");
        	        }
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	        System.out.println("An error occurred while viewing Meter Details.");
        	    }
        	}

    	//Bill History 
    	 private static void BillHistoryDetails(SessionFactory sessionFactory, Scanner scanner) {
    	        // Implement History management here...
    	    
    	    	    boolean exit = false;
    	    	    
    	    	    while (!exit) {
    	    	        System.out.println("\nElectric Billing  System:");
    	    	        System.out.println("1. Add BillHistory");
    	    	        System.out.println("2. View BillHistory");
    	   	        System.out.println("3. Go back to main menu");

    	    	        System.out.print("Enter your choice: ");
    	    	        int choice = scanner.nextInt();
    	    	        scanner.nextLine(); // Clear the newline character

    	    	        switch (choice) {
    	    	            case 1:
    	    	                addBillHistory(sessionFactory, scanner);
    	    	                break;
    	    	            case 2:
    	    	                viewBillHistory(sessionFactory);
    	    	                break;
    	    	           
    	    	            case 3:
    	    	                exit = true;
    	    	                System.out.println("Returning to main menu.");
    	    	                break;
    	    	            default:
    	    	                System.out.println("Invalid choice. Please select a valid option.");
    	    	        }
    	    	    }
    	    }

    	    	private static void addBillHistory(SessionFactory sessionFactory, Scanner scanner) {
    	    	    // Implement adding a new Bill here...
    	    		 try (Session session = sessionFactory.openSession()) {
    	    		        Transaction transaction = session.beginTransaction();

    	    		        System.out.println("Enter BillHistory details:");
    	    		        System.out.print("Meter_No: ");
    	    		        String Meter_No = scanner.nextLine();
    	    		       System.out.print("Month Name: ");
    	    		        String MonthName = scanner.nextLine();
    	    		
    	      		        System.out.print("Enetr Bill Amount: ");
    	    		        int Bill_Amount = scanner.nextInt();

    	    		        BillHistory billhistory = new BillHistory();
    	    		        billhistory.setMeter_No(Meter_No);
    	    		        billhistory.setMonthName(MonthName);   		        
    	    		        billhistory.setBill_Amount(Bill_Amount);

    	    		        session.save(billhistory);
    	    		        transaction.commit();

    	    		        System.out.println("Bill added successfully.");
    	    		    } catch (Exception e) {
    	    		        e.printStackTrace();
    	    		    }
    	    	}

    	    	private static void viewBillHistory(SessionFactory sessionFactory) {
    	    	    try (Session session = sessionFactory.openSession()) {
    	    	        String hql = "FROM BillHistory";
    	    	        Query<BillHistory> query = session.createQuery(hql, BillHistory.class);
    	    	        List<BillHistory> billhistorys = query.list();

    	    	        if (!billhistorys.isEmpty()) {
    	    	            System.out.println("List of billhistory:");
    	    	            for (BillHistory billhistory : billhistorys) {
    	    	                System.out.println("Customer Meter_No: " + billhistory.getMeter_No());
    	    	                System.out.println("Current MonthName: " + billhistory.getMonthName());
    	       	                System.out.println("Bill_Amount: " + billhistory.getBill_Amount());
    	   	                System.out.println("--------------------");
    	    	            }
    	    	        } else {
    	    	            System.out.println("No History found.");
    	    	        }
    	    	    } catch (Exception e) {
    	    	        e.printStackTrace();
    	    	        System.out.println("An error occurred while viewing History.");
    	    	    }
    	    	}

    	    	private static SessionFactory buildSessionFactory() {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure() // Load hibernate.cfg.xml by default
                    .build();

            return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        }

        private static boolean checkLogin(String username, String password, SessionFactory sessionFactory) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Login WHERE username = :username AND password = :password";
                Query<Login> query = session.createQuery(hql, Login.class);
                query.setParameter("username", username);
                query.setParameter("password", password);

                List<Login> result = query.list();

                return !result.isEmpty();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        private static Date parseDate(String dateString) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        }
    }
