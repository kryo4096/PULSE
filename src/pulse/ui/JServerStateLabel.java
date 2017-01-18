package pulse.ui;

import java.awt.Color;

import javax.swing.JLabel;

import pulse.interfaces.IServerStateMonitor;
import pulse.net.ServerState;

public class JServerStateLabel extends JLabel implements IServerStateMonitor{
	private ServerState state;
	
	public JServerStateLabel(){
		def();
	}
	
	public void setState(ServerState state){
		this.state = state;
		
		switch(state){
		case NO_HOST:
			noHost();
			break;
		case OFFLINE:
			offline();
			break;
		case ONLINE:
			online();
			break;
		default:
			def();
			break;
		
		}
		
	}
	private void def(){
		this.setForeground(Color.BLACK);
		this.setText("● No Connection");
	}
	
	private void noHost(){
		this.setForeground(Color.RED);
		this.setText("● Unknown Host");
	}
	
	private void offline(){
		this.setForeground(Color.RED);
		this.setText("● Offline");
	}
	
	private void online(){
		this.setForeground(Color.GREEN);
		this.setText("● Online");
	}
}
