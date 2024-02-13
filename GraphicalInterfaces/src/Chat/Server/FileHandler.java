package Chat.Server;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements FileProcessing {
    String fileLog;
    public FileHandler(String fileLog) {
        this.fileLog = fileLog;
    }

    @Override
    public void save(String text) {
        try (BufferedWriter out = new BufferedWriter (new FileWriter(fileLog))){
            out.write(text);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public String read() {
        StringBuilder str = new StringBuilder();
        if (Files.exists(Path.of(fileLog))) {
            try (BufferedReader in = new BufferedReader(new FileReader(fileLog))) {
                while (in.ready()) {
                    str.append(in.readLine()+"\n");
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return str.toString();
    }
}
