package com.edmilsoconge.jobsearch.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class Post{

    @Id
    private String id;
    private String profile;
    private String description;
    private int experience;
    private List<String> techs;

    public Post() {}

    public Post(String id, String profile, String description, int experience, List<String> techs) {
        this.id = id;
        this.profile = profile;
        this.description = description;
        this.experience = experience;
        this.techs = techs;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProfile() { return profile; }
    public void setProfile(String profile) { this.profile = profile; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public List<String> getTechs() { return techs; }
    public void setTechs(List<String> techs) { this.techs = techs; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return getExperience() == post.getExperience() && Objects.equals(getId(), post.getId()) && Objects.equals(getProfile(), post.getProfile()) && Objects.equals(getDescription(), post.getDescription()) && Objects.equals(getTechs(), post.getTechs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProfile(), getDescription(), getExperience(), getTechs());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                ", experience=" + experience +
                ", techs=" + techs +
                '}';
    }
}
