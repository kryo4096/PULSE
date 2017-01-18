package pulse.ui;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import pulse.functional.Action;
import pulse.interfaces.IActionTrigger;
import pulse.interfaces.IMessageSource;

public class JMessageField extends JTextField implements IMessageSource, IActionTrigger {

	
	
	public JMessageField(){
		super();
		
	}

	@Override
	public void setAction(Action action) {
		// TODO Auto-generated method stub
		this.registerKeyboardAction(e-> action.perform(), KeyStroke.getKeyStroke("ENTER"), JComponent.WHEN_FOCUSED);
	}

	@Override
	public String getText() {
		String s = super.getText();
		setText("");

		return s;

	}
	
	

}
