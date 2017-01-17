package pulse.net;

public class NoServerRunningException extends Exception {
	public NoServerRunningException(int port){
		super("no server running on port "+port);
	}
}
