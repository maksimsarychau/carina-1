/*******************************************************************************
 * Copyright 2013-2018 QaProSoft (http://www.qaprosoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qaprosoft.carina.browsermobproxy;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.Configuration.Parameter;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.proxy.SystemProxy;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.proxy.CaptureType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowserMobTest {
    private static String header = "my_header";
    private static String headerValue = "my_value";
    private static String testUrl = "https://ci.qaprosoft.com";
    private static String filterKey = "</html>";
    private static String requestMethod = "GET";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        R.CONFIG.put("browsermob_proxy", "true");
        R.CONFIG.put("browsermob_port", "0");
        R.CONFIG.put("proxy_set_to_system", "true");
        R.CONFIG.put("browsermob_disabled_mitm", "false");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        ProxyPool.stopAllProxies();
    }

    @Test
    public void testIsBrowserModStarted() {
        initialize();
        Assert.assertTrue(ProxyPool.getProxy().isStarted(), "BrowserMobProxy is not started!");
    }

    @Test
    public void testBrowserModProxySystemIntegration() {
        initialize();
        Assert.assertEquals(Configuration.get(Parameter.PROXY_HOST), System.getProperty("http.proxyHost"));
        Assert.assertEquals(Configuration.get(Parameter.PROXY_PORT), System.getProperty("http.proxyPort"));
    }

    @Test
    public void testBrowserModProxyHeader() {
        initialize();
        Map<String, String> headers = ProxyPool.getProxy().getAllHeaders();
        Assert.assertTrue(headers.containsKey(header), "There is no custom header: " + header);
        Assert.assertTrue(headers.get(header).equals(headerValue), "There is no custom header value: " + headerValue);

        ProxyPool.getProxy().removeHeader(header);
        if (ProxyPool.getProxy().getAllHeaders().size() != 0) {
            Assert.fail("Custom header was not removed: " + header);
        }
    }

    @Test
    public void testBrowserModProxyRegisteration() {
        BrowserMobProxy proxy = ProxyPool.startProxy();
        ProxyPool.registerProxy(proxy);
        Assert.assertTrue(ProxyPool.isProxyRegistered(), "Proxy wasn't registered in ProxyPool!");
        ProxyPool.stopAllProxies();
        Assert.assertFalse(ProxyPool.isProxyRegistered(), "Proxy wasn't stopped!");
    }

/*    @Test
    public void testBrowserModProxyResponseFiltering() {
        List<String> content = new ArrayList<>();

        ProxyPool.setupBrowserMobProxy();
        SystemProxy.setupProxy();
        BrowserMobProxy proxy = ProxyPool.getProxy();
        proxy.enableHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        proxy.newHar();

        proxy.addResponseFilter((request, contents, messageInfo) -> {
            if (contents.getTextContents().contains(filterKey)) {
                content.add(contents.getTextContents());
            }
        });

        makeHttpRequest(testUrl, requestMethod);

        Assert.assertNotNull(proxy.getHar(), "Har is unexpectedly null!");
        Assert.assertEquals(content.size(), 1,"Filtered response number is not as expected!");
        Assert.assertTrue(content.get(0).contains(filterKey), "Response doesn't contain expected key!");
    }*/

    private void initialize() {
        ProxyPool.setupBrowserMobProxy();
        SystemProxy.setupProxy();

        BrowserMobProxy proxy = ProxyPool.getProxy();
        proxy.addHeader(header, headerValue);
    }

    private void makeHttpRequest(String requestUrl, String requestMethod) {
        URL url;
        HttpURLConnection con;
        Integer httpResponseStatus;
        try {
            url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(requestMethod);
            httpResponseStatus = con.getResponseCode();
            Assert.assertTrue(httpResponseStatus < 399, "Response code is not as expected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
