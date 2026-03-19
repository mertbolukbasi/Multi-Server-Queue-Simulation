package model;

public class Server {
    private int server_id;
    private boolean isAvailable;
    private Customer currentCustomer;

    public Server(int server_id) {
        this.server_id = server_id;
        this.isAvailable = true;
        this.currentCustomer = null;
    }

    public void assignCustomerToServer(Customer customer) {
        this.isAvailable = false;
        this.currentCustomer = customer;
    }

    public void releaseServer() {
        this.isAvailable = true;
        this.currentCustomer = null;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}
