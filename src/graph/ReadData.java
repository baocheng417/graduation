package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 读取文件中的数据
 * @author xu
 *
 */
public class ReadData
{
	public static int[][] edge = new int[2000][2]; //存储图中的边
	public static int[][] A; //图中的邻接矩阵
 	public static Map<Integer,String> idToString = new HashMap<Integer,String>();  //存储图中的节点
 	public static int num = 0;  //边的数量
	/**
	 * 读取文件中的图
	 * @param filePath 文件存储路径
	 * @throws Exception 抛出读文件的异常
	 */
	public void read(String filePath) throws Exception
	{
		File file = new File(filePath);
		
		int edgenum = 0;
		
		if(file.isFile() && file.exists())
		{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader buffer = new BufferedReader(isr);
			String type;
//			int value;
			while((type = buffer.readLine()) != null)
			{
				type = type.trim();
				String[] res = type.split(" ");
				if(res[0].equals("id")) //读取id和label
				{
					String second = buffer.readLine();
					second = second.trim();
					String[] res2 = second.split(" ");
					idToString.put(Integer.parseInt(res[1]), res2[1]);
				}
				else if(res[0].equals("source")) //读取边
				{
					String second = buffer.readLine();
					second = second.trim();
					String[] res2 = second.split(" ");
					edge[edgenum][0] = Integer.parseInt(res[1]);
					edge[edgenum][1] = Integer.parseInt(res2[1]);
					edgenum++;
				}
			}
			num = edgenum;
			A = new int[idToString.size()][idToString.size()];
			for(int i = 0; i < num; i++)
			{
				A[edge[i][0]][edge[i][1]] = 1;
			}
			
			buffer.close();
			
		}
		else
		{
			System.out.println("找不到指定文件");
		}		
	}
	
	public void read2(String filePath) throws Exception
	{
		File file = new File(filePath);
		
		int edgenum = 0;
		
		if(file.isFile() && file.exists())
		{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader buffer = new BufferedReader(isr);
			String type;
//			int value;
			while((type = buffer.readLine()) != null)
			{
				type = type.trim();
				String[] res = type.split(" ");
				if(res[0].equals("id")) //读取id和label
				{
					idToString.put(Integer.parseInt(res[1]), "name");
				}
				else if(res[0].equals("source")) //读取边
				{
					String second = buffer.readLine();
					second = second.trim();
					String[] res2 = second.split(" ");
					edge[edgenum][0] = Integer.parseInt(res[1]);
					edge[edgenum][1] = Integer.parseInt(res2[1]);
					edgenum++;
				}
			}
			num = edgenum;
			A = new int[idToString.size()+1][idToString.size()+1];
			
			for(int i = 0; i < num; i++)
			{
				A[edge[i][0]][edge[i][1]] = 1;
			}
			
			buffer.close();
			
		}
		else
		{
			System.out.println("找不到指定文件");
		}		
	}
	
	public void printGraph(String filePath) throws Exception
	{
		//输出所有的id值和对应的label
		Iterator<Integer> iter = idToString.keySet().iterator();
		while(iter.hasNext())
		{
			Integer key = (Integer)iter.next();
			String val = (String)idToString.get(key);
			System.out.println(key+" "+ val);
		}
		//输出所有边 
		for(int i = 0;i < num; i++)
		{
			System.out.println(edge[i][0]+" "+edge[i][1]);
		}
		
		//输出邻接矩阵
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A[0].length; j++)
			{
				System.out.print(A[i][j]+" ");
				if(A[i][j] == 1)
				{
					System.out.print("--"+i+"-"+j+"--");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
//		String filePath = "d:\\dolphins.gml";
		String filePath2 = "d:\\karate.gml";
		ReadData rd = new ReadData();
//		rd.read(filePath);
		rd.read2(filePath2);
		rd.printGraph(filePath2);
//		rd.printGraph(filePath);
	}
}
