package org.lld.logger.logappenderstrageties;

import org.lld.logger.loggerutilities.LogMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements  LogAppender{

    private final String filePath;
    FileAppender(String filePath){
        this.filePath=filePath;
    }

    @Override
    public void append(LogMessage logMessage) {
        try(FileWriter fileWriter=new FileWriter(filePath,true)){
            fileWriter.write(logMessage.toString()+"\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
