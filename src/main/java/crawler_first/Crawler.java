package crawler_first;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class Crawler {
    public static void main(String[] args) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.itcast.com");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(content.length());
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response == null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
