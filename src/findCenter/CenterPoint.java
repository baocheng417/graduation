package findCenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.DealFile;
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
	 * @param filePath �ļ�����·��
	 * @param dg ��ȡ�ļ��е�ͼ��Ϣ
	 * @return ���Ľڵ㼯��
	 * @throws Exception �׳����ļ��쳣
	 */
	public List<Integer> getCenterPoint(String filePath, DealGraph dg) throws Exception
	{
		dg.readFile(filePath);
		float threshold = dg.getThreshold(); //��ȡ���Ľڵ����ֵ
		
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
			if(degree[i] > 2*threshold)
			{
				System.out.print(i+"="+degree[i]+" ");
				centerPointList.add(i);
				centerPointMap.put(i, idToString.get(i)+" "+degree[i]);
			}
		}
		return centerPointList;
	}
	
	public List<CenterDifferent> calculateDifferent(List<Integer> centerPointMaList)
	{
		int[][] A = DealGraph.A;
		List<CenterDifferent> differentList = new ArrayList<CenterDifferent>();
		
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
				differentList.add(new CenterDifferent(centerPointMaList.get(i), centerPointMaList.get(j), (float)Math.round(Math.sqrt(num)*1000)/1000));
			}
		}
		return differentList;
	}  
	
	public void print(List<CenterDifferent> centerDifferent, double similarConstant)
	{
		for (int i = 0; i < centerDifferent.size(); i++)
		{			
			if(centerDifferent.get(i).getValue() < similarConstant)
			{
				System.out.print(centerDifferent.get(i).getValue()+"-"+centerDifferent.get(i).getPre()+
						"-"+centerDifferent.get(i).getNext()+" ");
			}
		}
	}
	
	public List<CenterDifferent> sortOfCenter(List<CenterDifferent> centerDifferent)
	{
		Collections.sort(centerDifferent, new Comparator<CenterDifferent>()
		{
			@Override
			public int compare(CenterDifferent o1, CenterDifferent o2)
			{
				return o1.getValue()-o2.getValue()>0 ? -1 : (o1.getValue() == o2.getValue() ? 0 : 1);
			}
		});
		return centerDifferent;
	}
	
	public List<Integer> centerNode(List<CenterDifferent> centerDifferent, List<Integer> centerPoint)
	{
		List<Integer> centerNodes = new ArrayList<Integer>();
		List<CenterNode> node = new ArrayList<CenterNode>();
		
		float[] varieties = new float[centerDifferent.size()-1];
		
		for(int i = 1; i < centerDifferent.size(); i++)
		{
			float variety = centerDifferent.get(i).getValue() - centerDifferent.get(i-1).getValue();
			varieties[i-1] = variety;
			System.out.print(variety+" ");
		}
		
		
		
		
		return centerNodes; 
	}
	
	
	public static void main(String[] args) throws Exception
	{
		String filePath = "d:\\karate.gml";
		CenterPoint cp = new CenterPoint();
		DealGraph rg  = new ReadGraph();
		List<Integer> list = cp.getCenterPoint(filePath,rg);
		List<CenterDifferent> cs = cp.calculateDifferent(list);
		cs = cp.sortOfCenter(cs);
		System.out.println();
		cp.centerNode(cs, list);
		System.out.println();
		System.out.println(cs.size());
		cp.print(cs, 100);
	}
}
