package chapter12.exceptions;

class NeedsCleanup {
	private static long counter = 1;
	private final long id = counter++;

	public void dispose() {
		System.out.println("NeedsCleanup " + id + " dispose.");
	}

}

class ConstructionException extends Exception {
	private static final long serialVersionUID = 3915285359130131189L;

}

class NeedsCleanup2 extends NeedsCleanup {
	public NeedsCleanup2() throws ConstructionException {
		throw new ConstructionException();
	}
}

public class CleanupIdiom {
	public static void main(String[] args) {
		// section 1
		NeedsCleanup nc1 = new NeedsCleanup();
		try {

		} finally {
			nc1.dispose();
		}

		// section 2
		NeedsCleanup nc2 = new NeedsCleanup();
		NeedsCleanup nc3 = new NeedsCleanup();
		try {
			
		}finally {
			nc3.dispose();
			nc2.dispose();
		}
		
		//section 3
		try {
			NeedsCleanup2 nc4 = new NeedsCleanup2();			
			try {
			 NeedsCleanup2 nc5=	new NeedsCleanup2();
			 try {
				 
			 }finally {
				nc5.dispose();
			}
			}catch (ConstructionException e) {//nc5
				System.out.println(e);
			}finally {
				nc4.dispose();
			}
		}catch (ConstructionException e) {//nc4
			System.out.println(e);
		}
		
		
	}
}
