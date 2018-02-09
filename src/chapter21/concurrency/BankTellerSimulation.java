/**   
 * @{#} BankTellerSimulation.java by Hongji.Xu 2018年2月8日 上午10:33:50 
 * @Title:  BankTellerSimulation.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Read-only objects dont't require synchronization
class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

// Teach the customer line to display itself
class CustomerLine extends ArrayBlockingQueue<Customer> {
    private static final long serialVersionUID = -8675451647367612580L;

    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}

// Randomly add customers to a queue
class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }

}

class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;

    // customer served during this shift
    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
        customers = cq;
    }

    @Override
    public synchronized int compareTo(Teller other) {
        return customerServed < other.customerServed ? -1 : (customerServed == other.customerServed ? 0 : 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customerServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " terminating");
    }

    public synchronized void doSomethingElse() {
        customerServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serverCustomerLine() {
        assert !servingCustomerLine : "already serving:" + this;
        servingCustomerLine = true;
        notifyAll();
    }
    
    @Override
    public String toString() {
        return "Teller "+id+" ";
    }
    
    public String shortString() {
        return "T"+id;
    }
}

class TellerManager implements Runnable{
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTeller=new PriorityQueue<Teller>();
    private Queue<Teller> tellerDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;
    
    public TellerManager(ExecutorService exec,CustomerLine customers,int adjustmentPeriod) {
        this.exec=exec;
        this.customers=customers;
        this.adjustmentPeriod=adjustmentPeriod;
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTeller.add(teller);
    }
    
    public void adjustTellerNumber() {
        //this is actually a control system.by adjueting
        if(customers.size()/workingTeller.size() > 2 ) {
            if(tellerDoingOtherThings.size()>0) {
                Teller teller = tellerDoingOtherThings.remove();
                teller.serverCustomerLine();
                workingTeller.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTeller.add(teller);
            return;
        }
        
        if(workingTeller.size()>1&&customers.size()/workingTeller.size()<2) {
            reassignOneTeller();
        }
        
        if(customers.size()==0) {
            while(workingTeller.size()>1) {
                reassignOneTeller();
            }
        }
    }

    private void reassignOneTeller() {
        Teller teller = workingTeller.poll();
        teller.doSomethingElse();
        tellerDoingOtherThings.offer(teller);
    }
    
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers+" { ");
                for(Teller teller:workingTeller) {
                    System.out.print(teller.shortString()+" ");
                }
                System.out.println("}");
            }
        }catch (InterruptedException e) {
            System.out.println(this+" interrupted");
        }
        System.out.println(this+" terminating");
    }
    
    @Override
    public String toString() {
        return "TellerManager ";
    }
    
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        //if line is too long,customers will leave
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        //manager will add and remove tellers as necessary
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
        
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
