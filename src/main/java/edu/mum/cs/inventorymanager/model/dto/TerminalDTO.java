package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Terminal;

import java.util.List;
import java.util.stream.Collectors;

public class TerminalDTO {

    private long terminalId;
    private String terminalName;
    private String stationStatus = "Active";
    private LocationDTO location;
    private List<OrderDTO> orders;

    public TerminalDTO(Terminal terminal) {
        terminalId = terminal.getTerminalId();
        terminalName = terminal.getTerminalName();
        stationStatus = terminal.getStationStatus();
        location = new LocationDTO(terminal.getLocation());
//        orders = terminal.getOrders().stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());
    }

    public long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(long terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
