package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 使用lambda表达式处理文件
 */
public class MyProcessFile {
    public static void main(String[] args) throws IOException {
        String fileName = "lambdasinaction/chap3/data.txt";
        String result = processFileLimited(fileName);
        System.out.println(result);

        System.out.println("---");

        //读取一行
        String oneLine = processFile((BufferedReader br) -> br.readLine(), fileName);
        System.out.println(oneLine);

        //读取两行
        BufferedReaderProcessor p = (BufferedReader br) -> br.readLine() + br.readLine();
        String twoLines = processFile(p, fileName);
        System.out.println(twoLines);
    }

    public static String processFileLimited(String fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        }
    }

    /** 接受lambda表达式,灵活处理 */
    public static String processFile(BufferedReaderProcessor p, String fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return p.process(br);   //其他操作照常,把处理文件的行为参数化(接受参数和返回)
        }
    }

    public interface BufferedReaderProcessor {
        public String process(BufferedReader b) throws IOException;
    }
}
