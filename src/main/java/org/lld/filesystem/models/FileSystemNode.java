package org.lld.filesystem.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
public abstract class FileSystemNode {
    @Setter
    private String name;
    // Can Be problematic
    private final Map<String,FileSystemNode> childrens;

    private final long createdAt;

    @Setter
    private long updatedAt;

    public FileSystemNode(long createdAt,String name){
        this.name=name;
        this.createdAt=createdAt;
        this.updatedAt=createdAt;
        childrens=new HashMap<>();
    }

    public FileSystemNode getFileSystemNodeByName(String name){
        return childrens.get(name);
    }

    public boolean addFileSystemNode(String name,FileSystemNode fileSystemNode){
        if(childrens.containsKey(name)) return false;

        childrens.put(name,fileSystemNode);
        return true;
    }

    public boolean removeFileSystemNode(String name){
        if(!childrens.containsKey(name)) return  false;
        childrens.remove(name);

        return true;
    }

    public abstract boolean isFile();
    public abstract  void display(int depth);
}
