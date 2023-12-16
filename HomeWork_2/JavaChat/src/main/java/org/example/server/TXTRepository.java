package org.example.server;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TXTRepository implements Saveable{
    @Override
    public boolean saveLog(StringBuilder chat) {
        try (FileWriter writer = new FileWriter("log.txt", false))
        {
            for (String line: chat.toString().split("\\n")) {
                writer.write(line + "\n");
            }
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String loadLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader("log.txt")) {
            int c;
            while((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }
}
