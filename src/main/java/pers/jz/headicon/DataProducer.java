package pers.jz.headicon;

import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author jemmyzhang on 2018/1/10.
 */
public class DataProducer {
    public static final String OUTPUT_PATH = "out/";

    public static final String PNG = "PNG";
    public static final String PNG_SUFFIX = "." + PNG;
    public static final String ORIGIN_IMG = "origin.png";
    public static final String DESK_PREFIX = "D";
    public static final String NUMBER_PREFIX = "N";
    public static final String DESK_SUFFIX_CN = "桌";
    public static final String NUMBER_SUFFIX_CN = "号";


    public static void main(String[] args) {
        clear();
        List<Integer> list = Arrays.asList(1, 2, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 20, 21, 25);
        int people = 10;
        String data = generateData(list, people);
        writeDataFile(data);
    }

    private static String generateData(int seat, int people) {

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= seat; i++) {
            list.add(i);
        }
        generateData(list, people);
        return new Gson().toJson(list);
    }

    private static String generateData(List<Integer> list, int people) {
        List<Entity> entities = new ArrayList<>();
        if (Objects.isNull(list)) {
            throw new NullPointerException();
        }
        for (Integer item : list) {
            if (Objects.isNull(item))
                throw new NullPointerException();
            for (int i = 1; i <= people; i++) {
                String name = buildChineseName(item, i);
                String value = buildImageFileName(item, i);
                Entity entity = new Entity();
                Entity.Data data = new Entity.Data();
                data.setValue(value);
                entity.setName(name);
                entity.setAvatar(OUTPUT_PATH + value + PNG_SUFFIX);
                entity.setData(data);
                drawIcons(value);
                entities.add(entity);
            }
        }
        return new Gson().toJson(entities);
    }

    public static String buildChineseName(int desk, int number) {
        return desk + DESK_SUFFIX_CN + number + NUMBER_SUFFIX_CN;
    }

    public static String buildImageFileName(int desk, int number) {
        String deskStr = DESK_PREFIX + (desk < 10 ? "0" + desk : desk);
        String numberStr = NUMBER_PREFIX + (number < 10 ? "0" + number : number);
        return new StringBuilder(deskStr).append(numberStr).toString();
    }

    public static void clear() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!dir.isDirectory()) {
            String errMsg = String.format("Target path \"%s\" is not a directory! ", OUTPUT_PATH);
            throw new UnsupportedOperationException(errMsg);
        }
        String[] list = dir.list();
        if (list.length != 0) {
            for (int i = 0; i < list.length; i++) {
                delete(new File(dir, list[i]));
            }
        }
    }

    private static boolean delete(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = delete(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void writeDataFile(String value) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("out/sample-data.json"))) {
            writer.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drawIcons(String value) {
        StringBuilder builder = new StringBuilder(value);
        builder.append(PNG_SUFFIX);
        drawIcons(value, builder.toString());
    }

    public static void drawIcons(String content, String fileName) {
        if (Objects.isNull(content) || !content.matches("D\\d\\dN\\d\\d")) {
            throw new UnsupportedOperationException();
        }

        try (InputStream in = new FileInputStream(ORIGIN_IMG)) {
            BufferedImage originImage = ImageIO.read(in);
            Graphics g = originImage.getGraphics();
            Font font = new Font(Font.MONOSPACED, Font.BOLD, 41);
            g.setFont(font);
            g.drawString(content, 26, 110);
            File file = new File(OUTPUT_PATH + fileName);
            ImageIO.write(originImage, PNG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
