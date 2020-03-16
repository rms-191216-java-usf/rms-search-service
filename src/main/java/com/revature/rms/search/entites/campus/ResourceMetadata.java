package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.ResourceMetadataDto;

import java.util.Objects;

public class ResourceMetadata {

    private int resourceCreator;
    private String resourceCreationDateTime;
    private int lastModifier;
    private String lastModifiedDateTime;
    private int resourceOwner;

    public ResourceMetadata() {
        super();
    }

    public ResourceMetadata(String resourceCreationDateTime, String lastModifiedDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public ResourceMetadata(int resourceCreator, String resourceCreationDateTime, int lastModifier, String lastModifiedDateTime, int resourceOwner) {
        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = resourceCreationDateTime;
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

    public ResourceMetadataDto extractCampusMeta(){
        return new ResourceMetadataDto(this.resourceCreationDateTime, this.lastModifiedDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceMetadata that = (ResourceMetadata) o;
        return resourceCreator == that.resourceCreator &&
                lastModifier == that.lastModifier &&
                resourceOwner == that.resourceOwner &&
                Objects.equals(resourceCreationDateTime, that.resourceCreationDateTime) &&
                Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceCreator, resourceCreationDateTime, lastModifier, lastModifiedDateTime, resourceOwner);
    }

    @Override
    public String toString() {
        return "ResourceMetadata{" +
                "resourceCreator=" + resourceCreator +
                ", resourceCreationDateTime='" + resourceCreationDateTime + '\'' +
                ", lastModifier=" + lastModifier +
                ", lastModifiedDateTime='" + lastModifiedDateTime + '\'' +
                ", resourceOwner=" + resourceOwner +
                '}';
    }
}
