package org.example.dto;

public class CategorySummaryDTO {

    private String category;
    private Long count;

    public CategorySummaryDTO(String category, Long count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public Long getCount() {
        return count;
    }
}