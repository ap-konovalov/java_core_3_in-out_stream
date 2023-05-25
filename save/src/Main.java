import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


// https://github.com/netology-code/jd-homeworks/tree/master/files/task2
public class Main {
    private static final String SAVE_GAME_PATH = "/Users/aleksandr/Desktop/netology-java/JavaCore/in_out_streams/install/Games/savegames/";
    private static final String END_SAVE_ABSOLUTE_PATH = SAVE_GAME_PATH + "save3.dat";
    private static final String MIDDLE_SAVE_ABSOLUTE_PATH = SAVE_GAME_PATH + "save2.dat";
    private static final String BEGIN_SAVE_ABSOLUTE_PATH = SAVE_GAME_PATH + "save1.dat";

    public static void main(String[] args) {
        GameProgress begin = new GameProgress(100, 0, 1, 0);
        GameProgress middle = new GameProgress(75, 2, 5, 76);
        GameProgress end = new GameProgress(1, 14, 15, 168);
        saveGame(BEGIN_SAVE_ABSOLUTE_PATH, begin);
        saveGame(MIDDLE_SAVE_ABSOLUTE_PATH, middle);
        saveGame(END_SAVE_ABSOLUTE_PATH, end);
        List<String> filesPathes = List.of(BEGIN_SAVE_ABSOLUTE_PATH, MIDDLE_SAVE_ABSOLUTE_PATH, END_SAVE_ABSOLUTE_PATH);
        zipFiles(SAVE_GAME_PATH + "save.zip", filesPathes);
    }

    private static void saveGame(String absolutePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(absolutePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void zipFiles(String archiveAbsolutePath, List<String> filesPathes) {
        try (FileOutputStream fos = new FileOutputStream(archiveAbsolutePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            filesPathes.forEach(filePath -> {
                File srcFile = new File(filePath);
                try (FileInputStream fis = new FileInputStream(srcFile)) {
                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        deleteFiles(filesPathes);
    }

    private static void deleteFiles(List<String> filesPathes) {
        filesPathes.forEach(filePath -> {
            File srcFile = new File(filePath);
            srcFile.delete();
        });
    }
}