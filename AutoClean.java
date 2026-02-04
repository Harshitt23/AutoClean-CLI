import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class AutoClean {

    private static final Map<String, String[]> FILE_TYPES = new HashMap<>();

    static {
        FILE_TYPES.put("Images", new String[]{".jpg", ".jpeg", ".png", ".gif"});
        FILE_TYPES.put("Documents", new String[]{".pdf", ".docx", ".txt", ".pptx", ".xlsx"});
        FILE_TYPES.put("Videos", new String[]{".mp4", ".mkv", ".avi"});
        FILE_TYPES.put("Archives", new String[]{".zip", ".rar", ".7z"});
        FILE_TYPES.put("Executables", new String[]{".exe", ".msi"});
    }

    public static void main(String[] args) {
        String targetDir;

        if (args.length > 0) {
            targetDir = args[0];
        } else {
            targetDir = System.getProperty("user.home") + File.separator + "Downloads";
        }

        System.out.println("🧹 Cleaning directory: " + targetDir);
        cleanDirectory(new File(targetDir));
    }

    private static void cleanDirectory(File directory) {
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("❌ Invalid directory");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String category = getCategory(file.getName());
                File destFolder = new File(directory, category);

                if (!destFolder.exists()) {
                    destFolder.mkdir();
                }

                moveFile(file, destFolder);
            }
        }
    }

    private static String getCategory(String fileName) {
        String lower = fileName.toLowerCase();

        for (Map.Entry<String, String[]> entry : FILE_TYPES.entrySet()) {
            for (String ext : entry.getValue()) {
                if (lower.endsWith(ext)) {
                    return entry.getKey();
                }
            }
        }
        return "Others";
    }

    private static void moveFile(File file, File destFolder) {
        File destFile = new File(destFolder, file.getName());

        if (destFile.exists()) {
            String name = file.getName();
            int dotIndex = name.lastIndexOf(".");
            String newName = (dotIndex == -1)
                    ? name + "_copy"
                    : name.substring(0, dotIndex) + "_copy" + name.substring(dotIndex);

            destFile = new File(destFolder, newName);
        }

        try {
            Files.move(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✔ Moved " + file.getName() + " → " + destFolder.getName());
        } catch (IOException e) {
            System.out.println("⚠ Failed to move: " + file.getName());
        }
    }
}