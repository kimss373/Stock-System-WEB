import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import kr.ac.kopo.util.ConnectionFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class stockMain {

	public static void main(String[] args) {
		
		for (int k = 1; k < 340; k++) {
		try {
            // API URL과 파라미터 설정
            String baseUrl = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
            String serviceKey = "gzYzrIAgEUaiBPk2XZIEsCi57Fle5SFV37Tr3hH2TCQa9zYguPU7rHrBfoBN8kHaTXTS+YExx37Lh4vUhg4gsw==";
            String numOfRows = "50";
            String pageNo = k + "";
            String resultType = "xml";
            String beginBasDt = "20240703";
            String endBasDt = "20240712";

            // URL에 파라미터 추가
            String apiUrl = baseUrl + "?"
                    + "serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
                    + "&numOfRows=" + URLEncoder.encode(numOfRows, "UTF-8")
                    + "&pageNo=" + URLEncoder.encode(pageNo, "UTF-8")
                    + "&resultType=" + URLEncoder.encode(resultType, "UTF-8")
                    + "&beginBasDt=" + URLEncoder.encode(beginBasDt, "UTF-8")
                    + "&endBasDt=" + URLEncoder.encode(endBasDt, "UTF-8");

            // URL 객체 생성
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HTTP 메소드 설정
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 응답 읽기
            BufferedReader in;
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else { // 에러 응답
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 응답 출력
            System.out.println(response.toString());
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response.toString()));
            Document doc = builder.parse(is);
            
            
         // <item> 태그들을 선택하여 출력
            NodeList itemList = doc.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
                    // sql insert 시작
                    StringBuilder sql = new StringBuilder();
                    sql.append(" INSERT INTO STOCK ");
                    sql.append(" VALUES(seq_tbl_stock_stock_cd.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
                    
                    try (Connection connF = new ConnectionFactory().getConnection();
            				PreparedStatement pstmt = connF.prepareStatement(sql.toString());) {

            			pstmt.setString(1, getTagValue("basDt", element));
            			pstmt.setString(2, getTagValue("srtnCd", element));
            			pstmt.setString(3, getTagValue("isinCd", element));
            			pstmt.setString(4, getTagValue("itmsNm", element));
            			pstmt.setString(5, getTagValue("mrktCtg", element));
            			pstmt.setString(6, getTagValue("clpr", element));
            			pstmt.setString(7, getTagValue("vs", element));
            			pstmt.setString(8, getTagValue("fltRt", element));
            			pstmt.setString(9, getTagValue("mkp", element));
            			pstmt.setString(10, getTagValue("hipr", element));
            			pstmt.setString(11, getTagValue("lopr", element));
            			pstmt.setString(12, getTagValue("trqu", element));
            			pstmt.setString(13, getTagValue("trPrc", element));
            			pstmt.setString(14, getTagValue("lstgStCnt", element));
            			pstmt.setString(15, getTagValue("mrktTotAmt", element));
            			pstmt.executeUpdate();

            		} catch (Exception e) {
            			e.printStackTrace();
            		}
//                    System.out.println("Item " + (i+1) + ":");
//                    System.out.println("  basDt: " + getTagValue("basDt", element));
//                    System.out.println("  srtnCd: " + getTagValue("srtnCd", element));
//                    System.out.println("  isinCd: " + getTagValue("isinCd", element));
//                    System.out.println("  itmsNm: " + getTagValue("itmsNm", element));
//                    System.out.println("  mrktCtg: " + getTagValue("mrktCtg", element));
//                    System.out.println("  clpr: " + getTagValue("clpr", element));
//                    System.out.println("  vs: " + getTagValue("vs", element));
//                    System.out.println("  fltRt: " + getTagValue("fltRt", element));
//                    System.out.println("  mkp: " + getTagValue("mkp", element));
//                    System.out.println("  hipr: " + getTagValue("hipr", element));
//                    System.out.println("  lopr: " + getTagValue("lopr", element));
//                    System.out.println("  trqu: " + getTagValue("trqu", element));
//                    System.out.println("  trPrc: " + getTagValue("trPrc", element));
//                    System.out.println("  lstgStCnt: " + getTagValue("lstgStCnt", element));
//                    System.out.println("  mrktTotAmt: " + getTagValue("mrktTotAmt", element));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		}
	}
	
	// 특정 태그의 값을 가져오는 메소드
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
