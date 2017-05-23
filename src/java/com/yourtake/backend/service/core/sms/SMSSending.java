/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.backend.service.core.sms;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class SMSSending extends Thread {

    private String number;
    private String data;
    
    public SMSSending(String number, String data) {
        this.number=number;
        this.data=data;
    }
    
     @Override
    public void run() {
        try
        {
            String url="http://trans.businesskarma.in/api/web2sms.php";
                  CloseableHttpClient httpclient = HttpClients.createDefault();
                                    HttpGet httpGet = new HttpGet(new URIBuilder(url).
                                            addParameter("workingkey", "A1884de79d91d7c4d9ee90cf9eb0803b7").
                                            addParameter("sender", "BKarma").
                                            addParameter("to", "91"+number).
                                            addParameter("message", data).
                                            addParameter("user", "antdemo").
                                            addParameter("password", "Hardik@34").
                                            build());
                                    
                                    
                                    HttpEntity entity = httpclient.execute(httpGet).getEntity();

                                    String responseText = EntityUtils.toString(entity, "UTF-8");
               
                
        }
		
        catch(Exception e) 
        {
		System.err.println("Error in sending message "+e);		
		
        }

    }
    
}
