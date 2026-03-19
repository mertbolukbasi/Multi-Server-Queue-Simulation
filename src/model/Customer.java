package model;

public class Customer {
    private int customer_id;
    private int arrivalTime;
    private int serviceStartTime;
    private int serviceTime;
    private int serviceEndTime;

    public Customer(int customer_id, int arrivalTime) {
        this.customer_id = customer_id;
        this.arrivalTime = arrivalTime;
    }

    public int getWaitingTime() {
        return serviceStartTime - arrivalTime;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(int serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(int serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }
}
