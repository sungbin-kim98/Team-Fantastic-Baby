public class test {

    public static void main(String[] args ) {
	String s = new String( "p" );
	System.out.println( s.length() );
	System.out.println( s.substring(1) );
	s = s.substring(1);
	System.out.println( s.length() );
    }
}
