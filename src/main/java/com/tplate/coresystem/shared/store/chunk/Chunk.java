package com.tplate.coresystem.shared.store.chunk;

import java.io.InputStream;

public class Chunk {
    private Integer nroChunk;
    private Integer totalChunks;
    private InputStream data;

    public boolean isLast() {
        return this.nroChunk.equals(totalChunks);
    }

    public Chunk(Integer nroChunk, Integer totalChunks, InputStream data){
        this.nroChunk = nroChunk;
        this.totalChunks = totalChunks;
        this.data = data;
    }

    public Chunk() {
    }

    public Integer getNroChunk() {
        return nroChunk;
    }

    public void setNroChunk(Integer nroChunk) {
        this.nroChunk = nroChunk;
    }

    public Integer getTotalChunks() {
        return totalChunks;
    }

    public void setTotalChunks(Integer totalChunks) {
        this.totalChunks = totalChunks;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }
}
