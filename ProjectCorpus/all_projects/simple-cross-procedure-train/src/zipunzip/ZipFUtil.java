package zipunzip;

public class ZipFUtil {
	
	public static int F(int n) {
		if(n==1) {
			return 1;
		} else if(n==2) {
			return 1;
		} else {
			return F(n-1)+F(n-2);
		}
	}
	
}

