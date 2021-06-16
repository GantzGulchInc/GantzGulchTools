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
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

@Plugin(name = "FallbackConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(0)
public class GGLoggerLog4jConfigurationFactory extends ConfigurationFactory{

    @Override
    protected String[] getSupportedTypes() {
        return new String[] { ".xml.gglog4jdefault" };
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, String name, URI configLocation) {
        System.out.printf("getConfiguration: name: %s, configLocation: %s, loggerContext: %s\n", name, configLocation, loggerContext);
        return createConfiguration(name, super.newConfigurationBuilder());
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, String name, URI configLocation, ClassLoader loader) {
        System.out.printf("getConfiguration: name: %s, configLocation: %s, loggerContext: %s\n", name, configLocation, loggerContext);
        return createConfiguration(name, super.newConfigurationBuilder());
    }
    
    
    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        
        System.out.printf("getConfiguration: source: %s, loggerContext: %s\n", source, loggerContext);
        
        return new XmlConfiguration(loggerContext, source);

        // return createConfiguration("fallback", super.newConfigurationBuilder());
    }


    public Configuration createConfiguration(final String name, final ConfigurationBuilder<BuiltConfiguration> builder) {

        System.out.println("LOG4J: FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK FALLBACK ");
        
        System.out.println("log4j2.configuration: " + System.getProperty("log4j2.configurationFile"));
        
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
