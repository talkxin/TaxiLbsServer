package com.taxi.admin.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;


/**
 * 文件基本操作类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-6-21 下午02:16:10
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: liqi@c-platform.com<br>
 * History: 2011-8-3 下午01:42:49 zhangrui@c-platform.com <br>
 * 修改方法：writeTemplateFileWithoutStruts2Tag（调整加入手机分页标签引用的位置）
 * <p>
 * Version: 1.0
 * <p>
 */
public class FileUtils {

	private static Logger log = Logger.getLogger("FtpInfo");

	/**
	 * 文件拷贝
	 * 
	 * @param sourceFile
	 *            被拷贝文件的路径及文件名
	 * @param aimFile
	 *            文件拷贝的路径及文件名
	 * @return
	 */
	public static boolean copyFile(File sourceFile, File aimFile) {
		boolean isOK = false;
		// 最大可以拷贝2M文件-内存保存的大小
		int large = 2097152;

		FileInputStream in;
		FileOutputStream out;

		try {
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(aimFile);

			FileChannel inCannel = in.getChannel();
			FileChannel outCannel = out.getChannel();

			ByteBuffer buf = null;

			while (true) {
				if (inCannel.position() == inCannel.size()) {
					inCannel.close();
					outCannel.close();
					return true;
				}
				if ((inCannel.size() - inCannel.position()) < large) {
					large = (int) (inCannel.size() - inCannel.position());
				} else {
					large = 2097152;
				}

				buf = ByteBuffer.allocate(large);
				inCannel.read(buf);
				buf.flip();
				outCannel.write(buf);
				outCannel.force(false);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return isOK;
		}
	}

	/**
	 * 文件夹对文件夹拷贝
	 * 
	 * @param sourceFilePath
	 * @param aimFilePath
	 * @return
	 */
	public static boolean copyFileAllDirectory(String sourceFilePath, String aimFilePath) {
		String sourceFPath = getDirectoryPath(sourceFilePath);
		String aimiFPath = getDirectoryPath(aimFilePath);
		File aimfileDirectory = new File(aimiFPath);
		if (!aimfileDirectory.exists()) {
			try {
				if (aimfileDirectory.mkdirs()) {
					log.info("拷贝文件路径不存在，创建目录: " + aimiFPath);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<String> fileList = listFileName(sourceFPath);
		if (fileList != null && fileList.size() > 0) {
			for (String fName : fileList) {
				copyFile(new File(sourceFPath + fName), new File(aimFilePath + fName));
			}
		}
		return true;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            传入参数是包含保存路径和文件名的字符串
	 * @return boolean 是否成功
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			boolean isDel = file.delete();
			if (isDel) {
				log.info("删除了文件：" + fileName);
			} else {
				log.info("删除文件：" + fileName + "失败！");
			}
		}
	}

	/**
	 * 根据主题Id获得黑名单文件
	 * 
	 * @param themeId
	 *            主题Id
	 * @return 黑名单文件
	 */
	public static File getBlacklistFileByThemeId(int themeId) {
		// 路径
		StringBuffer tmpPath = new StringBuffer();
		tmpPath.append(getWebReleasePath());
		tmpPath.append("release");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("blacklist");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append(themeId);
		tmpPath.append(".txt");
		// 文件
		File tmpFile = new File(tmpPath.toString());
		if (!tmpFile.exists()) {
			try {
				tmpFile.createNewFile();
			}
			catch (IOException e) {
				log.debug("创建黑名单文件出现异常");
			}
		}
		return tmpFile;
	}

	/**
	 * 获得存在黑名单的主题存放文件
	 * 
	 * @return 主题Id所存放的文件
	 */
	public static File getBlackThemeFile() {
		// 路径
		StringBuffer tmpPath = new StringBuffer();
		tmpPath.append(getWebReleasePath());
		tmpPath.append("release");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("blacklist");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("themeList.txt");
		// 文件
		File tmpFile = new File(tmpPath.toString());
		if (!tmpFile.exists()) {
			try {
				tmpFile.createNewFile();
			}
			catch (IOException e) {
				log.debug("创建黑名单主题列表文件出现异常");
			}
		}
		return tmpFile;
	}

	/**
	 * 格式化路径，将所有路径后都增加 “\”
	 * 
	 * @param path
	 * @return
	 */
	public static String getDirectoryPath(String path) {
		if (path.substring(path.length() - 1).equals("/") || path.substring(path.length() - 1).equals("\\")) {
			path = path.substring(0, path.length() - 2);
		}
		return path + System.getProperty("file.separator");
	}

	/**
	 * 格式化路径，将所有路径后都增加 “\”
	 * 
	 * @param path
	 * @return
	 */
	public static String getDirectoryPath2(String path) {
		if (path.substring(path.length() - 1).equals("/") || path.substring(path.length() - 1).equals("\\")) {
			path = path.substring(0, path.length() - 1);
		}
		return path + System.getProperty("file.separator");
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param file
	 *            文件
	 * @return 文件拓展名
	 */
	public static String getExtFileName(File file) {
		// 文件名称
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 获得文件对应日期
	 * 
	 * @param file
	 *            文件
	 * @return 日期
	 */
	public static String getFileDate(File file) {
		// 文件名称
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("_") + 9);
	}

	/**
	 * 获得文件开头名字
	 * 
	 * @return 文件开头名字
	 */
	public static String getFileHeadName(File file) {
		// 文件名称
		String fileName = file.getName();
		return fileName.substring(0, fileName.lastIndexOf("_"));
	}

	/**
	 * 获得疯抢流入记录文件
	 * 
	 * @param sysDate
	 *            当前日期
	 * @return 疯抢流水日志文件
	 */
	public static File getSnatchFollowFile(String sysDate) {
		// 文件路径
		String path = getWebReleasePath() + "release" + System.getProperty("file.separator") + "snatchFollow" + System.getProperty("file.separator")
		        + sysDate + ".txt";
		// 文件
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 获得疯抢状态所放在的文件
	 * 
	 * @param themeId
	 *            主题ID
	 * @return 路径
	 */
	public static String getSnatchStutusFile(int themeId) {
		// 文件路径
		String tmpPath = getWebReleasePath() + "release" + System.getProperty("file.separator") + themeId + System.getProperty("file.separator")
		        + "snatchStatus.txt";
		// 文件
		File tmpFile = new File(tmpPath);
		if (!tmpFile.exists()) {
			try {
				tmpFile.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tmpPath;
	}

	/**
	 * 获得目标文件名称
	 * 
	 * @param file
	 *            文件
	 * @return 目标文件名称
	 */
	public static String getTargetFileName(File file) {
		// 文件名称
		String fileName = file.getName();
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获得目标文件路径
	 * 
	 * @param directory
	 *            目录
	 * @param fileName
	 *            文件名
	 * @return 目标文件路径
	 */
	public static String getTargetFilePath(String directory, String fileName) {
		return directory + System.getProperty("file.separator") + fileName;
	}

	public static String getWebReleasePath() {
		String webRootPath = "";
		try {
			String classPath = ConfigDateHandler.class.getClassLoader().getResource("").toURI().getPath();
			webRootPath = classPath.substring(0, classPath.indexOf("WEB-INF"));
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return webRootPath;
	}

	/**
	 * 根据主题Id获得白名单文件
	 * 
	 * @param themeId
	 *            主题Id
	 * @return 白名单文件
	 */
	public static File getWhiteListFileByThemeId(int themeId) {
		// 路径
		StringBuffer tmpPath = new StringBuffer();
		tmpPath.append(getWebReleasePath());
		tmpPath.append("release");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("whiteList");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append(themeId);
		tmpPath.append(".txt");
		// 文件
		File tmpFile = new File(tmpPath.toString());
		if (!tmpFile.exists()) {
			try {
				tmpFile.createNewFile();
			}
			catch (IOException e) {
				log.debug("创建白名单文件出现异常");
			}
		}
		return tmpFile;
	}

	/**
	 * 获得存在白名单的主题存放文件
	 * 
	 * @return 主题Id所存放的文件
	 */
	public static File getWhiteThemeFile() {
		// 路径
		StringBuffer tmpPath = new StringBuffer();
		tmpPath.append(getWebReleasePath());
		tmpPath.append("release");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("whiteList");
		tmpPath.append(System.getProperty("file.separator"));
		tmpPath.append("themeList.txt");
		// 文件
		File tmpFile = new File(tmpPath.toString());
		if (!tmpFile.exists()) {
			try {
				tmpFile.createNewFile();
			}
			catch (IOException e) {
				log.debug("创建白名单主题列表文件出现异常");
			}
		}
		return tmpFile;
	}

	/**
	 * 获取某路径下的所有文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> listFileName(String filePath) {
		String path = getDirectoryPath(filePath);
		List<String> flst = new ArrayList<String>();
		File file = new File(path);
		if (file.exists()) {
			File[] f = file.listFiles();
			for (int i = 0; i < f.length; i++) {
				flst.add(f[i].getName());
			}
		} else {
			flst = null;
		}
		return flst;
	}

	/**
	 * 获取某路径下的所有文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> listFullFileName(String filePath) {
		String path = getDirectoryPath2(filePath);
		List<String> flst = new ArrayList<String>();
		File file = new File(path);
		if (file.exists()) {
			File[] f = file.listFiles();
			if (f.length > 0) {
				for (int i = 0; i < f.length; i++) {
					flst.add(path + f[i].getName());
				}
			} else {
				flst = null;
			}
		} else {
			flst = null;
		}
		return flst;
	}

	public static void main(String[] args) {
		getBlacklistFileByThemeId(22);
	}

	/**
	 * 读文件
	 * 
	 * @param fileName
	 *            传入参数是包含保存路径和文件名的字符串
	 * @return String 文件内容
	 */
	public static String readFile(String fileName) {
		String reinfo = "";
		File file = new File(fileName);
		if (!file.exists()) {
			log.info("文件不存在: " + fileName);
			return null;
		} else if (file.isDirectory()) {
			log.info("不是文件: " + fileName);
			return null;
		}
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			reader = new BufferedReader(in);
			String s = new String();
			while ((s = reader.readLine()) != null) {
				reinfo += s + "\r\n";
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return reinfo;
	}

	/**
	 * 读文件,以行为单位，寻找某行是否subStr
	 * 
	 * @param fileName
	 *            文件名
	 * @param subStr
	 *            字文件
	 * @param isContain
	 *            是否包含
	 * @return 返回
	 */
	public static String readFile(String fileName, String subStr, boolean isContain) {
		String reinfo = "";
		File file = new File(fileName);
		if (!file.exists()) {
			log.info("文件不存在: " + fileName);
			return null;
		} else if (file.isDirectory()) {
			log.info("不是文件: " + fileName);
			return null;
		}
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			reader = new BufferedReader(in);
			String s = new String();
			while ((s = reader.readLine()) != null && s.startsWith(subStr) == isContain) {
				reinfo += s + "\r\n";
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return reinfo;
	}

	/**
	 * 读文件
	 * 
	 * @param fileName
	 *            传入参数是包含保存路径和文件名的字符串
	 * @return String 文件内容
	 */
	public static String readFileContent(String fileName) {
		String reinfo = "";
		File file = new File(fileName);
		if (!file.exists()) {
			log.info("文件不存在: " + fileName);
			return null;
		} else if (file.isDirectory()) {
			log.info("不是文件: " + fileName);
			return null;
		}
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			reader = new BufferedReader(in);
			String s = new String();
			while ((s = reader.readLine()) != null) {
				reinfo = s;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return reinfo;
	}

	/**
	 * 将文件内容读成一行字符串
	 * 
	 * @param fileName
	 *            传入参数是包含保存路径和文件名的字符串
	 * @return 文件内容
	 */
	public static String readFileToOneRow(String fileName) {
		return readFileToOneRow(fileName, "UTF-8");
		// String reinfo = "";
		// File file = new File(fileName);
		// if (!file.exists()) {
		// log.info("文件不存在: " + fileName);
		// return null;
		// } else if (file.isDirectory()) {
		// log.info("不是文件: " + fileName);
		// return null;
		// }
		// BufferedReader reader = null;
		// try {
		// InputStreamReader in = new InputStreamReader(new
		// FileInputStream(fileName), "UTF-8");
		// reader = new BufferedReader(in);
		// String s = new String();
		// while ((s = reader.readLine()) != null) {
		// reinfo += s;
		// }
		// }
		// catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// finally {
		// try {
		// reader.close();
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// return reinfo;
	}

	public static String readFileToOneRow(String fileName, String txtCode) {
		String reinfo = "";
		File file = new File(fileName);
		if (!file.exists()) {
			log.info("文件不存在: " + fileName);
			return null;
		} else if (file.isDirectory()) {
			log.info("不是文件: " + fileName);
			return null;
		}
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), txtCode);
			reader = new BufferedReader(in);
			String s = new String();
			while ((s = reader.readLine()) != null) {
				reinfo += s;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return reinfo;
	}

	/**
	 * 重命名文件
	 * 
	 * @param oldFileName
	 *            旧文件名称，传入参数是包含保存路径和文件名的字符串
	 * @param newFileName
	 *            新文件名称，传入参数是包含保存路径和文件名的字符串
	 * @return boolean 是否成功
	 */
	public static void renameFile(String oldFileName, String newFileName) {
		File oldFile = new File(oldFileName);
		File newFile = new File(newFileName);
		boolean f = oldFile.renameTo(newFile);
		if (f) {
			log.info("重命名成功.文件：" + oldFileName + "重命名为" + newFileName);
		} else {
			log.warn("重命名失败.文件：" + oldFileName + "重命名为" + newFileName);
		}
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 * @param newFileName
	 *            新文件名称，传入参数是包含保存路径和文件名的字符串
	 * @return boolean 是否成功
	 */
	public static void createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				if (file.mkdirs()) {
					log.info("文件路径不存在，创建目录: " + path);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将.gz文件解压成.txt文件
	 * 
	 * @param fileArr
	 *            文件数组
	 * @throws IOException
	 */
	public static void unGzFile(File[] fileArr, String sDate, String eDate, String directory) throws IOException {
		byte[] buf = new byte[1024 * 4];
		if (fileArr != null) {
			for (File f : fileArr) {
				// 文件日期
				int tmpDate = Integer.parseInt(getFileDate(f));
				// 判断时间
				if (tmpDate >= Integer.parseInt(sDate) && tmpDate <= Integer.parseInt(eDate)) {
					// 文件拓展名
					String extendName = getExtFileName(f);
					// 判断文件是否为.gz类型
					if ("gz".equals(extendName)) {
						OutputStream out = null;
						GZIPInputStream in = null;
						try {
							in = new GZIPInputStream(new FileInputStream(f));
							// 解压后的文件名
							String tmpName = getTargetFileName(f);
							// 目标文件
							File targetFile = new File(getTargetFilePath(directory, tmpName));
							// 判断目标文件是否存在
							if (!targetFile.exists()) {
								targetFile.createNewFile();
							}
							out = new FileOutputStream(targetFile);
							int tmpLength = 0;
							while ((tmpLength = in.read(buf)) != -1) {
								out.write(buf, 0, tmpLength);
							}
						}
						catch (FileNotFoundException e) {
							throw e;
						}
						catch (IOException e) {
							throw e;
						}
						finally {
							in.close();
							out.close();
						}
					}
				}
			}
		}
	}

	/**
	 * 将.gz文件解压成.txt文件
	 * 
	 * @param fileArr
	 *            文件数组
	 * @throws IOException
	 */
	public static String unGzFile(String fileName) throws Exception {
		String unGzFileName = fileName.substring(0, fileName.lastIndexOf("."));
		File f = new File(fileName);
		byte[] buf = new byte[1024 * 4];
		OutputStream out = null;
		GZIPInputStream in = null;
		try {
			in = new GZIPInputStream(new FileInputStream(f));
			// 目标文件
			File targetFile = new File(unGzFileName);
			// 判断目标文件是否存在
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			out = new FileOutputStream(targetFile);
			int tmpLength = 0;
			while ((tmpLength = in.read(buf)) != -1) {
				out.write(buf, 0, tmpLength);
			}
		}
		catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
		finally {
			in.close();
			out.close();
		}
		return unGzFileName;
	}

	/**
	 * 写文件
	 * 
	 * @param file
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @param isReWrite
	 *            是否覆盖
	 */
	public static void writeFile(File file, String content, boolean isAdd) {
		FileOutputStream out = null;
		PrintWriter writer = null;
		try {
			out = new FileOutputStream(file, isAdd);
			writer = new PrintWriter(out);
			writer.print(content);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (out != null) {
					out.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向文件里面写内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            内容
	 */
	public static void writeFile(String filePath, String content) {
		FileOutputStream out = null;
		PrintWriter writer = null;
		try {
			out = new FileOutputStream(filePath);
			writer = new PrintWriter(out);
			writer.print(content);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (out != null) {
					out.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写文件
	 * 
	 * @param filePath
	 *            保存文件路径
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @param isOvried
	 *            是否覆盖，true为覆盖已有文件，<br>
	 *            false为在已有文件后追加新内容。
	 * @return boolean 是否成功
	 */
	public static boolean writeFile(String filePath, String fileName, String content, boolean isOvried) {

		File file = new File(filePath);
		boolean result = false;
		if (!file.exists()) {
			try {
				if (file.mkdirs()) {
					log.info("文件路径不存在，创建目录: " + filePath);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		boolean append = !isOvried;
		FileOutputStream outputStream = null;
		BufferedWriter bWriter = null;
		// 为避免分布式部署造成的文件冲突，增加对文件是否存在的判断。
		// By zhangyu@c-platform.com，2010-12-01
		File surfLogFile = new File(filePath + fileName);
		// if (surfLogFile.exists()) {// 流水日志文件存在
		try {
			outputStream = new FileOutputStream(surfLogFile, append);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream, "UTF-8");
			bWriter = new BufferedWriter(outWriter);
			if (isOvried) {

				bWriter.write(content + "\r\n");
			} else {
				bWriter.write(content);
			}

			bWriter.flush();
			log.info("文件内容写入完成：" + filePath + fileName);
			result = true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bWriter != null) {
					bWriter.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		// } else {// 流水日志不存在
		// log.info("日志文件不存在，不做处理: " + surfLogFile.getAbsolutePath());
		// result = false;
		// }
		surfLogFile = null;
		return result;
	}

	/**
	 * 写文件
	 * 
	 * @param filePath
	 *            保存文件路径
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @param isOvried
	 *            是否覆盖，true为覆盖已有文件，<br>
	 *            false为在已有文件后追加新内容。
	 * @return boolean 是否成功
	 */
	public static boolean writeFileTemplate(String filePath, String fileName, String content, boolean isOvried) {
		content = StringOperator.getHtmlHeadStr() + content;
		File file = new File(filePath);
		boolean result = false;
		if (!file.exists()) {
			try {
				if (file.mkdirs()) {
					log.info("文件路径不存在，创建目录: " + filePath);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		boolean append = !isOvried;
		FileOutputStream outputStream = null;
		BufferedWriter bWriter = null;
		try {
			outputStream = new FileOutputStream(filePath + fileName, append);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream, "UTF-8");
			bWriter = new BufferedWriter(outWriter);
			if (isOvried) {

				bWriter.write(content + "\r\n");
			} else {
				bWriter.write(content);
			}

			bWriter.flush();
			result = true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bWriter != null) {
					bWriter.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * 留言板模版用
	 * 
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @param isOvried
	 * @return
	 */
	public static boolean writeTemplateFileWithoutStruts2Tag(String filePath, String fileName, String content, boolean isOvried) {
		// 如果jsp文件里已经没有了jsp指令等东西，就给它添加上；如果有，就不重复添加，以免jsp报错！
		if (content.indexOf("<%@") == -1) {
			String wapPage = "\n<%@ taglib uri=\"/WEB-INF/page.tld\" prefix=\"wapPage\"%>";
			content = StringOperator.getHtmlHeadStr() + wapPage + content;
		}

		File file = new File(filePath);
		boolean result = false;
		if (!file.exists()) {
			try {
				if (file.mkdirs()) {
					log.info("文件路径不存在，创建目录: " + filePath);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		boolean append = !isOvried;
		FileOutputStream outputStream = null;
		BufferedWriter bWriter = null;
		try {
			outputStream = new FileOutputStream(filePath + fileName, append);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream, "UTF-8");
			bWriter = new BufferedWriter(outWriter);
			if (isOvried) {

				bWriter.write(content + "\r\n");
			} else {
				bWriter.write(content);
			}

			bWriter.flush();
			result = true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bWriter != null) {
					bWriter.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
