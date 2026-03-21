package algorithm;

import model.Customer;
import model.Event;
import model.EventType;
import model.Server;
import utils.DistributionTable;
import utils.SimulationLogger;
import utils.StatisticsCollector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulation {
    private PriorityQueue<Event> futureEventList;
    private DistributionTable distributionTable;
    private List<Server> serverActivityList;
    private int totalCustomers;
    private int totalServers;

    public Simulation(int totalCustomers, int totalServers) {
        this.totalCustomers = totalCustomers;
        this.futureEventList = new PriorityQueue<>();
        this.distributionTable = new DistributionTable();
        this.totalServers = totalServers;

        this.serverActivityList = new ArrayList<>();
        for(int i = 0; i < this.totalServers; i++) serverActivityList.add(new Server(i+1));

        initializeFutureEventList();
    }

    /*
        Simulasyonun başlangıcı burada yapılacak. Clock değişkeni simülasyonun anlık zamanını temsil ediyor.
        Toplam müşteri sayısına (this.totalCustomers) göre for loop yapacağız.
        Her iterasyonda Customer nesnesi oluşturulacak ve DistributionTable içindeki interarrival fonksiyonu ile interarrival time oluşturulacak.
        Buna göre her müşterinin arrival time bulunup Customer sınıfı içindeki constructor aracılığıyla Customer nesnesi oluşturacağız.
        Ardından Event nesnesi oluşturup (constructor bakabilirsiniz) futureEventList PriorityQueue değişkenine ekleyeceğiz.

        Not: Sıralama yapmanıza gerek yok. Event sınıfındaki Comparable interface'i bunu kendi yapıyor. PriorityQueue içine eklemeniz yeterli.
        Not: Service time hesaplamıyoruz burada. Interarrival time ve arrival time hesaplayıp customer nesnesi oluşturuyoruz.
     */
    private void initializeFutureEventList() 
    {
        int clock = 0;

        DistributionTable obj = new DistributionTable();

        for(int i = 1 ; i <= getTotalCustomers(); i++)
        {
            
            int intArrival = obj.getInterarrivalTime();
            clock += intArrival;

            Customer customer = new Customer(i, intArrival);
            Event obj2 = new Event(clock, EventType.ARRIVAL, customer, getAvailableServer());

            futureEventList.add(obj2);

        }
        
    }

    /*
        Sonsuz döngü kurulacak. Clock değişkeni ile anlık zamanı tutulacak.
        Her döngü 1 clock tick'e eşit. (1 dakika gibi düşünebiliriz.)
        Her customer geldiğinde calculateCustomerServiceTime() ile service time hesaplanacak.
        Her döngüde projedeki 3. maddedeki a b c maddeleri kontrol edilip ona göre işlem yapılacak. (Bunlar için ayrı fonksiyon tanımlayabilirsiniz.)
     */
    public void runSimulation() {

        int clock = 0;
        Queue<Customer> waitingQueue = new LinkedList<>();
        StatisticsCollector stats = new StatisticsCollector(totalServers);
        SimulationLogger logger = new SimulationLogger();

        while (!futureEventList.isEmpty()) {

            Event event = futureEventList.poll();
            clock = event.getEventTime();
            Customer customer = event.getCustomer();

            // customer arrival
            if (event.getEvenType() == EventType.ARRIVAL) {

                stats.setTotalInterarrivalTime(stats.getTotalInterarrivalTime() + customer.getArrivalTime());

                // there is free server, serve
                if (hasAvailableServer()) {

                    Server availableServer = getAvailableServer();
                    int serviceTime = calculateCustomerServiceTime(availableServer.getServer_id());
                    customer.setServiceStartTime(clock);
                    customer.setServiceTime(serviceTime);
                    customer.setServiceEndTime(clock + serviceTime);
                    availableServer.assignCustomerToServer(customer);
                    stats.setTotalServiceTime(stats.getTotalServiceTime() + serviceTime);
                    futureEventList.add(new Event(clock + serviceTime, EventType.LEAVE, customer, availableServer));
                    logger.logEvent(clock, "Customer " + customer.getCustomer_id() + " arrived -> assigned to Server " + availableServer.getServer_id() + " (service time: " + serviceTime + ")");

                }
                // All servers busy, customer go waiting queue
                else {

                    waitingQueue.add(customer);
                    stats.setCustomersWhoWaited(stats.getCustomersWhoWaited() + 1);
                    logger.logEvent(clock, "Customer " + customer.getCustomer_id() + " arrived -> all servers busy, added to queue" + " (queue size: " + waitingQueue.size() + ")");
                }


            }
            // server become free
            else if (event.getEvenType() == EventType.LEAVE) {

                Server server = event.getServer();
                int waitingTime = customer.getWaitingTime();
                int timeInSystem = customer.getServiceEndTime() - customer.getServiceStartTime() + waitingTime;
                stats.setTotalCustomersProcessed(stats.getTotalCustomersProcessed() + 1);
                stats.setTotalWaitingTime(stats.getTotalWaitingTime() + waitingTime);
                stats.setTotalTimeInSystem(stats.getTotalTimeInSystem() + timeInSystem);
                logger.logEvent(clock, "Customer " + customer.getCustomer_id() + " left Server " + server.getServer_id() + " (waited: " + waitingTime + ", total in system: " + timeInSystem + ")");
                server.releaseServer();

                // move waiting cust to free sevrer
                if (!waitingQueue.isEmpty()) {

                    Customer nextCustomer = waitingQueue.poll();
                    int serviceTime = calculateCustomerServiceTime(server.getServer_id());
                    nextCustomer.setServiceStartTime(clock);
                    nextCustomer.setServiceTime(serviceTime);
                    nextCustomer.setServiceEndTime(clock + serviceTime);
                    server.assignCustomerToServer(nextCustomer);
                    stats.setTotalServiceTime(stats.getTotalServiceTime() + serviceTime);
                    futureEventList.add(new Event(clock + serviceTime, EventType.LEAVE, nextCustomer, server));
                    logger.logEvent(clock, "Customer " + nextCustomer.getCustomer_id() + " dequeued then assigned to Server " + server.getServer_id() + " (service time: " + serviceTime + ")");
                }
            }
        }

        stats.setTotalSimulationMinutes(clock);
        printStatistics(stats);

    }

    private void printStatistics(StatisticsCollector stats) {

        int processed = stats.getTotalCustomersProcessed();
        System.out.println("\n--- Simulation Statistics ---");
        System.out.println("Total customers processed: " + processed);
        System.out.println("Customers who waited: " + stats.getCustomersWhoWaited());
        System.out.println("Total waiting time: " + stats.getTotalWaitingTime());
        System.out.println("Total service time: " + stats.getTotalServiceTime());
        System.out.println("Total time in system: " + stats.getTotalTimeInSystem());
        System.out.println("Total simulation minutes: " + stats.getTotalSimulationMinutes());

        if (processed > 0) {

            System.out.println("Average waiting time: " + (double) stats.getTotalWaitingTime() / processed);
            System.out.println("Average service time: " + (double) stats.getTotalServiceTime() / processed);
            System.out.println("Average time in system: " + (double) stats.getTotalTimeInSystem() / processed);
        }
        System.out.println("---------------------------------");
    }

    // Sunucular müsait mi mu değil mi diye kontrol edecek. Bir tane bile boş varsa true olacak.
    private boolean hasAvailableServer() {
        for (Server server : serverActivityList) {
            if (server.isAvailable())
                return true;
        }
        return false;
    }

    // Müsait olan sunucuyu almak için kullanıcaz. ArrayListte 1. sunucu ilk indexte olduğu için ikisi de boş olsa bile her zaman 1. öncelikli olsun.
    private Server getAvailableServer() {
        for (Server server : serverActivityList) {
            if (server.isAvailable())
                return server;
        }
        return null;
    }

    private int calculateCustomerServiceTime(int server_id) {
        if(server_id == 1) return distributionTable.getServiceTimeForService1();
        else return distributionTable.getServiceTimeForService2();
    }

    /*
        Getters and Setters
     */
    public PriorityQueue<Event> getFutureEventList() {
        return futureEventList;
    }

    public void setFutureEventList(PriorityQueue<Event> futureEventList) {
        this.futureEventList = futureEventList;
    }

    public DistributionTable getDistributionTable() {
        return distributionTable;
    }

    public void setDistributionTable(DistributionTable distributionTable) {
        this.distributionTable = distributionTable;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public List<Server> getServerActivityList() {
        return serverActivityList;
    }

    public void setServerActivityList(List<Server> serverActivityList) {
        this.serverActivityList = serverActivityList;
    }
}
