package zipunzip;

import graph.mul.gen.ABC;
import graph.mul.gen.H123;
import graph.mul.gen.ZipFUtil;

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

interface H123 {
	public void Ha1();
}

interface ABC extends H123 {
	public void Ha();
}

class ZZip extends ZipFUtil implements ABC {

	@Override
	public void Ha() {
		System.out.println("HaHa!");
	}

	@Override
	public void Ha1() {
		System.out.println("HaHa1!");
	}
	
}


