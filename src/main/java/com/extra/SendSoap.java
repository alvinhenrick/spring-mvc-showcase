package com.extra;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.net.URL;

/**
 * Created by ahenrick on 6/11/14.
 */
public class SendSoap {

  public static void main(String args[]) throws Exception{

    String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
     + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
     + "<soap:Body>"
     + "<ConvertTemp xmlns=\"http://www.webserviceX.NET/\">"
     + "<Temperature>30.0</Temperature>"
     + "<FromUnit>degreeCelsius</FromUnit>"
     + "<ToUnit>degreeFahrenheit</ToUnit>"
     + "</ConvertTemp>"
     + "</soap:Body>"
     + "</soap:Envelope>";


    SOAPMessage sm = MessageFactory.newInstance().createMessage(null,new ByteArrayInputStream(
        message.getBytes()));

    //MimeHeaders mimeHeader = sm.getMimeHeaders();
    //mimeHeader.addHeader("SOAPAction", "http://www.webserviceX.NET/ConvertTemp");

    SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
    SOAPConnection connection = sfc.createConnection();

    URL endpoint = new URL("http://www.webservicex.net/ConvertTemperature.asmx");
    SOAPMessage response = connection.call(sm, endpoint);
    response.writeTo(System.out);

  }
}
