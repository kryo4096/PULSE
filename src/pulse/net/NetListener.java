package pulse.net;

import pulse.functional.OnConnectAction;


public class NetListener extends Thread {
	private Receiver receiver;
	private OnConnectAction action;
	
	public NetListener(Receiver receiver, OnConnectAction action) {
		super();
		
		setDaemon(true);
		
		this.receiver = receiver;
		this.action = action;

		
		
	}
	
	@Override
	public void run(){
		
		while(!isInterrupted()){
			
			if(!receiver.isUntouched()){
				
					action.trigger(receiver.lastConnection());
					
					interrupt();
				
			}
		}
		
	}
	
	
}
