package com.blog.blogApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Integer fieldValue;

    public ResourceNotFoundException(String fieldName, String resourceName, Integer fieldValue) {
        super(String.format("%s  %s : %s",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
