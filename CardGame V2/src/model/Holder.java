package model;

public abstract class Holder 
{
	private String name;
	private int result;
	
	public Holder(String name)
	{
		this.name = name;
	}
	
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
	   public int getResult()
	   {
		   return result;
	   }
	   public void setResult(int result)
	   {
		   this.result = result;
	   }

}
