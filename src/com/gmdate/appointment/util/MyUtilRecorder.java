package com.gmdate.appointment.util;

import java.io.IOException;

import android.media.MediaRecorder;

import com.xiaotian.framework.common.Mylog;
import com.xiaotian.framework.util.UtilRecorder;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name MyUtilRecorder
 * @description
 * @date 2015-3-12
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class MyUtilRecorder extends UtilRecorder {

	public void startRecording(String saveFile) {
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mediaRecorder.setAudioSamplingRate(SAMPLING_RATE);
		mediaRecorder.setOutputFile(saveFile);
		try {
			mediaRecorder.prepare();
		} catch (IOException e) {
			e.printStackTrace();
			Mylog.info("UtilRecorderRadio", "mediaRecorder prepare.");
		}
		mediaRecorder.start();
	}
}
