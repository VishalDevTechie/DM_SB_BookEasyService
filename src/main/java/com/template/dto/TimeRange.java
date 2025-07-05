package com.template.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeRange {

	@JsonProperty("startTime")
	private LocalTime start;

	@JsonProperty("endTime")
	private LocalTime end;

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

}
