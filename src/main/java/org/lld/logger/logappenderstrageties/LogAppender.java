package org.lld.logger.logappenderstrageties;

import org.lld.logger.loggerutilities.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
