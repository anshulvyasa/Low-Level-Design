package org.lld.logger.loggerutilities;

import lombok.extern.java.Log;
import org.lld.logger.enums.LogLevel;

public class LogMessage {
    private LogLevel logLevel;
    private final long timestmp;
    private  final String message;

    public LogMessage(LogLevel logLevel,String message){
        this.logLevel=logLevel;
        this.timestmp=System.currentTimeMillis();
        this.message=message;
    }

    public LogLevel getLogLevel(){
        return  logLevel;
    }

    public long getTimestmp(){
        return  timestmp;
    }

    public String getMessage(){
        return  message;
    }

    @Override
    public String toString(){
        return "["+logLevel+"]"+timestmp+" - "+message;
    }
}
