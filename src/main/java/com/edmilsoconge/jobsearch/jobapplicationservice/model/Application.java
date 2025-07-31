package com.edmilsoconge.jobsearch.jobapplicationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "applications")
public class Application {
    @Id
    private String id;
    private String userId;
    private String postId;
    private LocalDateTime appliedAt;
    private ApplicationStatus status;
    private String coverLetter;
    private List<DocumentInfo> documentIds;

    public Application() {}

    public Application(LocalDateTime appliedAt, ApplicationStatus status) {
        this.appliedAt = appliedAt;
        this.status = ApplicationStatus.PENDING;
    }

    public Application(String userId, String postId, String coverLetter, List<DocumentInfo> documentIds) {
        this.userId = userId;
        this.postId = postId;
        this.coverLetter = coverLetter;
        this.documentIds = documentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public List<DocumentInfo> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<DocumentInfo> documentIds) {
        this.documentIds = documentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getPostId(), that.getPostId()) && Objects.equals(getAppliedAt(), that.getAppliedAt()) && getStatus() == that.getStatus() && Objects.equals(getCoverLetter(), that.getCoverLetter()) && Objects.equals(getDocumentIds(), that.getDocumentIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getPostId(), getAppliedAt(), getStatus(), getCoverLetter(), getDocumentIds());
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", appliedAt=" + appliedAt +
                ", status=" + status +
                ", coverLetter='" + coverLetter + '\'' +
                ", documentIds=" + documentIds +
                '}';
    }
}
