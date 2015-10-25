package com.saick.base.excel.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateSimpleExcel {

	public static void main(String[] args) throws Exception {
		String data[][] = { { "���˻�", "LiXingHua", "30��" },
				{ "ħ�ֿƼ�", "mldn", "www.mldnjava.cn" } };  
		File outFile = new File("D:" + File.separator + "mldn.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(outFile);
		WritableSheet sheet = workbook.createSheet("MLDN����", 0);
		Label lab = null;
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				lab = new Label(y, x, data[x][y]);
				sheet.addCell(lab) ;
			}
		}
		workbook.write() ;
		workbook.close() ;
	}
}
