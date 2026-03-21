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

    public void incrementTotalCustomersProcessed() {
        totalCustomersProcessed++;
    }

    public void incrementCustomersWhoWaited() {
        customersWhoWaited++;
    }

    public void addWaitingTime(int waitingTime) {
        totalWaitingTime += waitingTime;
    }

    public void addServiceTime(int serviceTime) {
        totalServiceTime += serviceTime;
    }

    public void addInterarrivalTime(int interarrivalTime) {
        totalInterarrivalTime += interarrivalTime;
    }

    public void addTimeInSystem(int timeInSystem) {
        totalTimeInSystem += timeInSystem;
    }

    public void addIdleServerMinutes(int idleServerMinutes) {
        totalIdleServerMinutes += idleServerMinutes;
    }

    public void incrementSimulationMinute() {
        totalSimulationMinutes++;
    }

    public double getAverageWaitingTime() {
        if (totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) totalWaitingTime / totalCustomersProcessed;
    }

    public double getAverageServiceTime() {
        if (totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) totalServiceTime / totalCustomersProcessed;
    }

    public double getAverageInterarrivalTime() {
        if (totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) totalInterarrivalTime / totalCustomersProcessed;
    }

    public double getAverageTimeInSystem() {
        if (totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) totalTimeInSystem / totalCustomersProcessed;
    }

    public double getWaitingProbability() {
        if (totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) customersWhoWaited / totalCustomersProcessed;
    }

    public double getIdleServerProbability() {
        if (numberOfServers == 0 || totalSimulationMinutes == 0) {
            return 0.0;
        }
        return (double) totalIdleServerMinutes / (numberOfServers * totalSimulationMinutes);
    }

    public double getAverageWaitingTimeOfThoseWhoWait() {
        if (customersWhoWaited == 0) {
            return 0.0;
        }
        return (double) totalWaitingTime / customersWhoWaited;
    }

    public double getServerUtilization() {
        if (numberOfServers == 0 || totalSimulationMinutes == 0) {
            return 0.0;
        }
        return (double) totalServiceTime / (numberOfServers * totalSimulationMinutes);
    }

    public double getAverageIdleTimePerServer() {
        if (numberOfServers == 0) {
            return 0.0;
        }
        return (double) totalIdleServerMinutes / numberOfServers;
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
