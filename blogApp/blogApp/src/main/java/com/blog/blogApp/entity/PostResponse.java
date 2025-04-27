package com.blog.blogApp.entity;

import java.util.List;

public class PostResponse {
    private List<Post> Content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;
    private Boolean isLastPage;

    public List<Post> getContent() {
        return Content;
    }

    public void setContent(List<Post> content) {
        Content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }
}
