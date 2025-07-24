package com.edmilsoconge.jobsearch;

import java.util.List;

public record PostDTO(String profile, String description, int experience, List<String> techs) {
}
