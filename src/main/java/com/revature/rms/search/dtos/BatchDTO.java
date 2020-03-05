package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.batch.Curriculum;
import com.revature.rms.search.entites.batch.ResourceMetadata;
import com.revature.rms.search.entites.employee.Employee;

import java.util.List;
import java.util.Objects;

public class BatchDTO {

  private int id;
  private String name;
  private Employee trainer;
  private Employee coTrainer;
  private List<Employee> associates;
  private String startDate;
  private String endDate;
  private Curriculum curriculum;
  private ResourceMetadata resourceMetadata;

  public BatchDTO() {}

  public BatchDTO(
      int id,
      String name,
      Employee trainer,
      Employee coTrainer,
      List<Employee> associates,
      String startDate,
      String endDate,
      Curriculum curriculum,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.trainer = trainer;
    this.coTrainer = coTrainer;
    this.associates = associates;
    this.startDate = startDate;
    this.endDate = endDate;
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

  public Employee getTrainer() {
    return trainer;
  }

  public void setTrainer(Employee trainer) {
    this.trainer = trainer;
  }

  public Employee getCoTrainer() {
    return coTrainer;
  }

  public void setCoTrainer(Employee coTrainer) {
    this.coTrainer = coTrainer;
  }

  public List<Employee> getAssociates() {
    return associates;
  }

  public void setAssociates(List<Employee> associates) {
    this.associates = associates;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
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
    BatchDTO batchDTO = (BatchDTO) o;
    return id == batchDTO.id
        && Objects.equals(name, batchDTO.name)
        && Objects.equals(trainer, batchDTO.trainer)
        && Objects.equals(coTrainer, batchDTO.coTrainer)
        && Objects.equals(associates, batchDTO.associates)
        && Objects.equals(startDate, batchDTO.startDate)
        && Objects.equals(endDate, batchDTO.endDate)
        && curriculum == batchDTO.curriculum
        && Objects.equals(resourceMetadata, batchDTO.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, name, trainer, coTrainer, associates, startDate, endDate, curriculum, resourceMetadata);
  }

  @Override
  public String toString() {
    return "BatchDTO{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", trainer="
        + trainer
        + ", coTrainer="
        + coTrainer
        + ", associates="
        + associates
        + ", startDate='"
        + startDate
        + '\''
        + ", endDate='"
        + endDate
        + '\''
        + ", curriculum="
        + curriculum
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
