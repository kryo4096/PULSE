package pulse.ui;

import javax.swing.JButton;

import pulse.functional.Action;
import pulse.interfaces.IActionTrigger;

public class JTriggerButton extends JButton implements IActionTrigger {

	public JTriggerButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAction(Action action) {
		// TODO Auto-generated method stub
		this.addActionListener(e -> action.perform());
	}

}
