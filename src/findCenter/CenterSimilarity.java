package findCenter;

public class CenterSimilarity
{
	private int pre;
	private int next;
	private float value;
	public int getPre()
	{
		return pre;
	}
	public void setPre(int pre)
	{
		this.pre = pre;
	}
	public int getNext()
	{
		return next;
	}
	public void setNext(int next)
	{
		this.next = next;
	}
	public float getValue()
	{
		return value;
	}
	public void setValue(float value)
	{
		this.value = value;
	}
	public CenterSimilarity(int pre, int next, float value)
	{
		super();
		this.pre = pre;
		this.next = next;
		this.value = value;
	}
}
