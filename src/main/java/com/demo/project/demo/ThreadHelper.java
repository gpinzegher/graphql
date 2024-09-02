package com.demo.project.demo;

import org.slf4j.Logger;

public class ThreadHelper {
    public static void log(Logger log, Thread thread, Class loggingClass, String function) {
        log.warn(
                "[THREAD] " + loggingClass.getSimpleName() + "." + function + " -> Current Thread -> " +
                        "Name: [" + thread.getName() + "] " +
                        "Id: [" + thread.threadId() + "] " +
                        "Priority: [" + thread.getPriority() + "] " +
                        "State: [" + thread.getState() + "] " +
                        "ThreadGroup: [" + thread.getThreadGroup().toString() + "] "
        );
    }
}
