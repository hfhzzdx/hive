package Hbase;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Hbase_student {

        //获取配置
        Configuration conf = HBaseConfiguration.create();
        //微博内容表的  表名
       private static final byte[] TABLE_CONTENT = Bytes.toBytes("ns_weibo:content");
       //用户关系表 表名
     private static final byte[] TABLE_RELATION   = Bytes.toBytes("ns_weibo:relation");
       //微博收件箱表的表名
     private static final byte[] TABLE_INBOX  = Bytes.toBytes("ns_weibo:inbox");
    /**
     * 初始化命名空间
     *
     */

public void initNamespace(){
    HBaseAdmin admin =null ;
    try {
        Connection connection = ConnectionFactory.createConnection(conf);
        admin = (HBaseAdmin) connection.getAdmin();
        //命名空间类似于关系型数据库中的 schema，可以想象成文件夹
        NamespaceDescriptor weibo = NamespaceDescriptor.create("ns_weibo")
                .addConfiguration("creator", "Jinji")
                .addConfiguration("create_time", System.currentTimeMillis() + "")
                .build();


        admin.createNamespace(weibo);


    } catch (IOException e) {
        e.printStackTrace();
    }
    finally {

        if(null != admin){
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

 public void createTableContent(){
    Admin admin =null;
    Connection conn = null;
     try {
         conn=ConnectionFactory.createConnection();
         admin=conn.getAdmin();
         HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(TABLE_CONTENT));
         HColumnDescriptor info = new HColumnDescriptor(Bytes.toBytes("info"));
         //设置块缓存
         info.setBlockCacheEnabled(true);
         //设置块缓存的大小
         info.setBlocksize(2097152);
         //设置压缩方式
         info.setCompressionType(Compression.Algorithm.SNAPPY);
         //设置版本确界
         info.setMaxVersions(1);
         info.setMinVersions(1);
//添加列族
         descriptor.addFamily(info);
         //建表
         admin.createTable(descriptor);


     } catch (IOException e) {
         e.printStackTrace();
     } finally {
         if(null != admin){
             try {
                 admin.close();
                 conn.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
 }

 public void createTableRelation(){
    HBaseAdmin admin =null;
     try {
         Connection connection = ConnectionFactory.createConnection();
         admin= (HBaseAdmin) connection.getAdmin();
         HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_RELATION));
         HColumnDescriptor attends = new HColumnDescriptor(Bytes.toBytes("attends"));
         attends.setBlockCacheEnabled(true);
         attends.setBlocksize(2097152);
         attends.setMaxVersions(1);
         attends.setMinVersions(1);
         attends.setCompressionType(Compression.Algorithm.SNAPPY);



     } catch (IOException e) {
         e.printStackTrace();
     } finally {
     }
 }

}
