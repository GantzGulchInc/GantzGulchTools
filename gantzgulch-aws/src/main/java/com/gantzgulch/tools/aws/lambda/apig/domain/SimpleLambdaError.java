package com.gantzgulch.tools.aws.lambda.apig.domain;

import com.gantzgulch.tools.aws.lambda.apig.LambdaError;
import com.gantzgulch.tools.common.json.GGJsonWriter;

public class SimpleLambdaError implements LambdaError {

	private final int httpStatusCode;
	
	private final String body;

	public SimpleLambdaError(final int httpStatusCode, final Object body) {
		this.httpStatusCode = httpStatusCode;
		this.body = formatBody(body);
	}
	
	@Override
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	@Override
	public String getBody() {
		return body;
	}
	
	public static String formatBody(final Object body){
		
		try {
			return GGJsonWriter.STRICT.writeAsString(body);
		}catch(final RuntimeException e){
			return "";
		}
	}

}
