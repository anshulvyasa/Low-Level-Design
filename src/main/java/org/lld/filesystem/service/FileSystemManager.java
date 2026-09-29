package org.lld.filesystem.service;

import org.lld.filesystem.models.Directory;
import org.lld.filesystem.models.File;
import org.lld.filesystem.models.FileSystemNode;

public class FileSystemManager {
    private static FileSystemManager fileSystemManager;
    private FileSystemNode root;

    private FileSystemManager(){
       root=new Directory(System.currentTimeMillis(),"/");
    }

    public  static FileSystemManager getFileSystemManager(){
        if(fileSystemManager==null){
            synchronized(FileSystemManager.class){
                if(fileSystemManager==null){
                    fileSystemManager=new FileSystemManager();
                }
            }

        }

        return  fileSystemManager;
    }

    // TODO: Implement methord for CRUD Operation
    private boolean isValidPath(String path){
        return  path!=null&&path.startsWith("/");
    }

    public boolean createPath(String path){
        if(!isValidPath(path)) return false;
        if(path.equals("/")) return false;

        String[] components=path.split("/");
        FileSystemNode current=root;

        for(int i=1;i<components.length-1;i++){
            String component=components[i];

            if(component.isEmpty()) continue;
            if(current.getFileSystemNodeByName(component)==null){
                current.addFileSystemNode(component,new Directory(System.currentTimeMillis(),component));
            }
            FileSystemNode node=current.getFileSystemNodeByName(component);

            if(node.isFile()) return false;

            current=node;
        }

        String lastComponent=components[components.length-1];
        if(lastComponent.isEmpty()) return  false;

        FileSystemNode node;
        if(lastComponent.contains(".")){
            node=new File(System.currentTimeMillis(),lastComponent);
        }
        else{
            node=new Directory(System.currentTimeMillis(),lastComponent);
        }

        current.addFileSystemNode(lastComponent,node);

        return true;
    }

    public boolean deletePath(String path){
        if(!isValidPath(path)) return false;
        if(path.equals("/")) return false;

        String parentPath=getParentPath(path);
        FileSystemNode node=getNode(parentPath);

        if(node==null||!node.isFile()) return false;

        String lastComponent=path.substring(path.lastIndexOf("/")+1);

        return  node.removeFileSystemNode(lastComponent);
    }

    private String getParentPath(String path){
        int lastIndex=path.lastIndexOf("/");

        if(lastIndex<=0) return "/";

        return path.substring(0,lastIndex);
    }

    private FileSystemNode getNode(String path){
         if(path==null) return  null;
         if(path.equals("/")) return root;

         String[] components=path.split("/");
         FileSystemNode current=root;

         for(int i=1;i<components.length;i++){
             String component=components[i];
             if(component.isEmpty()) continue;
             if(current.getFileSystemNodeByName(component)==null) return null;

             current=current.getFileSystemNodeByName(component);
         }

         return current;
    }

    public boolean setContent(String path,String content){
        FileSystemNode node=getNode(path);
        if(node==null||!node.isFile()) return false;

        File file=(File)node;
        file.setContent(content);

        return true;
    }

    public String getContent(String path){
        FileSystemNode node=getNode(path);
        if(node==null||!node.isFile()) return null;

        File file=(File)node;
        return file.getContent();
    }

    public void display(){
        root.display(0);
    }
}
