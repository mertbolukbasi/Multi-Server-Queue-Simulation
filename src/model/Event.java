package model;

public class Event implements Comparable<Event>{
    private int eventTime;
    private EventType evenType;
    private Customer customer;
    private Server server;

    public Event(int eventTime, EventType evenType, Customer customer, Server server) {
        this.eventTime = eventTime;
        this.evenType = evenType;
        this.customer = customer;
        this.server = server;
    }

    @Override
    public int compareTo(Event event) {
        return Integer.compare(this.eventTime, event.eventTime);
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public EventType getEvenType() {
        return evenType;
    }

    public void setEvenType(EventType evenType) {
        this.evenType = evenType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
