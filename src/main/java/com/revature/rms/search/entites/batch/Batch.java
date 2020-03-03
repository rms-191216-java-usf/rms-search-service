package com.revature.rms.search.entites.batch;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Batch {

  private int id;

  private String name;

  private LocalDate startDate;

  private LocalDate endDate;

  private int trainerId;

  private int coTrainerId;

  private List<Integer> associates;

  private Curriculum curriculum;

  private ResourceMetadata resourceMetadata;

  public Batch() {}

  public Batch(
      int id,
      String name,
      LocalDate startDate,
      LocalDate endDate,
      int trainerId,
      int coTrainerId,
      List<Integer> associates,
      Curriculum curriculum,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.trainerId = trainerId;
    this.coTrainerId = coTrainerId;
    this.associates = associates;
    this.curriculum = curriculum;
    this.resourceMetadata = resourceMetadata;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getTrainerId() {
    return trainerId;
  }

  public void setTrainerId(int trainerId) {
    this.trainerId = trainerId;
  }

  public int getCoTrainerId() {
    return coTrainerId;
  }

  public void setCoTrainerId(int coTrainerId) {
    this.coTrainerId = coTrainerId;
  }

  public List<Integer> getAssociates() {
    return associates;
  }

  public void setAssociates(List<Integer> associates) {
    this.associates = associates;
  }

  public Curriculum getCurriculum() {
    return curriculum;
  }

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadata resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Batch batch = (Batch) o;
    return id == batch.id
        && trainerId == batch.trainerId
        && coTrainerId == batch.coTrainerId
        && Objects.equals(name, batch.name)
        && Objects.equals(startDate, batch.startDate)
        && Objects.equals(endDate, batch.endDate)
        && Objects.equals(associates, batch.associates)
        && curriculum == batch.curriculum
        && Objects.equals(resourceMetadata, batch.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        startDate,
        endDate,
        trainerId,
        coTrainerId,
        associates,
        curriculum,
        resourceMetadata);
  }

  @Override
  public String toString() {
    return "Batch{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + ", trainerId="
        + trainerId
        + ", coTrainerId="
        + coTrainerId
        + ", associates="
        + associates
        + ", curriculum="
        + curriculum
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
