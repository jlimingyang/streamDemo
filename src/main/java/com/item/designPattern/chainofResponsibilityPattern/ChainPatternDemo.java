package com.item.designPattern.chainofResponsibilityPattern;

public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.DEBUG);

        errorLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.DEBUG, "This is an debug information.");
        System.out.println("");
        loggerChain.logMessage(AbstractLogger.INFO,
                "This is a info level information.");
        System.out.println("");
        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
