package com.yogurt.vienna.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class NewsLetterService {

    /**
     * 실거래가 API 사이트 : https://www.data.go.kr/data/15058747/openapi.do
     * */


    @Value("${from}")
    private String fromEmail;

    @Value("${to}")
    private String toEmail;

    @Value("${APT_TRADE_ENCODING_KEY}")
    private String AptTradeEncodingKey;

    @Value("${APT_TRADE_DECODING_KEY}")
    private String AptTradeDecodingKey;

    public void sendNewsLetter(String sendGridApiKey) {

        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        String subject = "Sending news letter for test";
        Content content = new Content("text/plain", "easy to do anywhere");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("SendGrid IO Exception");
            e.printStackTrace();
        }

    }

    public String getAptPrice() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        //url
        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade");
        urlBuilder.append("?"+ URLEncoder.encode("ServiceKey", "UTF-8")+"="+AptTradeEncodingKey);
        urlBuilder.append("&"+URLEncoder.encode("LAWD_CD","UTF-8")+"="+URLEncoder.encode("11110", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("202101", "UTF-8")); /*월 단위 신고자료*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code : "+conn.getResponseCode());

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        parseXmlData(sb.toString());


        return "";


    }

    public void parseXmlData(String sb) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {



        /** 참고자료 : https://pangtrue.tistory.com/222 */
        /** 참고자료 : https://jeong-pro.tistory.com/144 */

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        InputSource is = new InputSource(new StringReader((sb.toString())));

        Document document = documentBuilder.parse(is);

//        document.getDocumentElement().normalize();
//
//        NodeList aptItemSet = document.getElementsByTagName("items");

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        // XPathExpression expr = xpath.compile("/response/body/items/item");
        XPathExpression expr = xpath.compile("//items/item");
        NodeList aptItemSet = (NodeList) expr.evaluate(document, XPathConstants.NODESET);


        for(int i=0; i<aptItemSet.getLength(); i++){

            NodeList aptItem = aptItemSet.item(i).getChildNodes();

            for(int j=0; j<aptItem.getLength(); j++){

                Node node = aptItem.item(j);

                System.out.println(node.getNodeName());

                //DTO 객체 만들어서 담기

            }

        }



    }



}
