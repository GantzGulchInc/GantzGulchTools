package com.gantzgulch.tools.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

public class GGPaths {

    
    public static final void makeDirectories(final Path path) throws IOException {
    
        final File file = path.toFile();
        
        file.mkdirs();
        
    }
    
    public static final void deleteRecursive(final Path path) throws IOException {
        
        FileUtils.deleteDirectory(path.toFile());
        
    }
}
