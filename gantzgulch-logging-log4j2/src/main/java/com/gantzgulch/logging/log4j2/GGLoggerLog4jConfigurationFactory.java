package com.gantzgulch.logging.log4j2;

import java.net.URI;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

@Plugin(name = "FallbackConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(0)
public class GGLoggerLog4jConfigurationFactory extends ConfigurationFactory{

    public GGLoggerLog4jConfigurationFactory() {
    }
    
    @Override
    protected String[] getSupportedTypes() {
        return new String[] { ".xml.gglog4jdefault" };
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, String name, URI configLocation) {
        return createConfiguration(name, super.newConfigurationBuilder());
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, String name, URI configLocation, ClassLoader loader) {
        return createConfiguration(name, super.newConfigurationBuilder());
    }
    
    
    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        return createConfiguration("default", newConfigurationBuilder());
    }


    public Configuration createConfiguration(final String name, final ConfigurationBuilder<BuiltConfiguration> builder) {

        builder.setStatusLevel(Level.WARN);
        builder.setConfigurationName(name);

        
        //
        // Layout
        //
        
        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d %-5level %c{1}: %msg%n%throwable");

        
        //
        // Console Appender
        //

        AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
        console.add(standard);
        
        builder.add(console);
        
        
        //
        // Root Logger
        //
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.DEBUG);

        rootLogger.add(builder.newAppenderRef("stdout"));

        builder.add(rootLogger);
        
        
        //
        // Build it.
        //

        return builder.build();
        
    }


}
