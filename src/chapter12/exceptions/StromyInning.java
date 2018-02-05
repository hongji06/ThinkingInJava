package chapter12.exceptions;

class BaseBallException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320161126825860071L;
}

class Foul extends BaseBallException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8009307114460347266L;
}

class Strike extends BaseBallException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224412960567552908L;
}

abstract class Inning {
	public Inning() throws BaseBallException {
	}

	public void event() throws BaseBallException {
	}

	public abstract void atBat() throws Strike, Foul;

	public void walk() {
	}
}

class StormException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

class RaineOut extends StormException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

class PopFoul extends Foul {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

interface Storm {
	public void event() throws RaineOut;

	public void rainHard() throws RaineOut;
}

public class StromyInning extends Inning implements Storm {
	
	public StromyInning() throws BaseBallException{
		// TODO Auto-generated constructor stub
	}
	
	public StromyInning(String string) throws BaseBallException{
		
	}
	
	public void event(){
		
	}
	

	@Override
	public void rainHard() throws RaineOut {
	}

	@Override
	public void atBat() throws Strike, Foul {
	}
	
	public static void main(String[] args) {
		try {
			StromyInning si = new StromyInning();
			si.atBat();
		}catch (PopFoul e) {
			System.out.println("pop foul");
		}catch (BaseBallException e) {
			System.out.println("baseball exception");
		}
	}

}
