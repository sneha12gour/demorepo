
class Producer extends Thread
{
	C c;
	Producer(C c)
	{
		this.c=c;
	}
	public void run()
	{
		for(int i=1;i<=10;i++)
		{
			c.show1(i);
		}
	}
}
class Consumer extends Thread
{
	C c;
	Consumer(C c)
	{
		this.c=c;
	}
	public void run()
	{
		for(int i=1;i<=10;i++)
		{
			c.show2(i);
		}
	}
}
class C
{
	int i;
	boolean b;
	synchronized void show1(int i)
	{
		if(b)
		{
			try{wait();}catch(Exception e){}
		}
		this.i=i;
		System.out.println("cut=>"+i);
		b=true;
		notify();
	}
	synchronized void show2(int i)
	{
		if(b==false)
		{
			try{wait();}catch(Exception e){}
		}
		this.i=i;
		System.out.println("show=>"+i);
		b=false;
		notify();
	}
	}
class pr3
{
	public static void main(String ar[])
	{
		C c1=new C();
		Producer a1=new Producer(c1);
		Consumer b1=new Consumer(c1);
		a1.start();
		b1.start();
	}
}