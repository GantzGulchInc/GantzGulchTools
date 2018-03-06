package com.gantzgulch.tools.common.config;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.logging.GGLogger;

public final class ConfigurationUtil {

    private static final GGLogger LOG = GGLogger.getLogger(ConfigurationUtil.class);

    private ConfigurationUtil() {
        throw new UnsupportedOperationException();
    }

    public static String getConfigValue(final String name) {

        LOG.info("getConfigValue: " + name);

        final String propValue = System.getProperty(name);

        if ( GGStrings.isNotBlank(propValue)) {

            LOG.info("getConfigValue: name: %s, property value: %s", name, propValue);

            return propValue;
        }

        final String envValue = System.getenv().get(name);

        if (GGStrings.isNotBlank(envValue)) {

            LOG.info("getConfigValue: name: %s, environment value: %s", name, envValue);

            return envValue;

        }

        LOG.info("getConfigValue: name: %s not found.", name);

        return null;

    }

    public static int getConfigValue(final String name, int defaultValue) {

        try {
            final String stringValue = getConfigValue(name);
            return Integer.parseInt(stringValue);
        } catch (final RuntimeException e) {
            return defaultValue;
        }
    }

}
