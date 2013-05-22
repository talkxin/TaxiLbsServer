package com.taxi.admin.common;

import java.text.NumberFormat;

/**
 * 
 * Description.��ʽ������
 * <p>
 * Copyright: Copyright (c) 2012-2-28 ����12:48:59
 * <p>
 * Company: ��������ʮ�����ּ������޹�˾
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class DataFormat {
	/** ��ʽ�� */
	private final static NumberFormat numFormat = NumberFormat.getInstance();

	/** 1K */
	public final static long ONE_K = 1 * 1024L;
	/** 1M */
	public final static long ONE_M = 1 * 1024 * 1024L;
	/** ʵ�� */
	private static DataFormat instance;

	/**
	 * ����ʵ��,����С������������
	 * 
	 * @return
	 */
	public synchronized static DataFormat getInstance() {
		if (instance == null) {
			instance = new DataFormat();
			// ��ȷ��С������λ
			numFormat.setMaximumFractionDigits(2);
		}

		return instance;
	}

	/**
	 * ��������ӵ�λ
	 * <p>
	 * �ֽڻ���Ϊ��
	 * 
	 * @param lon
	 *            �ֽ�
	 * @return ���ӵ�λ������
	 */
	public String byteToM(Long lon) {

		if (lon > 0) {
			return numFormat.format((double) lon / (ONE_M));
		}
		return "0";
	}

	/**
	 * ������ ��ʽ��
	 * <p>
	 * �����Ľ���ʽ��Ϊ����С������λ
	 * 
	 * @param first
	 *            ������
	 * @param last
	 *            ����
	 * @return ��ʽ����Ľ��
	 */
	public String getDivide2(long first, long last) {
		if (last == 0L) {
			throw new ArithmeticException("������Ϊ0��");
		}
		return numFormat.format((double) first / last);
	}
}
