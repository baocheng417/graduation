package graph;

import java.util.HashMap;
import java.util.Map;

public abstract class DealGraph
{	
	public static int[][] edge = new int[2000][2]; //�洢ͼ�еı�
	public static int[][] A; //ͼ�е��ڽӾ���
 	public static Map<Integer,String> idToString = new HashMap<Integer,String>();  //�洢ͼ�еĽڵ�
 	public static int num = 0;  //�ߵ�����
 	public static float threshold = 0l; //������ֵ
	
	/**
	 * ��ȡ�ļ��е�ͼ
	 * @param filePath �ļ��洢·��
	 * @throws Exception �׳����ļ����쳣
	 */
	public abstract void readFile(String filePath) throws Exception;
	
	/**
	 * ��ӡͼ����Ϣ 
	 */
	public abstract void printGraph();
	
	/**
	 * ���ͼ�ж�����ֵ
	 */
	public abstract void getThreshold();
	
}
