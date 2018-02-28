package com.gantzgulch.tools.httpclient;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.protocol.HttpClientContext;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.httpclient.impl.GGHttpClientImpl;
import com.gantzgulch.tools.json.GGJsonReader;
import com.gantzgulch.tools.json.GGJsonReaders;
import com.gantzgulch.tools.json.GGJsonWriter;
import com.gantzgulch.tools.json.GGJsonWriters;
import com.gantzgulch.tools.reqres.domain.DataResponse;
import com.gantzgulch.tools.reqres.domain.PagedSearchResponse;
import com.gantzgulch.tools.reqres.domain.User;

public class GGHttpJsonClientTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGHttpJsonClientTest.class);

    private final GGHttpClient httpClient = new GGHttpClientImpl(null, 30000, 30000);

    private final GGJsonReader reader = GGJsonReaders.STRICT;
    private final GGJsonWriter writer = GGJsonWriters.PRETTY;

    private final HttpClientContext ctx = new HttpClientContext();
    
    @Test
    public void getUsers_withTypeReference() throws IOException {
        
        final URI uri = URI.create("https://reqres.in/api/users?page=1");

        final TypeReference<PagedSearchResponse<User>> typeRef = new TypeReference<PagedSearchResponse<User>>() {};
        
        final PagedSearchResponse<User> result = httpClient.jsonClient(reader, writer).get(uri, null, ctx, typeRef); 

        LOG.debug("result: %s", result);

        assertThat(result, notNullValue());
        assertThat(result.getPage(), equalTo(1));
        
        assertThat(result.getData().get(0).getFirstName(), equalTo("George"));
        
    }
   
    @Test
    public void getUsers_withJavaType() throws IOException {
        
        final URI uri = URI.create("https://reqres.in/api/users?page=1");

        final JavaType javaType = reader.getTypeFactory().constructParametricType(PagedSearchResponse.class, User.class);
                
        final PagedSearchResponse<User> result = httpClient.jsonClient(reader, writer).get(uri, null, ctx, javaType); 

        LOG.debug("result: %s", result);

        assertThat(result, notNullValue());
        assertThat(result.getPage(), equalTo(1));
        
        assertThat(result.getData().get(0).getFirstName(), equalTo("George"));
        
        
    }

    @Test
    public void getUser_withTypeReference() throws IOException {
        
        final URI uri = URI.create("https://reqres.in/api/users/2");

        final TypeReference<DataResponse<User>> typeRef = new TypeReference<DataResponse<User>>() {};
        
        final DataResponse<User> result = httpClient.jsonClient(reader, writer).get(uri, null, ctx, typeRef); 

        LOG.debug("result: %s", result);

        assertThat(result, notNullValue());
        assertThat(result.getData(), notNullValue());
        assertThat(result.getData().getId(), equalTo(2));
        assertThat(result.getData().getFirstName(), equalTo("Janet"));
    }
   
    @Test
    public void getUser_withJavaType() throws IOException {
        
        final URI uri = URI.create("https://reqres.in/api/users/2");

        final JavaType javaType = reader.getTypeFactory().constructParametricType(DataResponse.class, User.class);
        
        final DataResponse<User> result = httpClient.jsonClient(reader, writer).get(uri, null, ctx, javaType); 

        LOG.debug("result: %s", result);

        assertThat(result, notNullValue());
        assertThat(result.getData(), notNullValue());
        assertThat(result.getData().getId(), equalTo(2));
        assertThat(result.getData().getFirstName(), equalTo("Janet"));

    }
   
}
