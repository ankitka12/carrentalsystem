
import java.util.ArrayList;
import java.util.Scanner;
    class carr {
        private String carid;
        private String brand;
        private String model;
        private double baseprice;
        private boolean isavailable;
        public carr(String carid,String brand,String model,double baseprice){
            this.carid=carid;
            this.brand=brand;
            this.model=model;
            this.baseprice=baseprice;
        }
        public String getCarid(){
            return carid;
        }
        public String getBrand(){
            return brand;
        }
        public String getModel(){
            return model;
        }
        public double calculatePrice(int days){
            return baseprice*days;

        }
        public boolean isAvailable(){
            return isavailable;
        }
        public void rent(){
            isavailable=false;
        }
        public void returncar(){
            isavailable=true;
        }
    }
    class Customer{
        private String customerid;
        private String name;
        private long customerNumber;
        private long adharnum;
        public Customer(String customerid,String name){
            this.customerid=customerid;
            this.name=name;
        }
        public String getCustomerid(){
            return customerid;
        }
        public String getName(){
            return name;
        }
        public long getCustomerNumber(){
            return customerNumber;
        }
        public long getAdharnum(){
            return adharnum;
        }
    }
    class rental{
        private carr Car;
        private Customer customer;
        private int days;
        rental(carr Car,Customer customer,int days){
            this.Car=Car;
            this.customer=customer;
            this.days=days;
        }
        public carr getCar(){
            return Car;
        }
        public Customer getCustomer(){
            return customer;
        }
        public int getDays(){
            return days;
        }
    }
    class CarRantelSystem{
        private ArrayList<carr> cars;
        private  ArrayList<Customer> customers;
        private ArrayList<rental> rentals;
        public CarRantelSystem(){
            cars=new ArrayList<>();
            customers=new ArrayList<>();
            rentals=new ArrayList<>();
        }
        public   void addcar(carr Car){
            cars.add(Car);
        }
        public void addcustomer(Customer customer){
            customers.add(customer);
        }
        public void  rentcar(carr Car,Customer customer,int days ){
            if(!Car.isAvailable()){
                Car.rent();
                rentals.add(new rental(Car,customer,days));
            }
            else{
                System.out.println("not available");
            }
        }
        public  void returnCar(carr Car){
            Car.returncar();
            rental renttoremove=null;
            for(rental Rental:rentals){
                if(Rental.getCar()==Car){
                    renttoremove=Rental;
                    break;
                }
            }
            if(renttoremove!=null){
                rentals.remove(renttoremove);
                System.out.println("car returned");
            }
            else {
                System.out.println("not returned");
            }
        }
        public  void  manue(){
            Scanner sc=new Scanner(System.in);
            while (true) {
                System.out.println("===car rental system===");
                System.out.println("1. Rent a car");
                System.out.println("2. Return a car");
                System.out.println("3. Exit");
                System.out.println("enter your choice");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("enter your name");
                    String customname = sc.next();
                    System.out.println();
                    System.out.println("availabe cars ");
                    for (carr Car : cars) {
                        if (!Car.isAvailable()) {
                            System.out.println(Car.getCarid()+"  " + Car.getBrand()+"  " + Car.getModel());
                        }
                    }
                    System.out.println("enter carid");
                    String carid = sc.next();
                    System.out.println("number of days car needed");
                    int day = sc.nextInt();
                    sc.nextLine();
                    Customer newcustomer = new Customer("cus" + (customers.size() + 1), customname);
                    addcustomer(newcustomer);
                    carr selectedCar = null;

                    for (carr Car : cars) {

                        if (Car.getCarid().equals(carid) && !Car.isAvailable()) {
                            selectedCar = Car;
                            break;
                        }
                    }

                    if (selectedCar != null) {

                        double totalPrice = selectedCar.calculatePrice(day);

                        System.out.println("\n== Rental Information ==\n");

                        System.out.println("Customer ID:" + newcustomer.getCustomerid());
                        System.out.println("customer name:" + newcustomer.getName());

                        System.out.println("Car:" + selectedCar.getBrand() + "  " + selectedCar.getModel());

                        System.out.println("Rental Days: " + day);

                        System.out.printf("Total Price: RS%.2f%n", totalPrice);

                        System.out.print("\nConfirm rental (Y/N): ");

                        String confirm = sc.next();

                        if (confirm.equalsIgnoreCase("Y")) {
                            rentcar(selectedCar, newcustomer, day);
                            System.out.println("car rented sucessfully");
                        } else {
                            System.out.println("cancelled");
                        }
                    } else {
                        System.out.println("invellid selection");
                    }

                } else if (choice == 2) {
                    System.out.println("return");
                    System.out.println("enter the car id");
                    String carId = sc.next();
                    carr carTOreturn = null;
                    for (carr Car : cars) {
                        if (Car.getCarid().equals(carId) && !Car.isAvailable()) {
                            carTOreturn = Car;
                            break;
                        }
                    }
                    if (carTOreturn != null) {
                        Customer customer = null;
                        for (rental rental : rentals) {
                            if (rental.getCar() == carTOreturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnCar(carTOreturn);

                        } else {
                            System.out.println("car information missing");
                        }
                    }
                }
                else if(choice==3) {
                    break;
                }

                else {
                    System.out.println("invalid entry");
                }
                System.out.println("thankyou for using car rentel system");
            }
        }
    }

    public class car {
        public static void main(String args[]){
            CarRantelSystem RENTT=new CarRantelSystem();
            carr Car1=new carr("C001","toytaa","Camry",600);
            carr Car2=new carr("C002","ford","endavor",900);
            carr Car3=new carr("C003","toytaa","fortuner",1500);
            RENTT.addcar(Car1);
            RENTT.addcar(Car2);
            RENTT.addcar(Car3);
            RENTT.manue();
        }
    }



