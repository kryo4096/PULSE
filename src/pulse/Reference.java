package pulse;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class Reference {

    public static final int PORT = 11235;
    public static final String NAME = "Pulse";
    public static final String VERSION = "0.1.0a";

    public static String getNameString() {
        return NAME + " " + VERSION;
    }
}
