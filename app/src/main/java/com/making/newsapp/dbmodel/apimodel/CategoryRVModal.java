package com.making.newsapp.dbmodel.apimodel;

public class CategoryRVModal {

    private String Category;
    private int CategoryImageUrl;

    public CategoryRVModal(String category, int categoryImageUrl) {
        Category = category;
        CategoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(int categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
