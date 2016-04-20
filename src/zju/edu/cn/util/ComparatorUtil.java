package zju.edu.cn.util;

import java.util.Comparator;

import zju.edu.cn.po.AveGrade;

public class ComparatorUtil implements Comparator<AveGrade> {
	public int compare(AveGrade a1,AveGrade a2) {
		Float aveGrage1 = a1.getAverageGrade();
		Float aveGrage2 = a2.getAverageGrade();
		return aveGrage1.compareTo(aveGrage2);
	}
}
