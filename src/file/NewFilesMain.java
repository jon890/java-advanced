package file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists : " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("File created: " + file);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists!");
        }

        try {
            Files.createDirectory(directory);
            System.out.println("Directory created: " + directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists!");
        }

//        Files.delete(file);
//        System.out.println("File deleted: " + file);

        System.out.println("Is regular file : " + Files.isRegularFile(file));
        System.out.println("Is directory : " + Files.isDirectory(directory));
        System.out.println("File name : " + file.getFileName());
        System.out.println("File size : " + Files.size(file) + " bytes");


        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved/renamed");

        System.out.println("Last modified : " + Files.getLastModifiedTime(newFile));

        BasicFileAttributes basicFileAttributes = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("======= Attributes =========");
        System.out.println("Creation time : " + basicFileAttributes.creationTime());
        System.out.println("Is directory : " + Files.isDirectory(newFile));
        System.out.println("Is regular file : " + Files.isRegularFile(newFile));
        System.out.println("Is symbolic link : " + Files.isSymbolicLink(newFile));
    }
}
