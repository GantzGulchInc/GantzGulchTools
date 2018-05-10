package com.gantzgulch.tools.httpclient.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.gantzgulch.tools.common.lang.GGCloseables;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.GGSslCertificateRetriever;

public class GGSslCertificateRetrieverImpl implements GGSslCertificateRetriever {

    private static final GGLogger LOG = GGLogger.getLogger(GGSslCertificateRetrieverImpl.class);

    public GGSslCertificateRetrieverImpl() {

    }

    @Override
    public X509Certificate[] retrieve(final URI uri) {

        try {
            
            final String host = uri.getHost();
            final int port = getPort(uri);
            
            LOG.trace("retrieve: host: %s, port: %d", host, port);
            
            final InMemoryTrustManager tm = createTrustManager();
            
            final SSLSocketFactory factory = createSocketFactory(tm);
            
            final SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            
            socket.setSoTimeout(10000);
            
            LOG.trace("retrieve: startHandshake...");
            
            try {
                
                socket.startHandshake();
                
            }finally{
                
                GGCloseables.closeQuietly(socket);
            }
            
            return tm.chain;
                
        } catch (final GeneralSecurityException | IOException e) {
            LOG.warn(e, "Error");
            throw new RuntimeException(e);
        }

    }

    private SSLSocketFactory createSocketFactory(final TrustManager tm) throws GeneralSecurityException {
        
        final SSLContext sc = SSLContext.getInstance("TLS");

        sc.init(null, new TrustManager[]{tm}, null);
        
        final SSLSocketFactory factory = sc.getSocketFactory();
        
        return factory;
        
    }
    
    private InMemoryTrustManager createTrustManager() throws GeneralSecurityException, IOException {
        
        final KeyStore ks = getTrustStore();

        final String trustManagerDefaultAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        
        LOG.trace("retrieve: trustManagerDefaultAlgorithm: %s", trustManagerDefaultAlgorithm);
        
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(trustManagerDefaultAlgorithm);
        
        tmf.init(ks);
        
        final X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        
        final InMemoryTrustManager tm = new InMemoryTrustManager(defaultTrustManager);
        
        return tm;
        
    }
    
    
    private int getPort(final URI uri) {
        
        final int uriPort = uri.getPort();
        
        if( uriPort > 0 ){
            return uriPort;
        }
        
        switch(uri.getScheme()){
        case "http":
            return 80;
        case "https":
            return 443;
        }
        
        return 0;
    }

    private KeyStore getTrustStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        
        final KeyStore keyStore = KeyStore.getInstance("JKS");
        
        final Path path = Paths.get(System.getProperty("java.home"), "lib", "security", "cacerts");
        
        try(final InputStream is = Files.newInputStream(path) ){
            keyStore.load(is, "changeit".toCharArray());
        }
        
        return keyStore;
    }

    private static class InMemoryTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        InMemoryTrustManager(final X509TrustManager tm) {
            this.tm = tm;
        }

        public X509Certificate[] getAcceptedIssuers() {

            return new X509Certificate[0];
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }
    
}
