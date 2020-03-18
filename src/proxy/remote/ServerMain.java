package proxy.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
	public static void main(String[] args) {
		try {
			GumballMachine gumballMachine = new GumballMachine("위치: 내컴퓨터", 5);
			//프록시 객체(Stub)
			GumballMachineRemote machine = (GumballMachineRemote)UnicastRemoteObject.exportObject(gumballMachine, 0);
			//RMI 레지스트리에 등록
			Registry registry = LocateRegistry.getRegistry(8000);
			registry.bind("gumball", machine);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
