package com.chaoxing.jw.email.upload;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.cxyd.tools.io.yun.YunFileUtils;
import com.cxyd.tools.io.yun.YunImageUtils;
import com.cxyd.tools.io.yun.YunParam;
import com.cxyd.tools.io.yun.YunUtils;
import com.cxyd.tools.utils.IpUtil;
import com.cxyd.tools.web.http.HttpClientUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.RowSet;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @version 1.0
 * @Auther: hemingyang
 * @Date: 2021/11/23 18:38
 * @Description:
 */
public class FileUploadUtils {

    public static YunParam getCurentYunParam(HttpServletRequest request, String fileName) throws FileUploadException {
        // 上传人passportId（必传）
        Integer puid = 21418053;
        // 用户没有puid的取系统设置 系统设置没有 就抛出异常

        Integer prdid = 4;
        // 上传人单位id，缺省：学习通：1385，移图：4965
        Integer fid = 4;
        // 获取当前系统设置的fid
        String uploadtype = "normal";

        YunParam yunParam = new YunParam(fileName, puid, prdid, fid, "10.0.250.152", uploadtype);
        return yunParam;
    }

}
