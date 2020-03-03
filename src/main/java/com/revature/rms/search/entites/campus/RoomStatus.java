package com.revature.rms.search.entites.campus;

import java.util.Objects;

public class RoomStatus {

  private int id;
  private boolean whiteboardCleaned;
  private boolean desksCleaned;
  private String submittedDateTime;
  private int submitterId;

  public RoomStatus() {}

  public RoomStatus(
      int id,
      boolean whiteboardCleaned,
      boolean desksCleaned,
      String submittedDateTime,
      int submitterId) {
    this.id = id;
    this.whiteboardCleaned = whiteboardCleaned;
    this.desksCleaned = desksCleaned;
    this.submittedDateTime = submittedDateTime;
    this.submitterId = submitterId;
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

  public int getSubmitterId() {
    return submitterId;
  }

  public void setSubmitterId(int submitterId) {
    this.submitterId = submitterId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoomStatus that = (RoomStatus) o;
    return id == that.id
        && whiteboardCleaned == that.whiteboardCleaned
        && desksCleaned == that.desksCleaned
        && submitterId == that.submitterId
        && Objects.equals(submittedDateTime, that.submittedDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, whiteboardCleaned, desksCleaned, submittedDateTime, submitterId);
  }

  @Override
  public String toString() {
    return "RoomStatus{"
        + "id="
        + id
        + ", whiteboardCleaned="
        + whiteboardCleaned
        + ", desksCleaned="
        + desksCleaned
        + ", submittedDateTime='"
        + submittedDateTime
        + '\''
        + ", submitterId="
        + submitterId
        + '}';
  }
}
