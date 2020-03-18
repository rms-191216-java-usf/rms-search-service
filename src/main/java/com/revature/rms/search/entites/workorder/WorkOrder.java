package com.revature.rms.search.entites.workorder;

import com.revature.rms.search.dtos.WorkOrderDto;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document(collection = "workorder")
public class WorkOrder {

  @Id private String id;

  @NotNull private String createdDateTime;

  @NotNull private String resolvedDateTime;

  @NotNull private Category category;

  @NotNull private String description;

  @NotNull private String contactEmail;

  @NotNull private int creatorId;

  @NotNull private int resolverId;

  public WorkOrder() {
    super();
  }

  public WorkOrder(
      String id,
      String createdDateTime,
      String resolvedDateTime,
      Category category,
      String description,
      String contactEmail,
      int creatorId,
      int resolverId) {
    this.id = id;
    this.createdDateTime = createdDateTime;
    this.resolvedDateTime = resolvedDateTime;
    this.category = category;
    this.description = description;
    this.contactEmail = contactEmail;
    this.creatorId = creatorId;
    this.resolverId = resolverId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatedDateTime() {
    return createdDateTime;
  }

  public void setCreatedDateTime(String createdDateTime) {
    this.createdDateTime = createdDateTime;
  }

  public String getResolvedDateTime() {
    return resolvedDateTime;
  }

  public void setResolvedDateTime(String resolvedDateTime) {
    this.resolvedDateTime = resolvedDateTime;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public int getResolverId() {
    return resolverId;
  }

  public void setResolverId(int resolverId) {
    this.resolverId = resolverId;
  }

  public WorkOrderDto extractWorkOrder() {
    return new WorkOrderDto(
        this.id,
        this.createdDateTime,
        this.resolvedDateTime,
        this.category,
        this.description,
        this.contactEmail);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkOrder workOrder = (WorkOrder) o;
    return id == workOrder.id
        && creatorId == workOrder.creatorId
        && resolverId == workOrder.resolverId
        && Objects.equals(createdDateTime, workOrder.createdDateTime)
        && Objects.equals(resolvedDateTime, workOrder.resolvedDateTime)
        && category == workOrder.category
        && Objects.equals(description, workOrder.description)
        && Objects.equals(contactEmail, workOrder.contactEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdDateTime, resolvedDateTime, category, description, contactEmail);
  }

  @Override
  public String toString() {
    return "WorkOrder{"
        + "id="
        + id
        + ", createdDateTime='"
        + createdDateTime
        + '\''
        + ", resolvedDateTime='"
        + resolvedDateTime
        + '\''
        + ", category="
        + category
        + ", description='"
        + description
        + '\''
        + ", contactEmail='"
        + contactEmail
        + '\''
        + ", creatorId="
        + creatorId
        + ", resolverId="
        + resolverId
        + '}';
  }
}
