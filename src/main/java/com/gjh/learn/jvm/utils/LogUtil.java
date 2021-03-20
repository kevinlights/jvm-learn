package com.gjh.learn.jvm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class LogUtil {
    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);
    private LogUtil() {}

    public static void trace(String msg, Object... args) {
        if (LOG.isTraceEnabled()) {
            LOG.trace(msg, args);
        }
    }

    public static void debug(String msg, Object... args) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(msg, args);
        }
    }

    public static void info(String msg, Object... args) {
        if (LOG.isInfoEnabled()) {
            LOG.info(msg, args);
        }
    }

    public static void warn(String msg, Object... args) {
        if (LOG.isWarnEnabled()) {
            LOG.warn(msg, args);
        }
    }

    public static void error(String msg, Object... args) {
        if (LOG.isErrorEnabled()) {
            LOG.error(msg, args);
        }
    }

}
