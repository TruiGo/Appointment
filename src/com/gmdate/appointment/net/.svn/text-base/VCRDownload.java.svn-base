package com.gmdate.appointment.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.Build;

import com.gmdate.appointment.common.Mylog;
import com.xiaotian.framework.util.UtilExternalStore;
import com.xiaotian.frameworkxt.util.UtilFile;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name VCRDownload
 * @description 媒体下载
 * @date 2015-3-13
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class VCRDownload extends RequestAsyncTask {
	private final int IO_BUFFER_SIZE = 8 * 1024;
	private UtilExternalStore mUtilExternalStore;
	private Context mContext;
	private File outPutFolder;

	public VCRDownload(Context context) {
		mContext = context;
		mUtilExternalStore = new UtilExternalStore(mContext);
		outPutFolder = new File(mUtilExternalStore.getExternalDirectory(), com.gmdate.appointment.common.Constant.CACHE_PATH_VCR);
		if (!outPutFolder.exists()) outPutFolder.mkdirs();
	}

	public String downloadFile(String path) throws HttpException {
		String fileName = UtilFile.getInstance().getFilename(path);
		disableConnectionReuseIfNecessary(); // 关闭网络持续连接属性,适用SDK 2.2以下版本
		HttpURLConnection urlConnection = null;
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		OutputStream outputStream = null;
		File outFile = new File(outPutFolder, fileName);
		try {
			if (outFile.exists()) {
				return outFile.getAbsolutePath();
			} else {
				outFile.createNewFile();
			}
			outputStream = new FileOutputStream(outFile);
			final URL url = new URL(path); // URL访问资源
			urlConnection = (HttpURLConnection) url.openConnection(); // 打开连接,默认GET
			in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE); // 输入流
			out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);// 缓冲输出流,接入传入的输出流中
			int hasReaded;
			while ((hasReaded = in.read()) != -1) { // 读取数据
				out.write(hasReaded); // 写入读取数据
			}
			Mylog.info(url);
			Mylog.info(outFile.getAbsolutePath());
			return outFile.getAbsolutePath();
		} catch (final IOException e) {
			e.printStackTrace();
			if (outFile != null) outFile.deleteOnExit();
			throw new HttpException(e);
		} catch (final Exception e) {
			e.printStackTrace();
			if (outFile != null) outFile.deleteOnExit();
			throw new HttpException(e);
		} finally {
			if (urlConnection != null) urlConnection.disconnect();
			try {
				if (out != null) out.close();
				if (in != null) in.close();
			} catch (final IOException e) {
				Mylog.printStackTrace(e);
			}
		}
	}

	public void disableConnectionReuseIfNecessary() {
		// SDK 小于2.2
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false"); // 当前SDK系统属性
		}
	}
}
