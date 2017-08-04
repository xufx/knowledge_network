package jsonTest;
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.GetDocumentResponse;
import com.baidubce.services.doc.model.ReadDocumentResponse;
/**
 * Created by xufuxiu on 2017/8/3.
 */
public class GetDocumentTest
{//获取百度云文档的相关信息
    public static void main(String[] args)
    {/*使用默认域名作为文档服务的服务地址*/
        String ACCESS_KEY_ID = "b591db87eae7435c9b4ac9aac0f195d6";//百度云账户的AccessKeyID
        String SECRET_ACCESS_KEY = "cd8b4e08ec8743b1aed9ed7413517484";//百度云账户的Secret Access Key

        //String ENDPOINT = "http://doc.bj.baidubce.com";自己定制的域名
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        /*配置认证密钥和服务器信息*/
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        // 让客户端使用代理访问文档服务，配置代理为本地8080端口
        config.setProxyHost("127.0.0.1");
        config.setProxyPort(8080);

        DocClient client = new DocClient(config);

        GetDocumentTest g=new GetDocumentTest();
        g.readDocument(client,"doc-hhcsjpng1pwdzs9");
    }
    public void readDocument(DocClient client, String documentId) {
        ReadDocumentResponse resp = client.readDocument(documentId);
        System.out.println("documentId: " + resp.getDocumentId());
        System.out.println("host: " + resp.getHost());
        System.out.println("token: " + resp.getToken());
    }
    /*documentId：百度云文档生成的*/
    public void getDocument(DocClient client, String documentId)
    {
        GetDocumentResponse resp = client.getDocument(documentId);
        System.out.println("documentId: " + resp.getDocumentId());
        System.out.println("title: " + resp.getTitle());
        System.out.println("format: " + resp.getFormat());
        System.out.println("status: " + resp.getStatus());
        if (resp.getStatus() == "PUBLISHED") {
            System.out.println("pageCount: " + resp.getPublishInfo().getPageCount());
            System.out.println("sizeInBytes: " + resp.getPublishInfo().getSizeInBytes());
            System.out.println("coverUrl: " + resp.getPublishInfo().getCoverUrl());
            System.out.println("publishTime: " + resp.getPublishInfo().getPublishTime());
        }
        if (resp.getStatus() == "UPLOADING") {
            System.out.println("bucket: " + resp.getUploadInfo().getBucket());
            System.out.println("object: " + resp.getUploadInfo().getObject());
            //System.out.println("bosEndpoint: " + resp.getPublishInfo().getBosEndpoint());
        }
        if (resp.getStatus() == "FAILED") {
            System.out.println("errorCode: " + resp.getError().getCode());
            System.out.println("errorMessage: " + resp.getError().getMessage());
        }
        System.out.println("notification: " + resp.getNotification());
        System.out.println("createTime: " + resp.getCreateTime());
    }
}
