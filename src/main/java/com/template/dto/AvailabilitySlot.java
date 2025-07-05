package com.template.dto;

import java.time.LocalDate;
import java.util.List;

public class AvailabilitySlot {
	private LocalDate date;
	private List<TimeRange> timeRanges;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<TimeRange> getTimeRanges() {
		return timeRanges;
	}

	public void setTimeRanges(List<TimeRange> timeRanges) {
		this.timeRanges = timeRanges;
	}

}
