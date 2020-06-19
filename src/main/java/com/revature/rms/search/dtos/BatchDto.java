package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.batch.Curriculum;

import java.util.List;
import java.util.Objects;

public class BatchDto {

  private int id;
  private String name;
  private EmployeeDto trainer;
  private EmployeeDto coTrainer;
  private List<EmployeeDto> associates;
  private String startDate;
  private String endDate;
  private Curriculum curriculum;
  private ResourceMetadataDto resourceMetadata;

  public BatchDto() {}

  public BatchDto(int id, String name, String startDate, String endDate, Curriculum curriculum) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.curriculum = curriculum;
  }

  public BatchDto(
      int id,
      String name,
      EmployeeDto trainer,
      EmployeeDto coTrainer,
      List<EmployeeDto> associates,
      String startDate,
      String endDate,
      Curriculum curriculum,
      ResourceMetadataDto resourceMetadata) {
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

  public EmployeeDto getTrainer() {
    return trainer;
  }

  public void setTrainer(EmployeeDto trainer) {
    this.trainer = trainer;
  }

  public EmployeeDto getCoTrainer() {
    return coTrainer;
  }

  public void setCoTrainer(EmployeeDto coTrainer) {
    this.coTrainer = coTrainer;
  }

  public List<EmployeeDto> getAssociates() {
    return associates;
  }

  public void setAssociates(List<EmployeeDto> associates) {
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

  public ResourceMetadataDto getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadataDto resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BatchDto batchDTO = (BatchDto) o;
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
