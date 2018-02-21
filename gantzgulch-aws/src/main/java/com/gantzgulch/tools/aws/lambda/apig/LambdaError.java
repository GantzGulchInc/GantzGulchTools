package com.gantzgulch.tools.aws.lambda.apig;

public interface LambdaError {

	int getHttpStatusCode();
	
	String getBody();
	
}
