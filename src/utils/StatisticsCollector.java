package utils;

public class StatisticsCollector {
    private int totalCustomersProcessed = 0;
    private int customersWhoWaited = 0;

    private int totalWaitingTime = 0;
    private int totalServiceTime = 0;
    private int totalInterarrivalTime = 0;
    private int totalTimeInSystem = 0;
    private int totalIdleServerMinutes = 0;

    private int totalSimulationMinutes = 0;
    private int numberOfServers;

    public StatisticsCollector(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public int getTotalCustomersProcessed() {
        return totalCustomersProcessed;
    }

    public void setTotalCustomersProcessed(int totalCustomersProcessed) {
        this.totalCustomersProcessed = totalCustomersProcessed;
    }

    public int getCustomersWhoWaited() {
        return customersWhoWaited;
    }

    public void setCustomersWhoWaited(int customersWhoWaited) {
        this.customersWhoWaited = customersWhoWaited;
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public int getTotalInterarrivalTime() {
        return totalInterarrivalTime;
    }

    public void setTotalInterarrivalTime(int totalInterarrivalTime) {
        this.totalInterarrivalTime = totalInterarrivalTime;
    }

    public int getTotalTimeInSystem() {
        return totalTimeInSystem;
    }

    public void setTotalTimeInSystem(int totalTimeInSystem) {
        this.totalTimeInSystem = totalTimeInSystem;
    }

    public int getTotalIdleServerMinutes() {
        return totalIdleServerMinutes;
    }

    public void setTotalIdleServerMinutes(int totalIdleServerMinutes) {
        this.totalIdleServerMinutes = totalIdleServerMinutes;
    }

    public int getTotalSimulationMinutes() {
        return totalSimulationMinutes;
    }

    public void setTotalSimulationMinutes(int totalSimulationMinutes) {
        this.totalSimulationMinutes = totalSimulationMinutes;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }
}
