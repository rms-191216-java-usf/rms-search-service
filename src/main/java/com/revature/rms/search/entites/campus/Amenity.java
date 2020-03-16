package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.AmenityDto;

import java.util.Objects;

public class Amenity {

    private AmenityType type;
    private AmenityStatus status;

    public Amenity() {
        super();
    }

    public Amenity(AmenityType type, AmenityStatus status) {
        this.type = type;
        this.status = status;
    }

    public AmenityType getType() {
        return type;
    }

    public void setType(AmenityType type) {
        this.type = type;
    }

    public AmenityStatus getStatus() {
        return status;
    }

    public void setStatus(AmenityStatus status) {
        this.status = status;
    }

    public AmenityDto extractAmenityDto(){
        return new AmenityDto(this.type, this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return type == amenity.type &&
                status == amenity.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, status);
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "type=" + type +
                ", status=" + status +
                '}';
    }
}
