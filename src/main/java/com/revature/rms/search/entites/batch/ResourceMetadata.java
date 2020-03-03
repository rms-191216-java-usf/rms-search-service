package com.revature.rms.search.entites.batch;

import java.util.Objects;

public class ResourceMetadata {

  private int resourceCreator;
  private String resourceCreatorDateTime;
  private int lastModifier;
  private String lastModifiedDateTime;
  private int resourceOwner;

  public ResourceMetadata() {}

  public ResourceMetadata(
      int resourceCreator,
      String resourceCreatorDateTime,
      int lastModifier,
      String lastModifiedDateTime,
      int resourceOwner) {
    this.resourceCreator = resourceCreator;
    this.resourceCreatorDateTime = resourceCreatorDateTime;
    this.lastModifier = lastModifier;
    this.lastModifiedDateTime = lastModifiedDateTime;
    this.resourceOwner = resourceOwner;
  }

  public int getResourceCreator() {
    return resourceCreator;
  }

  public void setResourceCreator(int resourceCreator) {
    this.resourceCreator = resourceCreator;
  }

  public String getResourceCreatorDateTime() {
    return resourceCreatorDateTime;
  }

  public void setResourceCreatorDateTime(String resourceCreatorDateTime) {
    this.resourceCreatorDateTime = resourceCreatorDateTime;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResourceMetadata that = (ResourceMetadata) o;
    return resourceCreator == that.resourceCreator
        && lastModifier == that.lastModifier
        && resourceOwner == that.resourceOwner
        && Objects.equals(resourceCreatorDateTime, that.resourceCreatorDateTime)
        && Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        resourceCreator,
        resourceCreatorDateTime,
        lastModifier,
        lastModifiedDateTime,
        resourceOwner);
  }

  @Override
  public String toString() {
    return "ResourceMetadata{"
        + "resourceCreator="
        + resourceCreator
        + ", resourceCreatorDateTime='"
        + resourceCreatorDateTime
        + '\''
        + ", lastModifier="
        + lastModifier
        + ", lastModifiedDateTime='"
        + lastModifiedDateTime
        + '\''
        + ", resourceOwner="
        + resourceOwner
        + '}';
  }
}
