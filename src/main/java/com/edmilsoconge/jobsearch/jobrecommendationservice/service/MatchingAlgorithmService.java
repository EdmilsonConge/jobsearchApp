package com.edmilsoconge.jobsearch.jobrecommendationservice.service;

import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;
import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MatchingAlgorithmService {

    public double calculateMatchScore(UserResponse userResponse, PostDTO postDTO) {
        double skillsMatch = calculateSkillsMatch(userResponse, postDTO);
        double experienceMatch = calculateExperienceMatch(userResponse, postDTO);
        double educationMatch = calculateEducationMatch(userResponse, postDTO);
        double descriptionMatch = calculateDescriptionMatch(userResponse, postDTO);

        return (skillsMatch * 0.4) + (experienceMatch * 0.3) + (educationMatch * 0.2) + (descriptionMatch * 0.1);
    }

    public String generateMatchReason(UserResponse userResponse, PostDTO postDTO, double matchScore) {
        if (matchScore>= 0.8) {
            return "Excellent match - Your skills and experience align perfectly with this role";
        } else if (matchScore >= 0.6) {
            return "Good match - Strong alignment with most requirements";
        } else if (matchScore >= 0.4) {
            return "Potential match - Some relevant experience, could be a growth opportunity";
        } else {
            return "Learning opportunity - Different from your background but could expand your skills";
        }
    }

    private double calculateSkillsMatch(UserResponse userResponse, PostDTO postDTO) {

        Set<String> userSkills = extractSkillsFromText(userResponse.description());
        Set<String> postSkills = extractSkillsFromText(postDTO.description());

        if (postSkills.isEmpty()) return 0.5;

        long matchingSkills = userSkills  // Remove .size()
                .stream()
                .mapToLong(skill -> postSkills.contains(skill) ? 1 : 0)
                .sum();

        return (double) matchingSkills / postSkills.size();
    }


    private double calculateExperienceMatch(UserResponse userResponse, PostDTO postDTO) {
        if (userResponse.experiences() == null || userResponse.experiences().isEmpty()) {
            return 0.3;
        }

        String jobDescription = postDTO.description().toLowerCase();
        int experienceYears = userResponse.experiences().size();

        if (jobDescription.contains("senior") || jobDescription.contains("lead")) {
            return Math.min(1.0, experienceYears / 5.0);
        } else if (jobDescription.contains("junior") || jobDescription.contains("entry")) {
            return experienceYears <= 2 ? 1.0 : 0.7;
        }else {
            return Math.min(1.0, experienceYears / 3.0);
        }
    }

    private double calculateEducationMatch(UserResponse userResponse, PostDTO postDTO) {
        if (userResponse.educations() == null || userResponse.educations().isEmpty()) {
            return 0.4;
        }

        String jobDescription = postDTO.description().toLowerCase();
        boolean requiresDegree = jobDescription.contains("degree") ||
                jobDescription.contains("bachelor") ||
                jobDescription.contains("master");

        if (requiresDegree) {
            return 1.0;
        } else {
            return 0.8;
        }
    }

    private double calculateDescriptionMatch(UserResponse userResponse, PostDTO postDTO) {
        if (userResponse.description() == null || userResponse.description().isEmpty()) {
            return 0.5;
        }

        Set<String> userWords = extractSkillsFromText(userResponse.description());
        Set<String> jobWords = extractSkillsFromText(postDTO.description());

        long matchingWords = userWords
                .stream()
                .mapToLong(word -> jobWords.contains(word) ? 1 : 0)
                .sum();

        return Math.min(1.0, (double) matchingWords / Math.max(jobWords.size(), 1));
    }

    private Set<String> extractSkillsFromText(String text) {
        if (text == null) return new HashSet<>();

        String[] commonSkills = {
                "java", "python", "javascript", "react", "spring", "mongodb",
                "sql", "git", "docker", "kubernetes", "aws", "azure",
                "management", "leadership", "communication", "teamwork"
        };
        Set<String> foundSkills = new HashSet<>();
        String lowerText = text.toLowerCase();

        for (String skill: commonSkills) {
            if (lowerText.contains(skill)) {
                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }

    private Set<String> extractWordsFromText(String text) {
        if (text == null) return new HashSet<>();

        return new HashSet<>(Arrays.asList(
                text.toLowerCase()
                        .replaceAll("a-zA-Z\\s", "")
                        .split("\\s+")
        ));
    }
}
