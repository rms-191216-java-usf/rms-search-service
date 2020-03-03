package com.revature.rms.search.entites.employee;

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

    public String getResourceCreationDateTime() {
        return resourceCreationDateTime;
    }

    public int getLastModifier() {
        return lastModifier;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public int getResourceOwner() {
        return resourceOwner;
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
