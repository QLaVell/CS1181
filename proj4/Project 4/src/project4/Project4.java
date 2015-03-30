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
    static final int REGULAR_REGISTERS = 0;
    static final int EXPRESS_REGISTERS = 1;

    public static void main(String[] args) {
        try{
            DataInput customerArrivalsFile = new DataInput(new File("arrival.txt"));
            DataInput registerFile = new DataInput(new File("checkout.txt"));
            
            Register[][] registers = new Register[2][0];

            // Create a clock object to access the time
            Clock clock = new Clock();

            // Create a register
//            Register regRegister = new Register(0.07, 2, 1);
            
            // Create an event list to hold all of the events
            EventList eventList = new EventList();
            
            // Create a list to hold the customers in order of arival
            CustomerList custList = new CustomerList();
            
            // Read in the registers from the register file
            int registerType = REGULAR_REGISTERS;
            int regNum = 1;
            while(registerFile.hasNextLine()){
                if(registerFile.hasNextInt()){
                    // Get the number of registers
                    int numOfRegisters = registerFile.getNextInt();
                    registerFile.getNextLine();
                    Register[] registerArray = new Register[numOfRegisters];
                    
                    // Get the line for a register
                    for(int i = 0; i < registerArray.length; i++){
                        String[] regInfo = registerFile.getNextLine().split("\\s");
                        if(regInfo.length == 2){
                            // Read in the registers
                            registerArray[i] = new Register(Double.parseDouble(regInfo[0]), Double.parseDouble(regInfo[1]), regNum++);
                        }
                    }
                    
                    if(registerType == 1){
                        // Add the register to the double array
                        registers[registerType++] = registerArray;
                    }else{
                        registers[registerType++] = new Register[] {registerArray[0], registerArray[1], registerArray[2], registerArray[3]};
                    }
                }
            }
            
            int customers = 3839;
            while((customers > 0) && customerArrivalsFile.hasNextLine()){
//                double arrivalTime = customerArrivals.getNextDouble();
//                int numItems = customerArrivals.getNextInt();
//                double timePerItem = customerArrivals.getNextDouble();
                
                String[] custInfo = customerArrivalsFile.getNextLine().split("\\s");
                
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
                        System.out.println(String.format("Customer %d arrived at time %.2f to get %d items at %.2f items/minute", cust.getCustomerNumber(), clock.time(), cust.getNumOfItems(), cust.getTimePerItem()));
                        break;
                    case customerEndShopping:
                        boolean express = false;
                        cust = (Customer)event.getEventParameter()[0];
                        Register register;
                        if(cust.getNumOfItems() <= 12){
                            express = true;
                        }
                        register = getShortestRegister(registers, express);
                        register.addCustomer(cust);
                        System.out.println(String.format("Customer %d entered lane %d with %d items at time %.2f", cust.getCustomerNumber(), register.getRegisterNumber(), cust.getNumOfItems(), clock.time()));
                        eventList.addNewEvent(new Event(EventType.customerEndCheckout, register.checkout(cust), new Object[] {register}));
                        break;
                    case customerEndCheckout:
//                        cust = (Customer)event.getEventParameter()[0];
                        Register reg = (Register)event.getEventParameter()[0];
                        cust = reg.getNextCustomer();
                        System.out.println(String.format("Customer %d left store from register %d at time %.2f", cust.getCustomerNumber(), reg.getRegisterNumber(), clock.time()));
                        break;
                    default:
                        
                        break;
                }
            }
            
            for(Register[] regArray : registers){
                for(Register r : regArray){
                    System.out.println(String.format("Register %d had a maximum line of %d", r.getRegisterNumber(), r.getLongestLineLength()));
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    
    public static Register getShortestRegister(Register[][] registers, boolean express){
        Register reg = registers[REGULAR_REGISTERS][0];
        if(!express){
            for(Register register : registers[REGULAR_REGISTERS]){
                if(register.getLineLength() < reg.getLineLength()){
                    reg = register;
                }
            }
        }else{
            for(Register register : registers[EXPRESS_REGISTERS]){
                if(register.getLineLength() < reg.getLineLength()){
                    reg = register;
                }
            }
            for(Register register : registers[REGULAR_REGISTERS]){
                if(register.getLineLength() < reg.getLineLength()){
                    reg = register;
                }
            }
        }
        
        return reg;
    }
    
}
