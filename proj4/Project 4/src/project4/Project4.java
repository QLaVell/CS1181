package project4;

import java.io.File;
import java.io.FileNotFoundException;
import lists.CustomerList;
import lists.EventList;
import store_objects.Customer;
import store_objects.Register;
import store_objects.Clock;
import store_objects.Event;
import store_objects.EventType;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * This Project simulates a grocery store. Using a simulated clock, the program
 * tests the time and number of customers going through the store and checkout
 * lanes.
 */
public class Project4 {

    public static void main(String[] args) {
        try{
            DataInput customerArrivals = new DataInput(new File("arrival.txt"));

            // Create a clock object to access the time
            Clock clock = new Clock();

            // Create a register
            Register regRegister = new Register(0.05, 2.5, 1);
            
            // Create an event list to hold all of the events
            EventList eventList = new EventList();
            
            // Create a list to hold the customers in order of arival
            CustomerList custList = new CustomerList();
            int customers = 10;
            while(customers > 0){
//                double arrivalTime = customerArrivals.getNextDouble();
//                int numItems = customerArrivals.getNextInt();
//                double timePerItem = customerArrivals.getNextDouble();
                
                String[] custInfo = customerArrivals.getNextLine().split("\\s");
                
                if(custInfo.length == 3){
                    custList.addNewCustomer(new Customer(Double.parseDouble(custInfo[0]), 
                            Integer.parseInt(custInfo[1]), 
                            Double.parseDouble(custInfo[2])));
                }
                customers--;
            }
            
            int customerNumber = 1;

            // Create a new Customer
            while(!custList.isEmpty()){
                Customer cust = custList.getNextCustomer();
                cust.setCustomerNumber(customerNumber++);
//                customerNumber++;

//                clock.time(cust.getArrivalTime());
//                System.out.println(String.format("Customer %d arrives. Time is: %.3f", customerNumber, clock.time()));
                
                eventList.addNewEvent(new Event(EventType.customerArrival, cust.getArrivalTime(), new Object[] {cust}));
                eventList.addNewEvent(new Event(EventType.customerEndShopping, cust.getArrivalTime() + cust.getCheckoutTime(), new Object[] {cust}));
//                System.out.println("Customer arriving at register...");
//                clock.time(clock.time() + cust.getCheckoutTime());
//                System.out.println("Current time is: " + clock.time());
//                System.out.println("Checking out a customer");
//                regRegister.checkout(cust.getNumOfItems());
//                System.out.println("New time is: " + clock.time());
                
            }
            while(!eventList.isEmpty()){
                Customer cust;
                Event event = eventList.getNextEvent();
                clock.time(event.getEventTime());
                switch(event.getEventType()){
                    case customerArrival:
                        cust = (Customer)event.getEventParameter()[0];
                        System.out.println(String.format("Customer %d arrived at time %.2f", cust.getCustomerNumber(), clock.time()));
                        break;
                    case customerEndShopping:
                        cust = (Customer)event.getEventParameter()[0];
                        System.out.println(String.format("Customer %d entered lane at time %.2f", cust.getCustomerNumber(), clock.time()));
                        eventList.addNewEvent(new Event(EventType.customerEndCheckout, regRegister.checkout(cust.getNumOfItems()), new Object[] {cust, regRegister}));
                        break;
                    case customerEndCheckout:
                        cust = (Customer)event.getEventParameter()[0];
                        Register reg = (Register)event.getEventParameter()[1];
                        System.out.println(String.format("Customer %d left store from register %d at time %.2f", cust.getCustomerNumber(), reg.getRegisterNumber(), clock.time()));
                        break;
                    default:
                        
                        break;
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    
}
