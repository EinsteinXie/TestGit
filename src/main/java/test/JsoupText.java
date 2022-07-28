package test;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: xcq
 * Date: 2022/1/12 3:51 下午
 * FileName: JsoupText
 */
public class JsoupText {

    public static void main2(String[] args) throws Exception {
        File file = new File("/Users/debug/Desktop/鬼眼神师.txt");
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file, true));
        try {

            Connection connect = Jsoup.connect("http://www.go5.cc/book/80469/").header("user-agent",
                                                                                       "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36 Edg/88.0.705.56").timeout(1000);
            Document document = connect.get();

            Element element = document.getElementById("chapterList");
            Elements elements = element.select("a");

            List<Map<String, String>> linkList = new ArrayList<>(elements.size());

            for (Element ele : elements) {
                Map<String, String> linkMap = new HashMap<>();
                String href = ele.attr("href");
                String title = ele.text();
                linkMap.put("href", href);
                linkMap.put("title", title);
                linkList.add(linkMap);
            }

            int cnt = 0;
            if (linkList != null && linkList.size() > 0) {
                for (int i = 45; i < linkList.size(); i++) {
                    Map<String, String> linkMap = linkList.get(i);
                    String href = linkMap.get("href");
                    String title = linkMap.get("title");

                    boolean flag = false;
                    String text = "";
                    boolean isTitle = true;
                    do {
                        Connection connect2 = Jsoup.connect("http://www.go5.cc" + href).header("user-agent",
                                                                                               "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36 Edg/88.0.705.56").timeout(
                                1000);
                        Document document2 = connect2.get();

                        //获取正文
                        Element textEle = document2.getElementById("TextContent");
                        Elements textElements = textEle.select("p");
                        for (Element ele : textElements) {
                            if (isTitle) {
                                text += ele.text() + "\r\n";
                                isTitle = false;
                            } else {
                                text += "\t" + ele.text() + "\r\n";
                            }
                        }

                        //是否还有下一页
                        Element element2 = document2.getElementById("next_url");
                        if (element2.text().contains("下一页")) {
                            isTitle = false;
                            String nextHref = element2.toString();
                            if (StringUtils.isNotBlank(nextHref)) {
                                nextHref = nextHref.substring(nextHref.indexOf("window.open('") + 13, nextHref.indexOf("','_self"));
                            }
                            href = nextHref;
                            flag = true;
                        } else {
                            break;
                        }

                    } while (flag);
                    os.write(text);
                    System.out.println("爬取:" + title + ",成功");
                    cnt += 1;
                }
            }

            System.out.println("cnt:" + cnt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    public static void main(String[] args) {

        String targetUrl = "https://www.xquledu.com/html/7/7686/4578827.html";

        OutputStreamWriter os = null;

        try {

            File file = new File("/Users/debug/Desktop/鬼眼神师.txt");
            os = new OutputStreamWriter(new FileOutputStream(file, true));

            boolean isContinue = true;
            do {
                Connection connect = Jsoup.connect(targetUrl).header("user-agent",
                                                                     "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36 Edg/88.0.705.56");
                Document document = connect.get();

                Elements elements = document.select("#amain > dl > dd:nth-child(2) > h1");
                String title = elements.get(0).text();
                os.write(title + "\r");

                if (title.contains("第二千二一五章")) {
                    isContinue = false;
                }

                Elements elements1 = document.select("#contents");
                String context = elements1.get(0).toString();

                context = context.replaceAll("<dd id=\"contents\">", "").replaceAll("</dd>", "").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\r \t").replaceAll("&nbsp;", "").replaceAll("<br>", "").replaceAll("\n", "");
                context = context.substring(context.indexOf("\r") + 2, context.length());
                os.write(context);

                System.out.println("爬取:" + title + ",成功");

                //获取下一页url
                Elements nextEles = document.select("#footlink > a:nth-child(3)");
                String href = nextEles.get(0).attr("href");

                targetUrl = "https://www.xquledu.com/html/7/7686/" + href;
            } while (isContinue);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
