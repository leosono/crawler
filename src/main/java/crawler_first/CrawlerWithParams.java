package crawler_first;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerWithParams{
    public static void main(String[] args) throws Exception {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("so","java"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params,"UTF-8");
        httpPost.setEntity(urlEncodedFormEntity);
        System.out.println(httpPost);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
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
