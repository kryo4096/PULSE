package pulse.ui;

import javax.swing.JTextArea;

import pulse.Reference;
import pulse.interfaces.IChatLogger;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class JChatLog extends JTextArea implements IChatLogger {
    @Override
    public synchronized void log(String nickname, String message) {
        String entry = "[ " + nickname + " ] " + message;
        println(entry);
    }
    
    @Override
    public synchronized void syslog(String message) {
        String entry = "[ " + Reference.getNameString() + " ] " + message;
        println(entry);
    }
    
    public JChatLog() {
        super();
        setEditable(false);
    }
    
    @Override
	public synchronized void clearCurrent() {
		// TODO Auto-generated method stub
		setText("");
	}

    public void println(Object obj) {

        append(obj.toString() + System.lineSeparator());
        setCaretPosition(getDocument().getLength());

    }

	
}
