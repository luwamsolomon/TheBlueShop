package edu.mum.cs.inventorymanager.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_terminal")
public class Terminal{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long terminalId;
    private String terminalName;
    private String stationStatus="Active";
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
//    @OneToMany(mappedBy = "terminal", cascade = CascadeType.PERSIST)
//    private List<Order> orders;

    public Terminal() {
    }

    public Terminal(String terminalName, String stationStatus, Location location, List<Order> orders) {
        this.terminalName = terminalName;
        this.stationStatus = stationStatus;
        this.location = location;
//        this.orders = orders;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
}
