package com.gantzgulch.tools.httpclient5.impl;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.GGSleep;
import com.gantzgulch.tools.httpclient5.*;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GGHttpClient5ImplTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpClient5ImplTest.class);

    private static final ContentType ACT = ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8);

    private static GGHttpClient5 httpClient;

    @BeforeClass
    public static void beforeClass() {
        httpClient = new GGHttpClient5Impl();
    }

    @Test
    public void getGoogleHome() {

        final URI uri = URI.create("https://www.google.com/");

        final HttpClientContext clientContext = httpClient.buildClientContext(null);

        final GGHttpClient5Response<String> googleHome = httpClient.get(uri, null, clientContext, httpClient.stringHandler());

        LOG.info("getGoogleHome: Home: %s\n", googleHome);

        final GGHttpClient5Response<String> googleHead = httpClient.head(uri, null, clientContext, httpClient.stringHandler());

        LOG.info("getGoogleHome: Head: %s\n", googleHead);

    }

    @Test
    @Ignore
    public void getAimAesKey() throws URISyntaxException {

        final String aimHost = "aim-srv.gantzgulch.com";
        final String aimKeyId = "ebc569f6-5941-11ed-a44a-0a2eba81beac";
        final String aimSerialNumber = "MP3-000038";
        final String aimPublicKey = "2880873E951A83BB81B465655936599F268E18F747AF4B733D90C63889578C6326CC6A9BBFB5D68458FCCDE3A0B5C7586BABB060DEEEC21441493F58464C4DD2";
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String aimNonce = sdf.format(new Date()) + "20230915135441RANDOM_TEXT";
        final String aimMode = "0";
        final String aimUsername = "admin";
        final String aimPassword = "%b#47^CXhWy0z2O7fi0&";

        final URI uri2 = GGURIBuilder
                .create()
                .withScheme("https")
                .withHost(aimHost)
                .withPath("/aim/rest/serverKey/%s/aesKey", aimKeyId)
                .withQueryParameter("sn", aimSerialNumber)
                .withQueryParameter("pk", aimPublicKey)
                .withQueryParameter("nonce", aimNonce)
                .withQueryParameter("mode", "0")
                .build();

        final GGHttpClient5Credentials creds = httpClient.createCredentials(uri2, GGHttpClient5AuthType.BASIC_AUTH, aimUsername, aimPassword);
        final List<GGHttpClient5Credentials> credentialsList = List.of(creds);

        final HttpClientContext clientContext = httpClient.buildClientContext(credentialsList);

        System.out.println("ur2: " + uri2);

        final GGHttpClient5Response<String> r = httpClient.get(uri2, null, clientContext, httpClient.stringHandler());

        LOG.info("getAimAesKey: JSON: %s\n", r);

    }

    @Test
    public void testParser() {

        ActiaResponseParser p = new ActiaResponseParser("100\r\n");

        LOG.info("testParser: p: %s", p);

        p = new ActiaResponseParser("100 stuff\r\n");

        LOG.info("testParser: p: %s", p);

    }

    @Test
    @Ignore
    public void updateActiaOS() throws URISyntaxException {

        final String host = "127.0.0.1";
        final int port = 46083;

        final String username = "admin";
        final String password = "auwaubDotmer";

        final File file = new File("/workspace/projects/Collinear/PodCloudSetup/PcsServer.bin/Data/Packages/os/CoreXS_PSA_AS-01.03.05-signed.zip");

        HttpClientContext clientContext = createClientContext(host, port, username, password);

        getDeviceStatus2(host, port, clientContext);

        // clientContext = createClientContext(host, port, username, password);

        // getDeviceStatus(host, port, clientContext);

        updateOs(host, port, file, clientContext);

        runUpdate(host, port, clientContext);

        monitorProgress(host, port, clientContext);

        // clientContext = createClientContext(host, port, username, password);

        reboot(host, port, clientContext);
    }


    private HttpClientContext createClientContext(
            final String host,
            final int port,
            final String username,
            final String password) {

        final GGHttpClient5Credentials creds = httpClient.createCredentials("http", host, port, GGHttpClient5AuthType.DIGEST_AUTH, username, password);

        final List<GGHttpClient5Credentials> credentialsList = List.of(creds);

        return httpClient.buildClientContext(credentialsList);
    }

    private void getDeviceStatus(
            final String host,
            final int port,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/device/status")
                .build();

        LOG.info("getDeviceStatus: uri: %s", uri);

        final GGHttpClient5Response<String> response = httpClient.get(uri, null, clientContext, httpClient.stringHandler());

        LOG.info("getDeviceStatus: uri: %s, response: ", uri, response);

    }

    private void getDeviceStatus2(
            final String host,
            final int port,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/api/devicemanagement/v2")
                .build();

        final HttpEntity updateFile = httpClient.toEntity("GetStatus", ACT);

        final GGHttpClient5Response<String> response = httpClient.post(uri, updateFile, null, clientContext, httpClient.stringHandler());

        LOG.info("getDeviceStatus2: response: %s", response);

        final ActiaResponseParser p = new ActiaResponseParser(response.getContent());

        LOG.info("getDeviceStatus2: parsedResponse: %s", p);

    }

    private void updateOs(
            final String host,
            final int port,
            final File file,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/updatefile")
                .build();

        final HttpEntity fileEntity = httpClient.toEntity(file, ACT);

        final GGHttpClient5Response<String> response = httpClient.put(uri, fileEntity, null, clientContext, httpClient.stringHandler());

        LOG.info("updateOs: put: uri: %s, response: %s", uri, response);

    }

    private void runUpdate(
            final String host,
            final int port,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/api/devicemanagement/v2")
                .build();

        final HttpEntity updateFile = httpClient.toEntity("RunUpdateAsync updatefile", ACT);

        final GGHttpClient5Response<String> response = httpClient.post(uri, updateFile, null, clientContext, httpClient.stringHandler());

        LOG.info("runUpdate: response: %s", response);

        final ActiaResponseParser p = new ActiaResponseParser(response.getContent());

        LOG.info("runUpdate: parsedResponse: %s", p);

    }

    private void monitorProgress(
            final String host,
            final int port,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/api/devicemanagement/v2")
                .build();

        while (true) {

            GGSleep.sleep(2, TimeUnit.SECONDS);

            final HttpEntity getUpdateStatus = httpClient.toEntity("GetUpdateStatus", ACT);

            final GGHttpClient5Response<String> response = httpClient.post(uri, getUpdateStatus, null, clientContext, httpClient.stringHandler());

            LOG.info("monitorProgress: uri: %s, response: %s", uri, response);

            final ActiaResponseParser p = new ActiaResponseParser(response.getContent());

            LOG.info("monitorProgress: parsedResponse: %s", p);

            final String r = response.getContent();

            if (r != null && r.contains("PROGRESS=100")) {

                LOG.info("monitorProgress: Found 100%");
                break;

            }
        }

    }

    private void reboot(
            final String host,
            final int port,
            final HttpClientContext clientContext) {

        final URI uri = GGURIBuilder
                .create()
                .withScheme("http")
                .withHost(host)
                .withPort(port)
                .withPath("/api/devicemanagement/v2")
                .build();

        for (int i = 0; i < 10; i++) {

            final HttpEntity updateFile = httpClient.toEntity("Reboot", ACT);

            final GGHttpClient5Response<String> response = httpClient.post(uri, updateFile, null, clientContext, httpClient.stringHandler());

            LOG.info("reboot: response: %s", response);

            final ActiaResponseParser p = new ActiaResponseParser(response.getContent());

            LOG.info("reboot: parsedResponse: %s", p);


            final String r = response.getContent();

            if (r != null && r.startsWith("100")) {
                LOG.info("reboot: REBOOTING!");
                break;
            }

            GGSleep.sleep(10, TimeUnit.SECONDS);
        }

    }


    public static class ActiaResponseParser {

        private static final String REGEX = "^(\\d+) *(.*)$";
        private static final Pattern PATTERN = Pattern.compile(REGEX);

        private final String response;

        private final String code;
        private final String content;

        public ActiaResponseParser(final String response) {

            this.response = response;

            if (response == null) {
                LOG.info("ctor: null");
                this.code = null;
                this.content = null;
            } else {

                final String r = response.replaceAll("\\R$", "");

                LOG.info("ctor: r: '%s'", r);

                final Matcher matcher = PATTERN.matcher(r);

                if (matcher.matches()) {
                    LOG.info("ctor: matches");
                    if( matcher.groupCount() > 0) {
                        this.code = matcher.group(1);
                    }else{
                        this.code = null;
                    }
                    if( matcher.groupCount() > 1 ){
                        this.content = matcher.group(2);
                    }else{
                        this.content = null;
                    }

                } else {
                    LOG.info("ctor: NOT matches");
                    this.code = null;
                    this.content = null;
                }
            }
        }

        public String getCode() {
            return code;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return String.format("ActiaResponseParser[code=%s, content=%s]", code, content);
        }
    }


}
