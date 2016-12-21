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
 * ��ȡ�ļ��е�����
 * @author xu
 *
 */
public class ReadData
{
	public static int[][] edge = new int[2000][2]; //�洢ͼ�еı�
	public static int[][] A; //ͼ�е��ڽӾ���
 	public static Map<Integer,String> idToString = new HashMap<Integer,String>();  //�洢ͼ�еĽڵ�
 	public static int num = 0;  //�ߵ�����
	/**
	 * ��ȡ�ļ��е�ͼ
	 * @param filePath �ļ��洢·��
	 * @throws Exception �׳����ļ����쳣
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
				if(res[0].equals("id")) //��ȡid��label
				{
					String second = buffer.readLine();
					second = second.trim();
					String[] res2 = second.split(" ");
					idToString.put(Integer.parseInt(res[1]), res2[1]);
				}
				else if(res[0].equals("source")) //��ȡ��
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
			System.out.println("�Ҳ���ָ���ļ�");
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
				if(res[0].equals("id")) //��ȡid��label
				{
					idToString.put(Integer.parseInt(res[1]), "name");
				}
				else if(res[0].equals("source")) //��ȡ��
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
			System.out.println("�Ҳ���ָ���ļ�");
		}		
	}
	
	public void printGraph(String filePath) throws Exception
	{
		//������е�idֵ�Ͷ�Ӧ��label
		Iterator<Integer> iter = idToString.keySet().iterator();
		while(iter.hasNext())
		{
			Integer key = (Integer)iter.next();
			String val = (String)idToString.get(key);
			System.out.println(key+" "+ val);
		}
		//������б� 
		for(int i = 0;i < num; i++)
		{
			System.out.println(edge[i][0]+" "+edge[i][1]);
		}
		
		//����ڽӾ���
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
