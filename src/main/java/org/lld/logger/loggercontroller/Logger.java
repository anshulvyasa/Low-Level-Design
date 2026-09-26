package org.lld.logger.loggercontroller;

import org.lld.logger.enums.LogLevel;
import org.lld.logger.logappenderstrageties.LogAppender;
import org.lld.logger.loggerutilities.LogMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static Map<String,Logger> instances=new ConcurrentHashMap<>();
    private LoggerConfig loggerConfig;

    private Logger(LogLevel logLevel, LogAppender logAppender){
         this.loggerConfig=new LoggerConfig(logLevel,logAppender);
    }

    public static Logger getLogger(LogLevel logLevel,LogAppender logAppender){
        String key=logLevel.name()+"_"+logAppender.getClass().getName();

        return  instances.computeIfAbsent(key,k->new Logger(logLevel,logAppender));
    }

    public void setLoggerConfig(LoggerConfig config){
        this.loggerConfig=config;
    }

    public void log(LogLevel level,String message){
         if(level.getValue()>=loggerConfig.getLogLevel().getValue()){
             LogMessage logMessage=new LogMessage(level,message);
             loggerConfig.getLogAppender().append(logMessage);
         }
    }

    public  void info(String message){ log(LogLevel.INFO,message); }
    public  void debug(String message){ log(LogLevel.DEBUG,message);}
    public  void error(String message){ log(LogLevel.ERROR,message);}
}
