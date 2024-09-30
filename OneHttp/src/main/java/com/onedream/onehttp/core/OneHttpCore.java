package com.onedream.onehttp.core;

import com.onedream.onehttp.bean.OneHttpHeaders;
import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class OneHttpCore {

    public static OneHttpResponse request(OneHttpRequest request) throws Exception {
        InputStream inputStream;
        URL url = new URL(request.getUrl()); //创建URL类对象，抛异常
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(request.getMethod());  //声明请求方式
        conn.setConnectTimeout(request.getConnectTimeout()); //设置连接超时
        //设置头部
        OneHttpHeaders headers = request.headers();
        if(null != headers){
            for (int i = 0, size = headers.size(); i < size; i++) {
                conn.setRequestProperty(headers.name(i), headers.value(i));
            }
        }
        //
        if (conn.getResponseCode() == 200) {
            inputStream = conn.getInputStream(); //得到服务端传回来的数据，相对客户为输入流
        } else {
            inputStream = conn.getErrorStream(); //得到服务端传回来的数据，相对客户为输入流
        }
        OneHttpResponse response = generator(request, conn, inputStream);
        closeQuietly(inputStream);
        return response;
    }

    private static OneHttpResponse generator(OneHttpRequest request, HttpURLConnection conn, InputStream inputStream) throws IOException {
        OneHttpResponse.Builder builder = new OneHttpResponse.Builder();
        byte[] data = readInputStream(inputStream);
        //
        builder.request(request);
        builder.code(conn.getResponseCode());
        builder.headers(conn.getHeaderFields());
        builder.data(data);
        builder.message(conn.getResponseMessage());
        //
        return builder.build();
    }


    //将输入流转化成byte[]类型
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];//声明缓冲区
        int length = -1; //定义读取默认的长度
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);

        }
        byteArrayOutputStream.close();//关闭输出流
        inputStream.close();//关闭输入流
        return byteArrayOutputStream.toByteArray();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {

            }
        }
    }
}
