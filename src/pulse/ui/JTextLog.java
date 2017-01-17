package pulse.ui;

import javax.swing.*;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class JTextLog extends JTextArea {
    public JTextLog() {
        super();
        setEditable(false);
    }

    public void println(Object obj) {

        append(obj.toString() + System.lineSeparator());
        setCaretPosition(getDocument().getLength());

    }


}
