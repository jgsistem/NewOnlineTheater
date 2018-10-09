package theater.Customer.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import theater.Customer.application.deserializer.CustomerCreateDeserializer;
import java.util.Date;

@JsonDeserialize(using = CustomerCreateDeserializer.class)
public class CustomerCreateDto {
	
	private long Id;
	private String  Name;
	private String Email;
	private int Status;
	private Date StatusExpirationDate;
	private Double MoneySpent;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public Date getStatusExpirationDate() {
		return StatusExpirationDate;
	}
	public void setStatusExpirationDate(Date statusExpirationDate) {
		StatusExpirationDate = statusExpirationDate;
	}
	public Double getMoneySpent() {
		return MoneySpent;
	}
	public void setMoneySpent(Double moneySpent) {
		MoneySpent = moneySpent;
	}
	
	

}
