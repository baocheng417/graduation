package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class ReadGraph extends DealGraph
{

	@Override
	public void readFile(String filePath) throws Exception
	{
		File file = new File(filePath);
		
		int edgenum = 0;
		
		if(file.isFile() && file.exists())
		{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader buffer = new BufferedReader(isr);
			String type;

			while((type = buffer.readLine()) != null)
			{
				type = type.trim();
				String[] res = type.split(" ");
				if(res[0].equals("id")) //读取id
				{
					idToString.put(Integer.parseInt(res[1]), "name"+res[1]);
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

	@Override
	public void printGraph()
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

	@Override
	public float getThreshold()
	{
		return (float)(num * 2)/idToString.size();	
	}

}
