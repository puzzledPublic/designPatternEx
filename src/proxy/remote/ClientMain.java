package proxy.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
	public static void main(String[] args) {
		try {
			
			//서버 RMI 레지스트리 리스트 가져오기
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
			//이름이 gumball인 레지스트리가 있으면 가져온다.
			GumballMachineRemote machine = (GumballMachineRemote) registry.lookup("gumball");
			//로컬 객체처럼 사용가능.
			GumballMonitor gumballMonitor = new GumballMonitor(machine);
			gumballMonitor.report();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
