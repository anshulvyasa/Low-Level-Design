package org.lld.filesystem.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class File extends  FileSystemNode{
    @Setter private String extension;
    @Setter private String content;

    public File(long createdAt,String name) {
        super(createdAt,name);
         this.extension=extractExtension(name);
    }

    private String extractExtension(String name){
        int dotIndex=name.lastIndexOf('.');
        return dotIndex>0?name.substring(dotIndex+1):"";
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        String intend=" ".repeat(depth*2);
        System.out.println(intend+" file:"+getName());
    }
}
