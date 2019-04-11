package com.item.tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PdfUtil {
    public static final File input = new File("pdf2excel/1");

    public static void main(String[] args) throws Exception {
        String title = "鼎和财产保险股份有限公司双流支公司手续费结算单";
        String[] rowsName = new String[]{"序号", "保/批单号", "险种", "投保人", "被保险人", "保单币种", "交费期数", "不含税保费", "销项税", "不含税手续费", "手续费进项税", "手续费价税合计","业务部门", "业务员"};
        List<Object[]> dataList = doc2Obj();
        Export2Excel ex = new Export2Excel(title, rowsName, dataList);
        ex.export();
    }


    public static void pdf2Txt(){
        PDDocument pd;
        BufferedWriter wr;
        try {
            File sourceFile = new File("1.pdf");
            File output = new File(sourceFile.getName().split("\\.")[0] + ".txt");
            pd = PDDocument.load(sourceFile);
            pd.save("CopyOf" + sourceFile.getName().split("\\.")[0] + ".pdf"); // Creates a copy called
            PDFTextStripper stripper = new PDFTextStripper();
            wr = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(output)));
            stripper.writeText(pd, wr);
            if (pd != null) {
                pd.close();
            }
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Object[]> doc2Obj() throws Exception{

        InputStreamReader isr = new InputStreamReader(new FileInputStream(input), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String lineTxt = null;
        List<String> doc = new ArrayList<String>();
        List<String> res = new ArrayList<String>();
        while ((lineTxt = br.readLine()) != null) {
            doc.add(lineTxt);
        }
        br.close();
        int cs = 1;
        System.out.println("文档行数:" + doc.size());
        for (int i = 0; i < doc.size(); i++) {
//            System.out.println(i);
            String line = doc.get(i).trim();
//            System.out.println("aaa:"+line);
            StringBuilder sb = new StringBuilder();
            if (isNumeric(line)) {
                sb.append(cs);
                sb.append("#");
                sb.append(line);
                sb.append("#");
//                System.out.println(sb.toString());
            }
            int a = i + 30 > doc.size() ? doc.size() : i + 30;
            for (int j = i + 1; j < a; j++) {
                String line01 = doc.get(j).trim();
                if (isNumeric(line01)) {
//                    System.out.println("reset:"+line01);
                    int flag = 0;
                    for (int k = i + 1; k < j; k++) {
                        String content = doc.get(k).trim().replaceAll(",", "");
//                        System.out.println("content:" + content);
                        String[] contentArr = content.split(" ");
                        int flag01 = k;
                        if (contentArr.length <= 2 && content.contains("保险")) {
//                            System.out.println("info:"+content);
                            for (int l = i + 1; l <= k; l++) {
                                sb.append(doc.get(l).trim());
                                flag = l + 1;
                            }

                        }
                        if (content.contains("CNY")) {
//                            System.out.println(content);
                            if (content.startsWith("CNY")) {
                                int cj = (k - flag) / 2;
                                sb.append("#");
                                for (int l = flag; l < k - cj; l++) {
//                                    System.out.println("1****"+doc.get(l).trim());
                                    sb.append(doc.get(l).trim());
                                }
                                sb.append("#");
                                for (int l = flag + cj; l < k; l++) {
//                                    System.out.println("2****"+doc.get(l).trim());
                                    sb.append(doc.get(l).trim());
                                }
//                                System.out.println("****" + sb.toString());
                            }
                            for (String str : contentArr) {
                                sb.append("#");
                                sb.append(str.trim());
                            }
                        }
                        if (content.contains("渠道")) {
                            sb.append("#");
                            sb.append(content);
                        }
                        if (content.contains("管理部")) {
                            sb.append(content);
                            sb.append("#");
                            for (int x = k + 1; x < j; x++) {
                                sb.append(doc.get(x).trim());
                            }
                        }

                    }
                    i = j - 1;
                    break;
                }
            }
            if (!sb.toString().contains("2222222222222222222222") && sb.toString() != "") {
                System.out.println(sb.toString());
                res.add(sb.toString());
            }
            cs++;
        }

        FileWriter fw = new FileWriter(new File("鼎和财产保险股份有限公司双流支公司手续费结算单.txt"));
        //写入中文字符时会出现乱码
        BufferedWriter bw = new BufferedWriter(fw);
        //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8")));

        res.forEach(s -> {
            try {
                bw.write(s + "\t\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
        fw.close();
        return res.stream().map(s -> s.split("#")).collect(Collectors.toCollection(ArrayList::new));
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}

