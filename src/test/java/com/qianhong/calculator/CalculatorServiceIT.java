package com.qianhong.calculator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class CalculatorServiceIT {

    @Test
    public void testPing() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:9999/calculator/api/calculator/ping");
        HttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertThat(EntityUtils.toString(response.getEntity()), containsString("Welcome to Java Maven Calculator Web App!!!"));
    }

    @Test
    public void testAdd() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:9999/calculator/api/calculator/add?x=8&y=26");
        HttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertThat(EntityUtils.toString(response.getEntity()), containsString("\"result\":34"));
    }

    @Test
    public void testSub() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:9999/calculator/api/calculator/sub?x=12&y=8");
        HttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertThat(EntityUtils.toString(response.getEntity()), containsString("\"result\":4"));
    }

    @Test
    public void testMul() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:9999/calculator/api/calculator/mul?x=11&y=8");
        HttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertThat(EntityUtils.toString(response.getEntity()), containsString("\"result\":88"));
    }

    @Test
    public void testDiv() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:9999/calculator/api/calculator/div?x=12&y=12");
        HttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertThat(EntityUtils.toString(response.getEntity()), containsString("\"result\":1"));
    }
}
