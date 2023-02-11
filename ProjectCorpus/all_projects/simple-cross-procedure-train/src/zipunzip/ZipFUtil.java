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

interface A {
	public void yyx_invoke();
}

class B implements A {
	@Override
	public void yyx_invoke() {
		System.out.println("B");
	}
}

class C implements Runnable {
	@Override
	public void run() {
		System.out.println("B");
	}
}

class D {
	void test() {
		Runnable r = new C();
		r.run();
		A a = new B();
		a.yyx_invoke();
	}
}
