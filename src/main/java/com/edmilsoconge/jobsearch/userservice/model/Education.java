package com.edmilsoconge.jobsearch.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "educations")
public class Education {
    @Id
    private String id;
    private String title;
    private String degree;
    private LocalDate dateOfConclusion;
    private String institution;
    private String description;

    public Education() {
    }

    public Education(String id, String title, LocalDate dateOfConclusion, String degree, String institution, String description) {
        this.id = id;
        this.title = title;
        this.dateOfConclusion = dateOfConclusion;
        this.degree = degree;
        this.institution = institution;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfConclusion() {
        return dateOfConclusion;
    }

    public void setDateOfConclusion(LocalDate dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
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
        return Objects.equals(getId(), education.getId()) && Objects.equals(getTitle(), education.getTitle()) && Objects.equals(getDegree(), education.getDegree()) && Objects.equals(getDateOfConclusion(), education.getDateOfConclusion()) && Objects.equals(getInstitution(), education.getInstitution()) && Objects.equals(getDescription(), education.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDegree(), getDateOfConclusion(), getInstitution(), getDescription());
    }

    @Override
    public String toString() {
        return "Education{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", degree='" + degree + '\'' +
                ", dateOfConclusion=" + dateOfConclusion +
                ", institution='" + institution + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
