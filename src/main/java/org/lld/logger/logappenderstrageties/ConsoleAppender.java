package org.lld.logger.logappenderstrageties;

import org.lld.logger.loggerutilities.LogMessage;

public class ConsoleAppender implements  LogAppender{

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logMessage);
    }
}
