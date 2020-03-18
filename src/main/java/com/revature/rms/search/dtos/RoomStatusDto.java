package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.Employee;

import java.util.Objects;

public class RoomStatusDto {

  private String id;
  private boolean whiteboardCleaned;
  private boolean chairsOrdered;
  private String submittedDateTime;
  private EmployeeDto submitter;
  private String otherNotes;
  private boolean archived;

  public RoomStatusDto() {
    super();
  }

  public RoomStatusDto(
      String id,
      boolean whiteboardCleaned,
      boolean chairsOrdered,
      String submittedDateTime,
      String otherNotes,
      boolean archived) {
    this.id = id;
    this.whiteboardCleaned = whiteboardCleaned;
    this.chairsOrdered = chairsOrdered;
    this.submittedDateTime = submittedDateTime;
    this.otherNotes = otherNotes;
    this.archived = archived;
  }

  public RoomStatusDto(String id, boolean whiteboardCleaned, boolean chairsOrdered, String submittedDateTime, EmployeeDto submitter, String otherNotes, boolean archived) {
    this.id = id;
    this.whiteboardCleaned = whiteboardCleaned;
    this.chairsOrdered = chairsOrdered;
    this.submittedDateTime = submittedDateTime;
    this.submitter = submitter;
    this.otherNotes = otherNotes;
    this.archived = archived;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoomStatusDto that = (RoomStatusDto) o;
    return whiteboardCleaned == that.whiteboardCleaned &&
            chairsOrdered == that.chairsOrdered &&
            archived == that.archived &&
            Objects.equals(id, that.id) &&
            Objects.equals(submittedDateTime, that.submittedDateTime) &&
            Objects.equals(submitter, that.submitter) &&
            Objects.equals(otherNotes, that.otherNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, whiteboardCleaned, chairsOrdered, submittedDateTime, submitter, otherNotes, archived);
  }

  @Override
  public String toString() {
    return "RoomStatusDto{" +
            "id='" + id + '\'' +
            ", whiteboardCleaned=" + whiteboardCleaned +
            ", chairsOrdered=" + chairsOrdered +
            ", submittedDateTime='" + submittedDateTime + '\'' +
            ", submitter=" + submitter +
            ", otherNotes='" + otherNotes + '\'' +
            ", archived=" + archived +
            '}';
  }
}
