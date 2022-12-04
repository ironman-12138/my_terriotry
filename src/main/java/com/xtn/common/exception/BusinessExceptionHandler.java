
package com.xtn.common.exception;

import com.xtn.common.base.ResultCode;
import com.xtn.common.base.Result;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Objects.requireNonNull;

/**
 * 异常处理器
 *
 * @author xCoder
 */
@RestControllerAdvice
public class BusinessExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public Result<T> handleBusinessException(BusinessException e){
		Result<T> r = new Result<>();
		r.setCode(e.getCode());
		r.setMsg(e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result<T> handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Result.error(ResultCode.BUSINESS_ERROR, "数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public Result<T> handleException(Exception e){
		logger.error(e.getMessage(), e);
		Result<T> r = new Result<>();
		r.setCode(ResultCode.UN_KNOW_ERROR);
		r.setMsg(e.getMessage());
		return r;
	}

	/**
	 * 处理接口参数异常
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	public Result<String> handleMethodArgumentNotValidException(Exception e) {
		try {
			String errorMsg;
			if (e instanceof MethodArgumentNotValidException) {
				errorMsg = requireNonNull(((MethodArgumentNotValidException) e).getBindingResult().getFieldError()).getDefaultMessage();
			} else {
				errorMsg = requireNonNull(((BindException) e).getBindingResult().getFieldError()).getDefaultMessage();
			}

			logger.error("参数异常:{}", errorMsg);
			return Result.error(ResultCode.PARAM_ERROR, errorMsg);
		} catch (Exception ex) {
			logger.error("系统异常：system error:{}", e.toString());
			e.printStackTrace();
			return Result.error(ResultCode.PARAM_ERROR, ResultCode.PARAM_ERROR_DESC);
		}
	}
}
