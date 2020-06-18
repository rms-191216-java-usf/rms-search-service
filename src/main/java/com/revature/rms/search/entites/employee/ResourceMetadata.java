package com.revature.rms.search.entites.employee;

import com.revature.rms.search.dtos.ResourceMetadataDto;

import java.util.Objects;

public class ResourceMetadata {

  private int resourceId;

  private int resourceCreator;

  private String resourceCreationDateTime;

  private int lastModifier;

  private String lastModifiedDateTime;

  private int resourceOwner;

  private boolean currentlyActive;

  public ResourceMetadata() {
    super();
  }

  public ResourceMetadata(
      int resourceId,
      int resourceCreator,
      String resourceCreationDateTime,
      int lastModifier,
      String lastModifiedDateTime,
      int resourceOwner,
      boolean currentlyActive) {
    this.resourceId = resourceId;
    this.resourceCreator = resourceCreator;
    this.resourceCreationDateTime = resourceCreationDateTime;
    this.lastModifier = lastModifier;
    this.lastModifiedDateTime = lastModifiedDateTime;
    this.resourceOwner = resourceOwner;
    this.currentlyActive = currentlyActive;
  }

  public int getResourceId() {
    return resourceId;
  }

  public void setResourceId(int resourceId) {
    this.resourceId = resourceId;
  }

  public int getResourceCreator() {
    return resourceCreator;
  }

  public void setResourceCreator(int resourceCreator) {
    this.resourceCreator = resourceCreator;
  }

  public String getResourceCreationDateTime() {
    return resourceCreationDateTime;
  }

  public void setResourceCreationDateTime(String resourceCreationDateTime) {
    this.resourceCreationDateTime = resourceCreationDateTime;
  }

  public int getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(int lastModifier) {
    this.lastModifier = lastModifier;
  }

  public String getLastModifiedDateTime() {
    return lastModifiedDateTime;
  }

  public void setLastModifiedDateTime(String lastModifiedDateTime) {
    this.lastModifiedDateTime = lastModifiedDateTime;
  }

  public int getResourceOwner() {
    return resourceOwner;
  }

  public void setResourceOwner(int resourceOwner) {
    this.resourceOwner = resourceOwner;
  }

  public boolean isCurrentlyActive() {
    return currentlyActive;
  }

  public void setCurrentlyActive(boolean currentlyActive) {
    this.currentlyActive = currentlyActive;
  }

  public ResourceMetadataDto extractEmployeeMeta() {
    return new ResourceMetadataDto(this.resourceCreationDateTime, this.lastModifiedDateTime, this.currentlyActive);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResourceMetadata that = (ResourceMetadata) o;
    return resourceId == that.resourceId
        && resourceCreator == that.resourceCreator
        && lastModifier == that.lastModifier
        && resourceOwner == that.resourceOwner
        && Objects.equals(resourceCreationDateTime, that.resourceCreationDateTime)
        && Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime)
        && Objects.equals(currentlyActive, that.currentlyActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            resourceId,
        resourceCreator,
        resourceCreationDateTime,
        lastModifier,
        lastModifiedDateTime,
        resourceOwner,
            currentlyActive);
  }

  @Override
  public String toString() {
    return "ResourceMetadata{"
        + "id="
        + resourceId
        + ", resourceCreator="
        + resourceCreator
        + ", resourceCreationDateTime='"
        + resourceCreationDateTime
        + '\''
        + ", lastModifier="
        + lastModifier
        + ", lastModifiedDateTime='"
        + lastModifiedDateTime
        + '\''
        + ", resourceOwner="
        + resourceOwner
        + '\''
        + ", isActive="
        + currentlyActive
        + '}';
  }
}
