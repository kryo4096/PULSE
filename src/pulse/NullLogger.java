package pulse;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class NullLogger implements IChatLogger {

    private static NullLogger singleton = new NullLogger();

    @Override
    public void log(String nickname, String message) {

    }

    private NullLogger() {
    }

    protected static NullLogger instance() {
        return singleton;
    }
}
