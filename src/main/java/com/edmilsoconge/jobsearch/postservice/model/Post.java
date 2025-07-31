package com.edmilsoconge.jobsearch.postservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "posts")
public class Post{

    @Id
    private String id;
    private String companyName;
    private String jobTitle;
    private String description;

    public Post() {}

    public Post(String id, String companyName, String jobTitle, String description) {
        this.id = id;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
        if (!(o instanceof Post post)) return false;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getCompanyName(), post.getCompanyName()) && Objects.equals(getJobTitle(), post.getJobTitle()) && Objects.equals(getDescription(), post.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompanyName(), getJobTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
