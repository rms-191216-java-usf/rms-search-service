package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.RoomStatusDto;

import java.util.Objects;

public class RoomStatus {

  private String id;
  private boolean chairsOrdered;
  private boolean whiteboardCleaned;
  private String submittedDateTime;
  private int submitterId;
  private String otherNotes;
  private boolean archived;

  public RoomStatus() {}

  public RoomStatus(
      String id,
      boolean chairsOrdered,
      boolean whiteboardCleaned,
      String submittedDateTime,
      int submitterId,
      String otherNotes,
      boolean archived) {
    this.id = id;
    this.chairsOrdered = chairsOrdered;
    this.whiteboardCleaned = whiteboardCleaned;
    this.submittedDateTime = submittedDateTime;
    this.submitterId = submitterId;
    this.otherNotes = otherNotes;
    this.archived = archived;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isChairsOrdered() {
    return chairsOrdered;
  }

  public void setChairsOrdered(boolean chairsOrdered) {
    this.chairsOrdered = chairsOrdered;
  }

  public boolean isWhiteboardCleaned() {
    return whiteboardCleaned;
  }

  public void setWhiteboardCleaned(boolean whiteboardCleaned) {
    this.whiteboardCleaned = whiteboardCleaned;
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

  public RoomStatusDto extractRoomStatus() {
    return new RoomStatusDto(
        this.id,
        this.whiteboardCleaned,
        this.chairsOrdered,
        this.submittedDateTime,
        this.otherNotes,
        this.archived);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoomStatus that = (RoomStatus) o;
    return chairsOrdered == that.chairsOrdered
        && whiteboardCleaned == that.whiteboardCleaned
        && submitterId == that.submitterId
        && archived == that.archived
        && Objects.equals(id, that.id)
        && Objects.equals(submittedDateTime, that.submittedDateTime)
        && Objects.equals(otherNotes, that.otherNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, chairsOrdered, whiteboardCleaned, submittedDateTime, submitterId, otherNotes, archived);
  }

  @Override
  public String toString() {
    return "RoomStatus{"
        + "id='"
        + id
        + '\''
        + ", chairsOrdered="
        + chairsOrdered
        + ", whiteboardCleaned="
        + whiteboardCleaned
        + ", submittedDateTime='"
        + submittedDateTime
        + '\''
        + ", submitterId="
        + submitterId
        + ", otherNotes='"
        + otherNotes
        + '\''
        + ", archived="
        + archived
        + '}';
  }
}
