package pulse.net;

public class NetListener extends Thread {
	private Receiver receiver;
	private Transmitter transmitter;
	private IChatLogger logger;
	
	
	
	public NetListener(Receiver receiver, Transmitter transmitter, IChatLogger logger) {
		super();
		
		setDaemon(true);
		
		this.receiver = receiver;
		this.transmitter = transmitter;
		this.logger = logger;
		
		
	}
	
	@Override
	public void run(){
		
		while(!isInterrupted()){
			
			if(!receiver.isUntouched()){
				try {
					transmitter.connect(receiver.lastConnection());
					logger.syslog("Connected to: " + receiver.lastConnection());
					interrupt();
				} catch (NoServerRunningException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
}
