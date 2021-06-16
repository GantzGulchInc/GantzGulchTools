package com.gantzgulch.tools.io.tar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.IOUtils;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.io.GGPaths;

public class GGTar {

    private static final GGLogger LOG = GGLogger.getLogger(GGTar.class);

    public static Collection<Path> unTar(final Path tarPath, final Path destination) throws IOException {

        try (final InputStream is = Files.newInputStream(tarPath)) {
            return unTar(is, destination);
        }

    }

    public static Collection<Path> unTar(final InputStream is, final Path destination) throws IOException {

        LOG.info("unTar: destination: %s", destination);

        final List<Path> extracted = new ArrayList<>();

        try (final TarArchiveInputStream tis = new TarArchiveInputStream(is)) {

            ArchiveEntry archiveEntry = null;

            while ((archiveEntry = tis.getNextEntry()) != null) {

                if (!archiveEntry.isDirectory()) {

                    final Path entryPath = Paths.get(archiveEntry.getName());

                    LOG.info("unTar: Extracting: %s, to %s", entryPath, destination);

                    final Path finalPath = destination.resolve(archiveEntry.getName()).normalize();

                    copyToOutput(tis, finalPath);

                    extracted.add(finalPath);

                }

            }

        }

        return extracted;
    }

    public static Collection<Path> unTarGzipped(final Path tarPath, final Path destination) throws IOException {

        try (final InputStream is = Files.newInputStream(tarPath)) {
            return unTarGzipped(is, destination);
        }

    }

    public static Collection<Path> unTarGzipped(final InputStream is, final Path destination) throws IOException {

        LOG.info("unTarGzipped: destination: %s", destination);

        try (final GzipCompressorInputStream gis = new GzipCompressorInputStream(is)) {

            return unTar(gis, destination);

        }

    }

    private static void copyToOutput(final TarArchiveInputStream tis, final Path entryPath) throws IOException {

        final Path parentPath = entryPath.getParent();

        LOG.info("copyToOuput: entryPath: %s, parentPath: %s", entryPath, parentPath);

        GGPaths.makeDirectories(parentPath);

        try (final OutputStream os = Files.newOutputStream(entryPath)) {
            IOUtils.copyLarge(tis, os);
        }

    }

}
