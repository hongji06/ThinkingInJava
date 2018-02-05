package chapter12.exceptions;

class VeryImportantException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Very Important Exception";
	}
}

class HoHumException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "A trival Exception";
	}
}

public class LostMessage {

	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void dispose() throws HoHumException{
		throw new HoHumException();
	}
	public static void main(String[] args) {
		try {
			LostMessage lostMessage = new LostMessage();
			try {
				lostMessage.f();
			}finally {
				try {
					lostMessage.dispose();
				}catch (Exception e) {
					System.out.println(e);
				}				
			}			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
