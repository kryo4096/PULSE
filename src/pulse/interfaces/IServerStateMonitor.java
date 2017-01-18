package pulse.interfaces;

import pulse.net.ServerState;

public interface IServerStateMonitor {
	public void setState(ServerState state);
}
