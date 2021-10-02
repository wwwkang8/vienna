package com.yogurt.vienna.service;

import com.yogurt.vienna.dto.AptDTO;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Service
public class AptNewsService {

    public List<AptDTO> getAptData() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        List<AptDTO> aptDTOList = new ArrayList<>();

        try{
            /* 부동산 API연결 로직*/
            StringBuilder sb = getRealEstateInfo();

            /* json 형태로 응답받을 시 사용 */
//            Gson gson = new Gson();
//            Type aptListType = new TypeToken<ArrayList<AptDTO>>(){}.getType();
//            aptDTOList = gson.fromJson(sb.toString(), String );

            /* xml 형태로 응답받을 경우 */
            InputSource is = new InputSource(new StringReader((sb.toString())));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            // XPathExpression expr = xpath.compile("/response/body/items/item");
            XPathExpression expr = xpath.compile("//items/item");

            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                aptDTOList.add(new AptDTO(nodeList.item(i).getNodeName(),nodeList.item(i).getNodeValue()));

                NodeList child = nodeList.item(i).getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    System.out.println("현재 노드 이름 : " + node.getNodeName());
                    System.out.println("현재 노드 타입 : " + node.getNodeType());
                    System.out.println("현재 노드 값 : " + node.getTextContent());
                    System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
                    System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
                    System.out.println("");
                }
            }


            return aptDTOList;

        } catch(XPathExpressionException e){
            System.out.println("Error occur because of XPathExpressionException!");
            e.printStackTrace();
        } catch(Exception e){
            System.out.println("Error occur because of api connection to get real estate!");
            e.printStackTrace();
        }

        return null;
    }


    /*-----------------------------------------------*/
    /* 공공데이터 data.go.kr 에서 부동산 정보 가져오는 함수 */
    /*-----------------------------------------------*/
    private StringBuilder getRealEstateInfo() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=wqloLv%2FDJVI5N4ICLMIF5GtNFxfVYFxD06KsOcuzasSy7w6ofQS95scfQg4RcmyqXQKXRcHSN3AigQqinPe%2BFQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("wqloLv%2FDJVI5N4ICLMIF5GtNFxfVYFxD06KsOcuzasSy7w6ofQS95scfQg4RcmyqXQKXRcHSN3AigQqinPe%2BFQ%3D%3D", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode("11110", "UTF-8")); /*지역코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("202105", "UTF-8")); /*계약월*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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

        return sb;
    }

}
