package test;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Base64Util;
import util.FileUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: xcq
 * Date: 2021/9/2 3:36 下午
 * FileName: test.TestOCR
 */
public class TestOCR {

    private static final Logger logger = LoggerFactory.getLogger(TestOCR.class);

    static final String ACCESS_TOKEN = "24.1673e197dd93b2074386d8f1a0988c09.2592000.1633160541.282335-24790632";

    public static void main(String[] args) throws Exception {
        //        test.TestOCR ocr = new test.TestOCR();
        //        ocr.getAccessToken();

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
//        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis";
        // 本地文件路径
        String filePath = "/Users/debug/Desktop/备授课-备课页.pdf";
        byte[] fileBts = FileUtil.readFileByBytes(filePath);
        String fileStr = Base64Util.encode(fileBts);
        String pdf_file = URLEncoder.encode(fileStr, "UTF-8");

        String param = "pdf_file=" + pdf_file + "&pdf_file_num=3";

        String result = util.HttpUtil.post(url, ACCESS_TOKEN, param);

        JSONObject jo = JSONObject.parseObject(result);
        JSONArray ja = jo.getJSONArray("words_result");
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jsonObject = ja.getJSONObject(i);
            String words = jsonObject.getString("words");
            System.out.println(words);
        }
    }

    void getAccessToken() {

        String apiKey = "1LztoIEeGpaGtr6BdZnlAzXU";
        String secretKey = "gx3i2fBDzqjTqPwTPFWIRgunFumxtyGM";

        String url = "https://aip.baidubce.com/oauth/2.0/token";

        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", apiKey);
        params.put("client_secret", secretKey);

        String response = HttpUtil.get(url, params);
        logger.info("resp:[{}]", response);

    }

}
