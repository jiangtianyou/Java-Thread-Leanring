package com.jty.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * JSON工具类，使用jackson库
 */
@Slf4j
public class JsonUtils {

	private static ObjectMapper mapper = null;

	private static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			// 忽略目标对象没有的属性
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return mapper;
	}

	public static <T> T fromJson(String jsonString, Class<T> type) {
		try {
			return getMapper().readValue(jsonString, type);
		} catch (IOException e) {
			log.error("fromJson error :" + e.toString());
			log.error(errorTrackSpace(e));
			return null;
		}
	}

	public static <T> T fromJson(String jsonString, TypeReference<T> valueTypeRef) {
		try {
			return getMapper().readValue(jsonString, valueTypeRef);
		} catch (IOException e) {
			log.error("fromJson error :" + e.toString());
			log.error(errorTrackSpace(e));
			return null;
		}
	}

	public static String toJson(Object bean) {
		try {
			return getMapper().writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			log.error("toJson error :" + e.toString());
			log.error(errorTrackSpace(e));
			return StringUtils.EMPTY;
		}
	}

	public static String toPrettyJson(Object bean) {
		try {
			return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			log.error("toPrettyJson error :" + e.toString());
			log.error(errorTrackSpace(e));
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 输出异常信息
	 */
	private static String errorTrackSpace(Exception e) {
		StringBuffer sb = new StringBuffer();
		if (e != null) {
			for (StackTraceElement element : e.getStackTrace()) {
				sb.append("\r\n\t").append(element);
			}
		}
		return sb.length() == 0 ? "" : sb.toString();
	}
}
