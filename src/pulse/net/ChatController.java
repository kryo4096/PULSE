package pulse.net;

import static pulse.Reference.PORT;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import pulse.interfaces.*;

public class ChatController {
	
	private IMessageSource source;
	private IActionTrigger sendTrigger;
	private IChatLogger logger; 
	private IIPStringSource ipSource; 
	private IActionTrigger connectTrigger; 
	private IServerStateMonitor monitor;
	
	private Receiver receiver;
	private Transmitter transmitter;
	private NetListener listener;
	
	
	
	
	public ChatController(IMessageSource source, IActionTrigger sendTrigger, IChatLogger logger,
			IIPStringSource ipSource, IActionTrigger connectTrigger, IServerStateMonitor monitor) {
		super();
		this.source = source;
		this.sendTrigger = sendTrigger;
		this.logger = logger;
		this.ipSource = ipSource;
		this.connectTrigger = connectTrigger;
		this.monitor = monitor;
		
		
		
		receiver = new Receiver(PORT, logger);
		transmitter = new Transmitter(PORT, logger);
		listener = new NetListener(receiver, ip -> connect(ip));

		receiver.start();
		listener.start();
		
		source.disable();
		
		sendTrigger.setAction(() -> transmitter.sendMessage(source.getText()));

		connectTrigger.setAction(() -> {
			logger.clearCurrent();
			InetAddress address;
			try {
				address = InetAddress.getByName(ipSource.getText());
				
			} catch (UnknownHostException e1) {
				monitor.setState(ServerState.NO_HOST);
				source.disable();
				return;
			
			}
			listener.interrupt();
			connect(address);
			
		
		});

	}
	
	private void connect(InetAddress ip){
		
		final ServerState state = transmitter.connect(ip);
		
		monitor.setState(state);
		
		if(state.canConnect()){
			
			logger.syslog("Connected to: " + ip);
			source.enable();
		} else {
			source.disable();
		}
		
		
		
	}
	
	




}
