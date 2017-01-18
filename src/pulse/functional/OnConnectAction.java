package pulse.functional;

import java.net.InetAddress;

@FunctionalInterface
public interface OnConnectAction {
	public void trigger(InetAddress ip);
}
