package com.example.office.http.entity;

/**
 * Created by YCY on 16/5/5.
 */
public class FileProps {
    private String destDir;
    private String destName;

    public FileProps(String destDir, String destName) {
        this.destDir = destDir;
        this.destName = destName;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    @Override
    public String toString() {
        return "FileProps{" +
                "destDir='" + destDir + '\'' +
                ", destName='" + destName + '\'' +
                '}';
    }
}
