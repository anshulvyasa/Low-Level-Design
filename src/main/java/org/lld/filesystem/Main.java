package org.lld.filesystem;

import org.lld.filesystem.service.FileSystemManager;

public class Main {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager=FileSystemManager.getFileSystemManager();

        fileSystemManager.createPath("/omega/file1.txt");
        fileSystemManager.createPath("/omega/file2.txt");

        fileSystemManager.display();

        fileSystemManager.setContent("/omega/file1.txt","Hey This is Anshul Vyas");
        System.out.println(fileSystemManager.getContent("/omega/file1.txt"));
    }
}
