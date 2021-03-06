package com.yogurt.vienna.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.yogurt.vienna.dto.News.AptInfoDTO;
import com.yogurt.vienna.entity.User;
import com.yogurt.vienna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsLetterService {

    /**
     * 실거래가 API 사이트 : https://www.data.go.kr/data/15058747/openapi.do
     * */

    @Autowired
    UserRepository userRepository;

    @Value("${from}")
    private String fromEmail;

    @Value("${to}")
    private String toEmail;

    @Value("${APT_TRADE_ENCODING_KEY}")
    private String AptTradeEncodingKey;

    @Value("${APT_TRADE_DECODING_KEY}")
    private String AptTradeDecodingKey;

    public void sendNewsLetter(String sendGridApiKey) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {

        //for문으로 입력된 이메일에 전송하기
        List<User> userList = (List<User>) userRepository.findAll();

        for(int i=0; i< userList.size(); i++){

            Email from = new Email(fromEmail);
            Email to = new Email(userList.get(i).getEmail());
            String subject = "Sending news letter for test";
            List<AptInfoDTO> aptInfoDTOList = this.getAptPrice();
            Content content = new Content("text/plain", aptInfoDTOList.toString());
            Mail mail = new Mail(from, subject, to, content);
            mail.setTemplateId("d-4a1e12044e6a4c398091c057511f0b69");

            // 템플릿 적용을 어떻게 하지??
            // 오늘 이메일 템플릿 적용까지 한다
            // 메일 템플릿 세팅까지 했는데 CSS가 안먹는다.





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

                System.out.println(userList.get(i).getEmail() + " 뉴스레터 전송완료");
            } catch (IOException e) {
                System.out.println("SendGrid IO Exception");
                e.printStackTrace();
            }

        }
    }

    public List<AptInfoDTO> getAptPrice() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

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

        List<AptInfoDTO> aptInfoDTOList = parseXmlData(sb.toString());


        return aptInfoDTOList;


    }

    public List<AptInfoDTO> parseXmlData(String sb) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

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

        List<AptInfoDTO> aptInfoDTOList = new ArrayList<>();

        for(int i=0; i<aptItemSet.getLength(); i++){

            NodeList aptItem = aptItemSet.item(i).getChildNodes();
            AptInfoDTO aptInfo = new AptInfoDTO();
            StringBuffer trnDate = new StringBuffer();

            for(int j=0; j<aptItem.getLength(); j++){


                Node node = aptItem.item(j);

                String nodeName = node.getNodeName();
                String nodeValue = node.getTextContent();

                switch(nodeName) {

                    case "거래금액":
                        String aptPrice = nodeValue.replace(" ", "");
                        aptInfo.setAptPrice(aptPrice);
                        break;
                    case "건축년도":
                        aptInfo.setConstructYear(nodeValue);
                        break;
                    case "년":
                        aptInfo.setTrnYear(nodeValue);
                        break;
                    case "월":
                        aptInfo.setTrnMonth(nodeValue);
                        break;
                    case "일":
                        aptInfo.setTrnDay(nodeValue);
                        break;
                    case "전용면적":
                        aptInfo.setJeonyong(nodeValue);
                        break;
                    case "지번":
                        aptInfo.setJibeon(nodeValue);
                        break;
                    case "지역코드":
                        aptInfo.setAreaCode(nodeValue);
                        break;
                    case "층":
                        aptInfo.setFloor(nodeValue);
                        break;
                    default:

                        break;
                }

            }

            trnDate.append(aptInfo.getTrnYear());
            trnDate.append(aptInfo.getTrnMonth());

            if(aptInfo.getTrnDay().length() == 1){ //일자가 1자리일 경우를 위해서 앞에 0을 붙인다.
                trnDate.append("0");
            }
            trnDate.append(aptInfo.getTrnDay());
            aptInfo.setTrnDate(trnDate.toString()); //년 + 월 + 일 합쳐서 거래일자 생성

            aptInfoDTOList.add(aptInfo);

       }

        /** 아파트정보 구조체 출력 */
        for(int j=0; j<aptInfoDTOList.size(); j++){
            System.out.println("가격 : "+aptInfoDTOList.get(j).getAptPrice()
                                + ", 건축년도 : "+aptInfoDTOList.get(j).getConstructYear()
                                + ", 거래일자 : "+aptInfoDTOList.get(j).getTrnDate()
                                + ", 전용면적 : "+aptInfoDTOList.get(j).getJeonyong()
                                + ", 지번 : "+aptInfoDTOList.get(j).getJibeon()
                                + ", 지역코드 : "+aptInfoDTOList.get(j).getAreaCode()
                                + ", 층 : "+aptInfoDTOList.get(j).getFloor());
        }

        return aptInfoDTOList;

    }



}
