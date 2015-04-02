package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        PrintWriter log = null;
        try {
            DataInput customerArrivalsFile = new DataInput(
                    new File("arrival.txt"));
            DataInput registerFile = new DataInput(new File("1-0.txt"));
            String outputFilename = "18Reg1Exp.txt";
            log = new PrintWriter(new File(outputFilename));

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
            int run = 0;
            boolean done = false;
            String registerLine = registerFile.getNextLine();
            while (!registerLine.equals("") && !done) {
                if (registerLine.matches("[0-9]+")) {
                    // Get the number of registers
                    int numOfRegisters = Integer.parseInt(registerLine);
                    Register[] registerArray = new Register[numOfRegisters];

                    // Get the line for a register
                    for (int i = 0; i < registerArray.length; i++) {
                        String[] regInfo = (registerFile.getNextLine()).
                                split("\\s");
                        if (regInfo.length == 2) {
                            // Read in the registers
                            registerArray[i] = new Register(Double.
                                    parseDouble(regInfo[0]), 
                                    Double.parseDouble(regInfo[1]), i + 1);
                        }
                    }

                    registers[registerType++] = registerArray;

                }
                if (run == 0) {
                    registerLine = registerFile.getNextLine();
                    run++;
                } else {
                    done = true;
                }
            }

            while (customerArrivalsFile.hasNextLine()) {

                String[] custInfo = customerArrivalsFile.getNextLine().
                        split("\\s");

                if (custInfo.length == 3) {
                    custList.addNewCustomer(new Customer(Double.
                            parseDouble(custInfo[0]),
                            Integer.parseInt(custInfo[1]),
                            Double.parseDouble(custInfo[2])));
                }

            }

            int customerNumber = 1;

            // Create a new Customer
            while (!custList.isEmpty()) {
                Customer cust = custList.getNextCustomer();
                cust.setCustomerNumber(customerNumber++);

                if(cust.getArrivalTime() < 960){
                    eventList.addNewEvent(new Event(EventType.customerArrival, 
                            cust.getArrivalTime(), new Object[]{cust}));
                    eventList.addNewEvent(new Event(EventType.customerEndShopping, 
                            cust.getArrivalTime() + cust.getCheckoutTime(), 
                            new Object[]{cust}));
                }
                

            }
            while (!eventList.isEmpty()) {
                Customer cust;
                Event event = eventList.getNextEvent();
                clock.time(event.getEventTime());
                switch (event.getEventType()) {
                    case customerArrival:
//                        cust = (Customer) event.getEventParameter()[0];
//                        System.out.println(String.format("Customer %d arrived "
//                                + "at time %.2f to get %d items at %.2f "
//                                + "items/minute", cust.getCustomerNumber(),
//                                clock.time(), cust.getNumOfItems(),
//                                cust.getTimePerItem()));
                        break;
                    case customerEndShopping:
                        boolean express = false;
                        cust = (Customer) event.getEventParameter()[0];
                        Register register;
                        if (cust.getNumOfItems() < 12) {
                            express = true;
                        }
                        register = getShortestRegister(registers, express);
                        register.addCustomer(cust);
//                        System.out.println(String.format("Customer %d entered "
//                                + "lane %d with %d items at time %.2f",
//                                cust.getCustomerNumber(),
//                                register.getRegisterNumber(),
//                                cust.getNumOfItems(), clock.time()));
                        eventList.addNewEvent(new Event(
                                EventType.customerEndCheckout,
                                register.checkout(cust),
                                new Object[]{register}));
                        break;
                    case customerEndCheckout:
                        Register reg = (Register) event.getEventParameter()[0];
                        cust = reg.getNextCustomer();
//                        System.out.println(String.format("Customer %d left "
//                                + "store from register %d at time %.2f",
//                                cust.getCustomerNumber(),
//                                reg.getRegisterNumber(), clock.time()));
                        String custOutput = String.format(
                            "Customer %04d\r\n" +
                            "________________________\r\n" +
                            "Arrival Time: %5.2f\r\n" +
                            "End Shopping Time: %5.2f\r\n" +
                            "End Waiting Time: %5.2f\r\n" +
                            "End Checkout Time: %5.2f\r\n\r\n", 
                            cust.getCustomerNumber(), cust.getArrivalTime(), 
                            cust.getArrivalTime() + cust.getCheckoutTime(), 
                            cust.getArrivalTime() + cust.getCheckoutTime() + 
                                    cust.getWaitTime(),
                            clock.time());
                        log.write(custOutput);
                        break;
                    default:

                        break;
                }
            }

            int numOfCustomersServed = 0;
            StringBuilder sb = new StringBuilder(String.format("%10s%12s%12s%12s%16s%12s%12s%18s",
                    "Register", "Max Line", "Avg Line", "Down Time",
                    "Avg Down Time", "Total Wait", "Avg Wait",
                    "Total Customers\r\n"));
            System.out.println(String.format("%10s%12s%12s%12s%16s%12s%12s%18s",
                    "Register", "Max Line", "Avg Line", "Down Time",
                    "Avg Down Time", "Total Wait", "Avg Wait",
                    "Total Customers"));
            for (int i = 0; i < registers.length; i++) {
                for (Register r : registers[i]) {
                    if (i == REGULAR_REGISTERS) {
                        System.out.print("Regular ");
                        sb.append("Regular ");
                    } else if (i == EXPRESS_REGISTERS) {
                        System.out.print("Express ");
                        sb.append("Express ");
                    }
                    numOfCustomersServed += r.getTotalCustomers();
                    System.out.print(String.format("%02d%4s", 
                            r.getRegisterNumber(), ""));
                    sb.append(String.format("%02d%4s", 
                            r.getRegisterNumber(), ""));
                    System.out.print(String.format("%4d", 
                            r.getLongestLineLength()));
                    sb.append(String.format("%4d", 
                            r.getLongestLineLength()));
                    System.out.print(String.format("%14.2f", 
                            r.getAverageLength()));
                    sb.append(String.format("%14.2f", 
                            r.getAverageLength()));
                    System.out.print(String.format("%12.2f", 
                            r.getDownTime()));
                    sb.append(String.format("%12.2f", 
                            r.getDownTime()));
                    System.out.print(String.format("%13.2f", 
                            r.getDownTime() / r.getTimesIdle()));
                    sb.append(String.format("%13.2f", 
                            r.getDownTime() / r.getTimesIdle()));
                    System.out.print(String.format("%16.2f", 
                            r.getTotalWaitTime()));
                    sb.append(String.format("%16.2f", 
                            r.getTotalWaitTime()));
                    System.out.print(String.format("%11.2f", 
                            r.getTotalWaitTime() / r.getTotalCustomers()));
                    sb.append(String.format("%11.2f", 
                            r.getTotalWaitTime() / r.getTotalCustomers()));
                    System.out.println(String.format("%13d", 
                            r.getTotalCustomers()));
                    sb.append(String.format("%13d\n", 
                            r.getTotalCustomers()));
                }
            }
            System.out.println("The total number of customers served was " + 
                    numOfCustomersServed);
            sb.append("The total number of customers served was ")
                    .append(numOfCustomersServed)
                    .append("\n");
            
            log.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }finally{
            if(log != null){
                log.close();
            }
        }
    }

    public static Register getShortestRegister(Register[][] registers, 
            boolean express) {
        Register reg;
        if (!express || (registers[EXPRESS_REGISTERS].length <= 0)) {
            reg = registers[REGULAR_REGISTERS][0];
            for (Register register : registers[REGULAR_REGISTERS]) {
                if (register.getLineLength() < reg.getLineLength()) {
                    reg = register;
                }
            }
        } else {
            reg = registers[EXPRESS_REGISTERS][0];
            for (Register register : registers[EXPRESS_REGISTERS]) {
                if (register.getLineLength() < reg.getLineLength()) {
                    reg = register;
                }
            }
            for (Register register : registers[REGULAR_REGISTERS]) {
                if (register.getLineLength() < reg.getLineLength()) {
                    reg = register;
                }
            }
        }

        return reg;
    }

}
