package edu.mum.cs.inventorymanager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_salesperson")
public class Salesperson {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long salesmanId;
	//@NotEmpty(message = "*staffId is required")
	private String staffId;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	@NotNull
	private User user;
	@OneToOne
	@JoinColumn(name = "locationId")
	private Location location;
	private String status="Active";
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "merchantId")
	private Merchant merchant;

	public Salesperson() {
	}

	public Salesperson(String staffId, @NotNull User user, Location location) {
		this.staffId = staffId;
		this.user = user;
		this.location = location;
	}

	public Salesperson(String staffId, @NotNull User user, Location location,String status) {
		this.staffId = staffId;
		this.user = user;
		this.location = location;
		this.status = status;
	}

	public long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(long salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return "Salesperson{" +
				"salesmanId=" + salesmanId +
				", staffId='" + staffId + '\'' +
				", user=" + user +
				", location=" + location +
				", status='" + status + '\'' +
				", merchant=" + merchant +
				'}';
	}
}
