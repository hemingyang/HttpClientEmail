package com.chaoxing.jw.email.upload;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cxyd.tools.io.yun.YunFileUtils;
import com.cxyd.tools.io.yun.YunParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
/**
 * Description: 
 * @auther: hemingyang
 * @param: 
 * @return: 
 * @date: 2022/5/11 17:06
 */
public class DownloadFileHttpCilent {

    public String download() {
        HttpClient client = new HttpClient();
        GetMethod get = null;
        FileOutputStream output = null;
        try {
            String format = DateUtil.format(new Date(), "yyyyMMdd");
            get = new GetMethod("http://120.92.71.163:9091/gzlgxy/GZLGXY" + format + ".dmp.tar.gz");
            String authHeader = "Basic " + new String(Base64.encodeBase64(String.format("%s:%s", "admin", "admin@123@qwer").getBytes()));
            get.setRequestHeader("Authorization", authHeader);
            int i = client.executeMethod(get);
            if (i == 200) {
                System.out.println("The response value of token:" + get.getResponseHeader("token"));
                File storeFile = new File("GZLGXY"+ format + ".dmp.tar.gz");
                output = new FileOutputStream(storeFile);
                output.write(get.getResponseBody());
                YunParam yunParam = FileUploadUtils.getCurentYunParam(null , "GZLGXY"+ format + ".dmp.tar.gz");
                com.alibaba.fastjson.JSONObject json = YunFileUtils.upload(storeFile, yunParam);
                String objectid = json.getString("objectid");
                String result = HttpUtil.get("http://cs.ananas.chaoxing.com/status/" + objectid);
                JSONObject jsonObject = JSONUtil.parseObj(result);
                Object url = jsonObject.get("download");
                return url.toString();
            } else {
                System.out.println("DownLoad file occurs exception, the error code is :" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            get.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return null;
    }
}

