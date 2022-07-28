package test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: xcq
 * Date: 2021/10/28 3:47 下午
 * FileName: PortExcel
 */
public class PortExcel {

    public static void main(String[] args) throws Exception {

        //funuo
//        File file = new File("/Users/debug/Desktop/funuo.json");
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        StringBuilder sb = new StringBuilder();
//        String lineTxt = null;
//        while ((lineTxt = bufferedReader.readLine()) != null) {
//            sb.append(lineTxt);
//        }
//
//        List<Address> list = new ArrayList<>();
//
//        JSONArray jsonArray = JSONArray.parseArray(sb.toString());
//        for (Object obj : jsonArray) {
//            JSONObject jo = (JSONObject) obj;
//
//            //省
//            String provinceId = jo.getString("provinceId");
//            String provinceName = jo.getString("provinceName");
//
//            //市
//            JSONArray citysJa = jo.getJSONArray("citys");
//            for (Object obj2 : citysJa) {
//                JSONObject jo2 = (JSONObject) obj2;
//                String cityId = jo2.getString("cityId");
//                String cityName = jo2.getString("cityName");
//
//                //地
//                JSONArray distJa = jo2.getJSONArray("distrcts");
//                for (Object obj3 : distJa) {
//                    JSONObject jo3 = (JSONObject) obj3;
//                    String districtId = jo3.getString("districtId");
//                    String districtName = jo3.getString("districtName");
//
//                    Address address = new Address(provinceId, provinceName, cityId, cityName, provinceId, provinceName, cityId, cityName, districtId, districtName);
//                    list.add(address);
//                }
//
//            }
//        }
//
//        List<List<String>> rows = new ArrayList<>();
//        List<String> title = CollUtil.newArrayList("归属地省级编码", "归属地省级中文", "归属地市级编码", "归属地市级中文", "配送地省级编码", "配送地省级中文", "配送地市级编码", "配送地市级中文", "配送地地级编码", "配送地地级中文");
//        rows.add(title);
//        for (Address add : list) {
//            List<String> row = CollUtil.newArrayList(add.getProvinceId(), add.getProvinceTag(), add.getCityId(), add.getCityTag(), add.getPostProvinceId(), add.getPostProvinceTag(), add.getPostCityId(), add.getPostCityTag(),
//                                                     add.getPostDistId(), add.getPostDistTag());
//            rows.add(row);
//        }
//
//        //输出excel
//        ExcelWriter excelWriter = ExcelUtil.getWriter("/Users/debug/Desktop/funuo.xlsx");
//        excelWriter.write(rows, true);
//        //关闭writer，释放内存
//        excelWriter.close();

        //lt配送地址
//        File file = new File("/Users/debug/Desktop/lt:getExpress.json");
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        StringBuilder sb = new StringBuilder();
//        String lineTxt = null;
//        while ((lineTxt = bufferedReader.readLine()) != null) {
//            sb.append(lineTxt);
//        }
//
//        List<Address> list = new ArrayList<>();
//
//        JSONObject fatherJo = JSONObject.parseObject(sb.toString());
//        JSONArray dataJa = fatherJo.getJSONArray("data");
//
//        JSONArray jsonArray = dataJa;
//        for (Object obj : jsonArray) {
//            JSONObject jo = (JSONObject) obj;
//
//            //省
//            String provinceId = jo.getString("provinceId");
//            String provinceName = jo.getString("provinceName");
//            String numProvinceId = jo.getString("numProvinceCode");
//
//            //市
//            JSONArray citysJa = jo.getJSONArray("citys");
//            for (Object obj2 : citysJa) {
//                JSONObject jo2 = (JSONObject) obj2;
//                String cityId = jo2.getString("cityId");
//                String cityName = jo2.getString("cityName");
//                String numCityId = jo2.getString("numCityCode");
//
//                //地
//                JSONArray distJa = jo2.getJSONArray("distrcts");
//                for (Object obj3 : distJa) {
//                    JSONObject jo3 = (JSONObject) obj3;
//                    String districtId = jo3.getString("districtId");
//                    String districtName = jo3.getString("districtName");
//
//                    Address address = new Address(numProvinceId, null, numCityId, null, provinceId, provinceName, cityId, cityName, districtId, districtName);
//                    list.add(address);
//                }
//
//            }
//        }
//
//        List<List<String>> rows = new ArrayList<>();
//        List<String> title = CollUtil.newArrayList("配送地省级编码", "配送地省级中文", "所属归属地省级编码", "配送地市级编码", "配送地市级中文", "所属归属地市级编码", "配送地地级编码", "配送地地级中文");
//        rows.add(title);
//        for (Address add : list) {
//            List<String> row = CollUtil.newArrayList(add.getPostProvinceId(), add.getPostProvinceTag(), add.getProvinceId(), add.getPostCityId(), add.getPostCityTag(),
//                                                     add.getCityId(), add.getPostDistId(), add.getPostDistTag());
//            rows.add(row);
//        }
//
//        //输出excel
//        ExcelWriter excelWriter = ExcelUtil.getWriter("/Users/debug/Desktop/联通配送地址.xlsx");
//        excelWriter.write(rows, true);
//        //关闭writer，释放内存
//        excelWriter.close();

        //lt归属地
        File file = new File("/Users/debug/Desktop/lt:getNumberAddress.json");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            sb.append(lineTxt);
        }

