
public class Starter {
	public static void main(String[] args){
		Dummy a=new Dummy();	 // erstellt das Spielfeld
		
		
		try{
		a.sleep(1000);}  // wartet explizit auf a.start() welches die Anzeige eroeffnet
		
		catch(InterruptedException e){}
		
		a.start();
	}
}
