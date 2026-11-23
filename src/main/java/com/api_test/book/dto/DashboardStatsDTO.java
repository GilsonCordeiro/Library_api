package com.api_test.book.dto;

public class DashboardStatsDTO {

    private final long totalBooks;
    private final long activeBooks;
    private final long inactiveBooks;
    private final long totalCategories;

    public DashboardStatsDTO(long totalBooks, long activeBooks, long inactiveBooks, long totalCategories) {
        this.totalBooks = totalBooks;
        this.activeBooks = activeBooks;
        this.inactiveBooks = inactiveBooks;
        this.totalCategories = totalCategories;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getActiveBooks() {
        return activeBooks;
    }

    public long getInactiveBooks() {
        return inactiveBooks;
    }

    public long getTotalCategories() {
        return totalCategories;
    }
}
