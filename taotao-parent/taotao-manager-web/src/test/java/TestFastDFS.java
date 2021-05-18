import com.taotao.utils.FastDFSClient;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import java.io.File;

public class TestFastDFS {
    @Test
    public void testUploadFile() throws Exception{
        //1.向工程添加jar包
        //2.创建一个配置文件，配置tracker服务器地址
        //3.加载配置文件
        ClientGlobal.init("E:\\IDEA\\IDEAproject\\taotao430\\taotao-parent\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
        //4.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //5.使用TrackerClient对象获得trackerserver对象。
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        //6.创建一个StorageServer的引用，我们用null就可以
        StorageServer storageServer = null;
        //7.创建一个StorageClient对象。trackerserver、StorageServer两个参数
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //8.使用StorageClient对象上传文件
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", "2");
        metaList[1] = new NameValuePair("createTime", "2017-04-13 16:01:00");
        metaList[2] = new NameValuePair("createUser", "陈永鹏");
        String[] upload_files = storageClient.upload_file("C:\\Users\\song\\Pictures\\iCloud Photos\\Photos\\IMG_0039.jpg", "jpg", metaList);
        for(String file : upload_files){
            System.out.println(file);
        }
    }
    @Test
    public void testFastDFSClient() throws Exception{
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\IDEA\\IDEAproject\\taotao430\\taotao-parent\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");

        String imgPath = fastDFSClient.uploadFile("C:\\Users\\song\\Pictures\\iCloud Photos\\Photos\\IMG_0041.jpg");

        System.out.println(imgPath);
    }
    @Test
    public void getFile() throws Exception {
        // get file list where the path has
        String path = "E:\\QQ\\数据\\1154867045\\FileRecv\\淘淘商城开发课前资料\\day08（淘淘商城07 - Solr入门以及搜索系统实现）\\京东商品爬虫\\test";
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();

        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                // only take file name
                //System.out.println(array[i].getName());
                // take file path and name
                //System.out.println("#####" + array[i]);
                // take file path and name
                //System.out.println(array[i].getPath());
                FastDFSClient fastDFSClient = new FastDFSClient("E:\\IDEA\\IDEAproject\\taotao430\\taotao-parent\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");

                String imgPath = fastDFSClient.uploadFile(array[i].getPath());
                System.out.println(imgPath);
                String newpath = "http://192.168.30.130:8888/" + imgPath;
                String oldpath = "http://image.taotao.com/jd/" + array[i].getName();
                System.out.println(newpath);
                System.out.println(oldpath);
            }
        }
    }

}
