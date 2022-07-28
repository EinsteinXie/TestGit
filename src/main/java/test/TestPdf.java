package test;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Author: xcq
 * Date: 2021/9/2 1:52 下午
 * FileName: test.TestPdf
 */
public class TestPdf {

    public static void main(String[] args) throws Exception {

//        File file = new File("/Users/debug/Desktop/备授课-备课页.pdf");
//        PDDocument document = PDDocument.load(file);
//
//        int pages = document.getNumberOfPages();
//
//        // 读文本内容
//        PDFTextStripper stripper = new PDFTextStripper();
//        // 设置按顺序输出
//        stripper.setSortByPosition(true);
//        stripper.setStartPage(2);
//        stripper.setEndPage(2);
//        String content = stripper.getText(document);
//        System.out.println(content);
//
//        Word07Writer writer = new Word07Writer();
//        // 添加段落（正文）
//        writer.addText(new Font("宋体", Font.PLAIN, 22), content);
//        // 写出到文件
//        writer.flush(FileUtil.file("/Users/debug/Desktop/test_word.docx"));
//        // 关闭
//        writer.close();

//        File file = new File("/Users/debug/Desktop/测试.txt");
//        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file, false));
//        for (int i = 0; i < 10; i++) {
//            os.write("\t\t\t\t\t\t\t\t\t\t\t" + i + "\r\n");
//        }
//        os.close();

//        Function<String, Integer> function = s -> Integer.parseInt(s);
//
//        System.out.println(function.apply("6666"));

        test(a -> a.length() > 3, "121313");
        Function<Integer, String[]> function = String[]::new;
    }

    private static void test(Predicate<String> predicate, String msg) {
        boolean b = predicate.test(msg);
        System.out.println("b:" + b);
    }

}
