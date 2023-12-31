package cn.tuyucheng.taketoday.javaxval.methodvalidation.model;

import cn.tuyucheng.taketoday.javaxval.methodvalidation.constraints.ConsistentDateParameters;
import cn.tuyucheng.taketoday.javaxval.methodvalidation.constraints.ValidReservation;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Validated
public class Reservation {

	private LocalDate begin;

	private LocalDate end;

	@Valid
	private Customer customer;

	@Positive
	private int room;

	@ConsistentDateParameters
	@ValidReservation
	public Reservation(LocalDate begin, LocalDate end, Customer customer, int room) {
		this.begin = begin;
		this.end = end;
		this.customer = customer;
		this.room = room;
	}

	public LocalDate getBegin() {
		return begin;
	}

	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
}
