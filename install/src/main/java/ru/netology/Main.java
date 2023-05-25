package main.java.ru.netology;

import java.io.*;

// https://github.com/netology-code/jd-homeworks/tree/master/files/task1
public class Main {
    private static final String PATH_TO_GAME = "install/Games/";
    private static final String TEMP_GAME_DIR = PATH_TO_GAME + "temp";
    private static final String LOG_FILE_PATH = TEMP_GAME_DIR + "/temp.txt";
    private static final String MAIN_GAME_DIR = PATH_TO_GAME + "src/main";
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        mkdir(PATH_TO_GAME + "src");
        mkdir(MAIN_GAME_DIR);
        mkdir(PATH_TO_GAME + "src/test");
        mkdir(PATH_TO_GAME + "res");
        mkdir(PATH_TO_GAME + "res/drawables");
        mkdir(PATH_TO_GAME + "res/vectors");
        mkdir(PATH_TO_GAME + "res/icons");
        mkdir(PATH_TO_GAME + "savegames");
        mkdir(TEMP_GAME_DIR);
        createFile(MAIN_GAME_DIR + "/Main.java");
        createFile(MAIN_GAME_DIR + "/Utils.java");
        createFile(LOG_FILE_PATH);
        writeToFile(LOG_FILE_PATH, sb.toString().getBytes());
    }

    private static void writeToFile(String path, byte[] data) {
        try (FileOutputStream out = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            bos.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mkdir(String path) {
        File dir = new File(path);
        if (dir.mkdir()) {
            sb.append("Создана директория '" + dir.getName() + "'\n");
        } else if (dir.exists()) {
            sb.append("Директория '" + dir.getName() + "' уже существует\n");
        } else {
            sb.append("Директория '" + dir.getName() + "' не создана\n");
        }
    }

    private static void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                sb.append("Создан файл '" + file.getName() + "'\n");
            } else if (file.exists()) {
                sb.append("Файл '" + file.getName() + "' уже существует\n");
            } else {
                sb.append("Файл '" + file.getName() + "' не создан\n");
            }
        } catch (IOException e) {
            sb.append("Файл '" + file.getName() + "' не создан\n");
            throw new RuntimeException(e);
        }
    }
}