package proxy.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
//RMI 인터페이스
public interface GumballMachineRemote extends Remote {
	
	int getCount() throws RemoteException;
	
	String getLocation() throws RemoteException;
	
	State getState() throws RemoteException;
}
