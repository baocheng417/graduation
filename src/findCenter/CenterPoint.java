package findCenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.DealGraph;
import graph.ReadData;
import graph.ReadGraph;

/**
 * ������ͼ�����Ľڵ�Ĵ���
 * @author xu 
 */
public class CenterPoint
{
	static Map<Integer,String> centerPointMap = new HashMap<Integer,String>(); //�洢���Ľڵ�
	static int[][] edge = new int[2000][2];
	static int pointNum = 0;
	/**
	 * 
	 * @param threshold ����ѡȡ���Ľڵ����ֵ
	 * @param filePath �ļ�����·��
	 * @param dg ��ȡ�ļ��е�ͼ��Ϣ
	 * @return ���Ľڵ㼯��
	 * @throws Exception �׳����ļ��쳣
	 */
	public List<Integer> getCenterPoint(float threshold, String filePath, DealGraph dg) throws Exception
	{
		dg.readFile(filePath);
		
		Map<Integer,String> idToString = DealGraph.idToString;//��ȡ�ļ��еĽڵ�
		edge = DealGraph.edge;//��ȡ�ļ��еı�
		int[] degree = new int[idToString.size()+1];//�洢�ڵ�Ķ�
		int num = DealGraph.num;  //��ñߵ���Ŀ
		List<Integer> centerPointList = new ArrayList<Integer>();//���Ľڵ㼯��
		
		for(int i = 0; i < num; i++)
		{
			degree[edge[i][0]]++;
			degree[edge[i][1]]++;
		}
		
		for (int i = 0; i < degree.length; i++)
		{
			if(degree[i] >= 2*threshold)
			{
				centerPointList.add(i);
				centerPointMap.put(i, idToString.get(i)+" "+degree[i]);
			}
		}
		return centerPointList;
	}
	
	public List<CenterSimilarity> calculateSimilarity(List<Integer> centerPointMaList)
	{
		int[][] A = DealGraph.A;
		List<CenterSimilarity> similarityList = new ArrayList<CenterSimilarity>();
		
		for(int i = 0; i < centerPointMaList.size(); i++)
		{ 
			for(int j = i+1; j < centerPointMaList.size(); j++)
			{
				double num = 0;
				for(int k = 0; k < A.length; k++)
				{
					if(centerPointMaList.get(i) == k || centerPointMaList.get(j) == k)
						continue;
					num += Math.pow(A[centerPointMaList.get(i)][k]-A[centerPointMaList.get(j)][k], 2);
				}
				similarityList.add(new CenterSimilarity(centerPointMaList.get(i), centerPointMaList.get(j), (float)Math.round(Math.sqrt(num)*1000)/1000));
			}
		}
		return similarityList;
	}  
	
	public void print(List<CenterSimilarity> centerSimilarity, double similarConstant)
	{
		for (int i = 0; i < centerSimilarity.size(); i++)
		{
			if(i % 20 == 0)
			{
				System.out.println();
			}
			
			if(centerSimilarity.get(i).getValue() < similarConstant)
			{
				System.out.print(centerSimilarity.get(i).getValue()+"-"+centerSimilarity.get(i).getPre()+
						"-"+centerSimilarity.get(i).getNext()+" ");
			}
		}
	}
	
	public List<CenterSimilarity> sortOfCenter(List<CenterSimilarity> centerSimilarity)
	{
		Collections.sort(centerSimilarity, new Comparator<CenterSimilarity>()
		{
			@Override
			public int compare(CenterSimilarity o1, CenterSimilarity o2)
			{
				return o1.getValue()-o2.getValue()>0 ? 1 : (o1.getValue() == o2.getValue() ? 0 : -1);
			}
		});
		return centerSimilarity;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		String filePath = "d:\\karate.gml";
		CenterPoint cp = new CenterPoint();
		DealGraph rg  = new ReadGraph();
		List<Integer> list = cp.getCenterPoint(DealGraph.threshold, filePath,rg);
		List<CenterSimilarity> cs = cp.calculateSimilarity(list);
		cs = cp.sortOfCenter(cs);
		System.out.println(cs.size());
		cp.print(cs, 100);
	}
}
