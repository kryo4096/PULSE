package pulse;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public interface IChatLogger {

    default void log(String nickname, String message) {
        String entry = "[ " + nickname + " ] " + message;
        String filename = nickname + ".log";
        System.out.println(entry);
        try (PrintStream ps = new PrintStream(Files.newOutputStream(Paths.get(filename), StandardOpenOption.CREATE, StandardOpenOption.WRITE));) {
            ps.println(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
