package com.example.vishambar.webserviceusingokhttp;

public class Model {
    private String authorEmailId;
    private String authorPassword;
    private String authorName;

    public Model(String authorEmailId, String authorPassword, String authorName) {
        this.authorEmailId = authorEmailId;
        this.authorName = authorName;
        this.authorPassword = authorPassword;
    }


    @Override
    public String toString() {
        return authorEmailId+" "+authorName+" "+authorPassword;
    }
}
