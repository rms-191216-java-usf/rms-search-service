package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.AppUser;

import java.util.Objects;

public class ResourceMetadataDto {

  private AppUser resourceCreator;
  private String resourceCreationDateTime;
  private AppUser lastModifier;
  private String lastModifiedDateTime;
  private AppUser resourceOwner;
  private boolean currentlyActive;

  public ResourceMetadataDto() {}

  public ResourceMetadataDto(String resourceCreationDateTime, String lastModifiedDateTime, boolean currentlyActive) {
    this.resourceCreationDateTime = resourceCreationDateTime;
    this.lastModifiedDateTime = lastModifiedDateTime;
    this.currentlyActive = currentlyActive;
  }

  public ResourceMetadataDto(
      AppUser resourceCreator,
      String resourceCreationDateTime,
      AppUser lastModifier,
      String lastModifiedDateTime,
      AppUser resourceOwner,
      boolean currentlyActive) {
    this.resourceCreator = resourceCreator;
    this.resourceCreationDateTime = resourceCreationDateTime;
    this.lastModifier = lastModifier;
    this.lastModifiedDateTime = lastModifiedDateTime;
    this.resourceOwner = resourceOwner;
    this.currentlyActive = currentlyActive;
  }

  public AppUser getResourceCreator() {
    return resourceCreator;
  }

  public void setResourceCreator(AppUser resourceCreator) {
    this.resourceCreator = resourceCreator;
  }

  public String getResourceCreationDateTime() {
    return resourceCreationDateTime;
  }

  public void setResourceCreationDateTime(String resourceCreationDateTime) {
    this.resourceCreationDateTime = resourceCreationDateTime;
  }

  public AppUser getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(AppUser lastModifier) {
    this.lastModifier = lastModifier;
  }

  public String getLastModifiedDateTime() {
    return lastModifiedDateTime;
  }

  public void setLastModifiedDateTime(String lastModifiedDateTime) {
    this.lastModifiedDateTime = lastModifiedDateTime;
  }

  public AppUser getResourceOwner() {
    return resourceOwner;
  }

  public void setResourceOwner(AppUser resourceOwner) {
    this.resourceOwner = resourceOwner;
  }

  public boolean isCurrentlyActive() {
    return currentlyActive;
  }

  public void setCurrentlyActive(boolean currentlyActive) {
    this.currentlyActive = currentlyActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResourceMetadataDto that = (ResourceMetadataDto) o;
    return Objects.equals(resourceCreator, that.resourceCreator)
        && Objects.equals(resourceCreationDateTime, that.resourceCreationDateTime)
        && Objects.equals(lastModifier, that.lastModifier)
        && Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime)
        && Objects.equals(resourceOwner, that.resourceOwner)
        && Objects.equals(currentlyActive, that.currentlyActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        resourceCreator,
        resourceCreationDateTime,
        lastModifier,
        lastModifiedDateTime,
        resourceOwner,
            currentlyActive);
  }

  @Override
  public String toString() {
    return "ResourceMetadataDto{"
        + "resourceCreator="
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
        + ", isActive="
        + currentlyActive
        + '}';
  }
}
