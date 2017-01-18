package pulse.net;

public enum ServerState {
	
	
	
	ONLINE(true),OFFLINE(false),NO_HOST(false);
	
	private boolean canConnect;

	ServerState(boolean canConnect){
		this.canConnect = canConnect;
	}

	public boolean canConnect() {
		return canConnect;
	}



}