        List<Address> list = new ArrayList<>();

        JSONObject fatherJo = JSONObject.parseObject(sb.toString());
        JSONArray dataJa = fatherJo.getJSONArray("data");

        JSONArray jsonArray = dataJa;
        for (Object obj : jsonArray) {
            JSONObject jo = (JSONObject) obj;

            //省
            String provinceId = jo.getString("provinceId");
            String provinceName = jo.getString("provinceName");

            //市
            JSONArray citysJa = jo.getJSONArray("citys");
            for (Object obj2 : citysJa) {
                JSONObject jo2 = (JSONObject) obj2;
                String cityId = jo2.getString("cityId");
                String cityName = jo2.getString("cityName");

                Address address = new Address(provinceId, provinceName, cityId, cityName, null, null, null, null, null, null);
                list.add(address);

            }
        }

        List<List<String>> rows = new ArrayList<>();
        List<String> title = CollUtil.newArrayList("归属地省级编码", "归属地省级中文", "归属地市级编码", "归属地市级中文");
        rows.add(title);
        for (Address add : list) {
            List<String> row = CollUtil.newArrayList(add.getProvinceId(), add.getProvinceTag(), add.getCityId(), add.getCityTag());
            rows.add(row);
        }

        //输出excel
        ExcelWriter excelWriter = ExcelUtil.getWriter("/Users/debug/Desktop/联通归属地地址.xlsx");
        excelWriter.write(rows, true);
        //关闭writer，释放内存
        excelWriter.close();
    }

}

class Address {
    private String provinceId;
    private String provinceTag;
    private String cityId;
    private String cityTag;

    private String postProvinceId;
    private String postProvinceTag;
    private String postCityId;
    private String postCityTag;
    private String postDistId;
    private String postDistTag;

    public Address(String provinceId, String provinceTag, String cityId, String cityTag, String postProvinceId, String postProvinceTag, String postCityId, String postCityTag, String postDistId, String postDistTag) {
        this.provinceId = provinceId;
        this.provinceTag = provinceTag;
        this.cityId = cityId;
        this.cityTag = cityTag;
        this.postProvinceId = postProvinceId;
        this.postProvinceTag = postProvinceTag;
        this.postCityId = postCityId;
        this.postCityTag = postCityTag;
        this.postDistId = postDistId;
        this.postDistTag = postDistTag;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceTag() {
        return provinceTag;
    }

    public void setProvinceTag(String provinceTag) {
        this.provinceTag = provinceTag;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityTag() {
        return cityTag;
    }

    public void setCityTag(String cityTag) {
        this.cityTag = cityTag;
    }

    public String getPostProvinceId() {
        return postProvinceId;
    }

    public void setPostProvinceId(String postProvinceId) {
        this.postProvinceId = postProvinceId;
    }

    public String getPostProvinceTag() {
        return postProvinceTag;
    }

    public void setPostProvinceTag(String postProvinceTag) {
        this.postProvinceTag = postProvinceTag;
    }

    public String getPostCityId() {
        return postCityId;
    }

    public void setPostCityId(String postCityId) {
        this.postCityId = postCityId;
    }

    public String getPostCityTag() {
        return postCityTag;
    }

    public void setPostCityTag(String postCityTag) {
        this.postCityTag = postCityTag;
    }

    public String getPostDistId() {
        return postDistId;
    }

    public void setPostDistId(String postDistId) {
        this.postDistId = postDistId;
    }

    public String getPostDistTag() {
        return postDistTag;
    }

    public void setPostDistTag(String postDistTag) {
        this.postDistTag = postDistTag;
    }
}