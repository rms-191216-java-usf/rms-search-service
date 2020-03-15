package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.Employee;

import java.util.Objects;

public class RoomStatusDto {


  private int id;
  private boolean whiteboardCleaned;
  private boolean chairsOrdered;
  private boolean desksCleaned;
  private String submittedDateTime;
  private EmployeeDto submitter;
  private String otherNotes;

  public RoomStatusDto() {
    super();
  }

  public RoomStatusDto(
      int id,
      boolean whiteboardCleaned,
      boolean chairsOrdered,
      boolean desksCleaned,
      String submittedDateTime,
      String otherNotes) {
    this.id = id;
    this.whiteboardCleaned = whiteboardCleaned;
    this.chairsOrdered = chairsOrdered;
    this.desksCleaned = desksCleaned;
    this.submittedDateTime = submittedDateTime;
    this.otherNotes = otherNotes;
  }

  public RoomStatusDto(
      int id,
      boolean whiteboardCleaned,
      boolean chairsOrdered,
      boolean desksCleaned,
      String submittedDateTime,
      EmployeeDto submitter,
      String otherNotes) {
    this.id = id;
    this.whiteboardCleaned = whiteboardCleaned;
    this.chairsOrdered = chairsOrdered;
    this.desksCleaned = desksCleaned;
    this.submittedDateTime = submittedDateTime;
    this.submitter = submitter;
    this.otherNotes = otherNotes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isWhiteboardCleaned() {
    return whiteboardCleaned;
  }

  public void setWhiteboardCleaned(boolean whiteboardCleaned) {
    this.whiteboardCleaned = whiteboardCleaned;
  }

  public boolean isChairsOrdered() {
    return chairsOrdered;
  }

  public void setChairsOrdered(boolean chairsOrdered) {
    this.chairsOrdered = chairsOrdered;
  }

  public boolean isDesksCleaned() {
    return desksCleaned;
  }

  public void setDesksCleaned(boolean desksCleaned) {
    this.desksCleaned = desksCleaned;
  }

  public String getSubmittedDateTime() {
    return submittedDateTime;
  }

  public void setSubmittedDateTime(String submittedDateTime) {
    this.submittedDateTime = submittedDateTime;
  }

  public EmployeeDto getSubmitter() {
    return submitter;
  }

  public void setSubmitter(EmployeeDto submitter) {
    this.submitter = submitter;
  }

  public String getOtherNotes() {
    return otherNotes;
  }

  public void setOtherNotes(String otherNotes) {
    this.otherNotes = otherNotes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoomStatusDto that = (RoomStatusDto) o;
    return id == that.id
        && whiteboardCleaned == that.whiteboardCleaned
        && chairsOrdered == that.chairsOrdered
        && desksCleaned == that.desksCleaned
        && Objects.equals(submittedDateTime, that.submittedDateTime)
        && Objects.equals(submitter, that.submitter)
        && Objects.equals(otherNotes, that.otherNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        whiteboardCleaned,
        chairsOrdered,
        desksCleaned,
        submittedDateTime,
        submitter,
        otherNotes);
  }

  @Override
  public String toString() {
    return "RoomStatusDto{"
        + "id="
        + id
        + ", whiteboardCleaned="
        + whiteboardCleaned
        + ", chairsOrdered="
        + chairsOrdered
        + ", desksCleaned="
        + desksCleaned
        + ", submittedDateTime='"
        + submittedDateTime
        + '\''
        + ", submitter="
        + submitter
        + ", otherNotes='"
        + otherNotes
        + '\''
        + '}';
  }
>>>>>>> 529987ae91c18fb6bae771019b1e12ef8da22392
}
