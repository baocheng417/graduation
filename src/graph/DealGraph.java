package graph;

import java.util.HashMap;
import java.util.Map;

public abstract class DealGraph
{	
	public static int[][] edge = new int[2000][2]; //存储图中的边
	public static int[][] A; //图中的邻接矩阵
 	public static Map<Integer,String> idToString = new HashMap<Integer,String>();  //存储图中的节点
 	public static int num = 0;  //边的数量
 	public static float threshold = 0l; //度数阈值
	
	/**
	 * 读取文件中的图
	 * @param filePath 文件存储路径
	 * @throws Exception 抛出读文件的异常
	 */
	public abstract void readFile(String filePath) throws Exception;
	
	/**
	 * 打印图中信息 
	 */
	public abstract void printGraph();
	
	/**
	 * 获得图中度数阈值
	 */
	public abstract void getThreshold();
	
}
