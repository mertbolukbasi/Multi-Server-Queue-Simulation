package algorithm;

import model.Event;
import model.Server;
import utils.DistributionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
    private void initializeFutureEventList() {
        int clock = 0;
    }

    /*
        Sonsuz döngü kurulacak. Clock değişkeni ile anlık zamanı tutulacak.
        Her döngü 1 clock tick'e eşit. (1 dakika gibi düşünebiliriz.)
        Her customer geldiğinde calculateCustomerServiceTime() ile service time hesaplanacak.
        Her döngüde projedeki 3. maddedeki a b c maddeleri kontrol edilip ona göre işlem yapılacak. (Bunlar için ayrı fonksiyon tanımlayabilirsiniz.)
     */
    public void runSimulation() {

    }

    // Sunucular müsait mi mu değil mi diye kontrol edecek. Bir tane bile boş varsa true olacak.
    private boolean hasAvailableServer() {
        return false;
    }

    // Müsait olan sunucuyu almak için kullanıcaz. ArrayListte 1. sunucu ilk indexte olduğu için ikisi de boş olsa bile her zaman 1. öncelikli olsun.
    private Server getAvailableServer() {
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
