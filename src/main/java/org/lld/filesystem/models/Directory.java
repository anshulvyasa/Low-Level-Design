package org.lld.filesystem.models;

public class Directory extends FileSystemNode{
    public Directory(long createdAt, String name) {
        super(createdAt, name);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        String intend=" ".repeat(depth*2);
        System.out.println(intend+" dir:"+getName());

        for(FileSystemNode node:getChildrens().values()){
             node.display(depth+1);
        }
    }
}
