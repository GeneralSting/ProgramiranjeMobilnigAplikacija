package com.example.pma_1.Classes.Models;

public class Metadata {
    private int count;
    private int limit;
    private int offset;
    private String status;
    private int total;

    public Metadata(int count, int limit, int offset, String status, int total) {
        this.count = count;
        this.limit = limit;
        this.offset = offset;
        this.status = status;
        this.total = total;
    }
}
