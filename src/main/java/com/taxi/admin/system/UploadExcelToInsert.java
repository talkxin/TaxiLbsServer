package com.taxi.admin.system;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 上传Excel反射录入
 * 
 * @author talkliu
 * 
 */
public class UploadExcelToInsert<T> {
	/**
	 * 反射上传
	 * 
	 * @param f
	 * @param c
	 * @return
	 */
	public List<T> inserts(File f, Class t, HashMap<String, String> key) {
		Workbook book = null;
		try {
			if (f != null && t != null && key != null) {
				// 返回的list
				List<T> list = new ArrayList<T>();
				// 读取excel
				book = Workbook.getWorkbook(f);
				// 获取第一个工作簿
				Sheet sheet = book.getSheet(0);
				// 获取首行验证
				Cell[] cell = sheet.getRow(0);
				Field[] fGet = t.getDeclaredFields();
				// 将属性与属性类型取出
				HashMap<String, String> fmap = new HashMap<String, String>();
				// for (int i = 0; i < cell.length; i++) {
				// if (!fGet[i].getName().equals(cell[i].getContents())) {
				// return null;
				// }
				// }
				for (Field ff : fGet) {
					fmap.put(ff.getName(),
							ff.getType().toString().replaceAll("class ", ""));
				}
				for (int i = 1; i < sheet.getRows(); i++) {
					// 反射创建对象
					Object obj = t.newInstance();
					Cell[] cRow = sheet.getRow(i);
					for (int j = 0; j < cRow.length; j++) {
						PropertyDescriptor pd = new PropertyDescriptor(
								key.get(cell[j].getContents()), t);
						Method wm = pd.getWriteMethod();
						Constructor con = Class.forName(
								fmap.get(key.get(cell[j].getContents())))
								.getConstructor(String.class);
						wm.invoke(obj, con.newInstance(cRow[j].getContents()));
					}
					list.add((T) obj);
				}
				// 关闭流
				book.close();
				return list;
			}
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			// 关闭流
			book.close();
		}
	}
}
