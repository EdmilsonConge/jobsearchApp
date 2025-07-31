package com.edmilsoconge.jobsearch.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "educations")
public class Education {
    @Id
    private String id;
    private String nameOfEducation;
    private LocalDate dateOfConclusion;
    private String locationOfEducation;
    private String description;

    public Education(String id, String nameOfEducation, LocalDate dateOfConclusion, String locationOfEducation, String description) {
        this.id = id;
        this.nameOfEducation = nameOfEducation;
        this.dateOfConclusion = dateOfConclusion;
        this.locationOfEducation = locationOfEducation;
        this.description = description;
    }

    public Education() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfEducation() {
        return nameOfEducation;
    }

    public void setNameOfEducation(String nameOfEducation) {
        this.nameOfEducation = nameOfEducation;
    }

    public LocalDate getDateOfConclusion() {
        return dateOfConclusion;
    }

    public void setDateOfConclusion(LocalDate dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public String getLocationOfEducation() {
        return locationOfEducation;
    }

    public void setLocationOfEducation(String locationOfEducation) {
        this.locationOfEducation = locationOfEducation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education education)) return false;
        return Objects.equals(getId(), education.getId()) && Objects.equals(getNameOfEducation(), education.getNameOfEducation()) && Objects.equals(getDateOfConclusion(), education.getDateOfConclusion()) && Objects.equals(getLocationOfEducation(), education.getLocationOfEducation()) && Objects.equals(getDescription(), education.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameOfEducation(), getDateOfConclusion(), getLocationOfEducation(), getDescription());
    }

    @Override
    public String toString() {
        return "Education{" +
                "id='" + id + '\'' +
                ", nameOfEducation='" + nameOfEducation + '\'' +
                ", dateOfConclusion=" + dateOfConclusion +
                ", locationOfEducation='" + locationOfEducation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
