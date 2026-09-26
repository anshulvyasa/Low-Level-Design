package org.lld.logger;

import org.lld.logger.enums.LogLevel;
import org.lld.logger.logappenderstrageties.ConsoleAppender;
import org.lld.logger.logappenderstrageties.LogAppender;
import org.lld.logger.loggercontroller.Logger;

public class Main {
    public static void main(String[] args) {
        LogAppender appender=new ConsoleAppender();
        Logger logger=Logger.getLogger(LogLevel.DEBUG,appender);

        logger.debug("Somethins is Wrong With The Working");
        logger.info("Main file is where you run this program");
    }
}
