package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationLogger {
    private BufferedWriter writer;
    private final String filePath = "simulation_logs.log";

    public SimulationLogger() {
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            System.err.println("Log file error: " + e.getMessage());
        }
    }

    private void logHeader() throws IOException {
        System.out.println();
        String header = "Simulation Logs";
        System.out.println(header);
        writer.write(header);
        writer.newLine();
    }

    public void logEvent(int currentClock, String eventMessage) {
        System.out.println("Clock: " + currentClock);
        System.out.println("Log: " + eventMessage);
        System.out.println();
        String logMessage = "Clock: " + currentClock + " " + "Log: " + eventMessage;

        try {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Log writing error: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("File close error: " + e.getMessage());
        }
    }
}
