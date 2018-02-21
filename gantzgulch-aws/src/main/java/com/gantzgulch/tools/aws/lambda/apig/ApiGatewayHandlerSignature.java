package com.gantzgulch.tools.aws.lambda.apig;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gantzgulch.tools.common.logging.GGLogger;

public class ApiGatewayHandlerSignature {

	@SuppressWarnings("unused")
	private static final GGLogger LOG = GGLogger.getLogger(ApiGatewayHandlerSignature.class);

	private final ApiGatewayHandlerSignatureMethod method;

	private final String resource;

	public ApiGatewayHandlerSignature(//
			final ApiGatewayHandlerSignatureMethod method, //
			final String resource) {

		this.resource = resource;
		this.method = method;
	}

	public ApiGatewayHandlerSignatureMethod getMethod() {
		return method;
	}

	public String getResource() {
		return resource;
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final ApiGatewayHandlerSignature rhs = (ApiGatewayHandlerSignature) obj;

		return new EqualsBuilder(). //
				append(this.method, rhs.method). //
				append(this.resource, rhs.resource). //
				isEquals();

	}

	@Override
	public int hashCode() {

		return new HashCodeBuilder(). //
				append(this.method). //
				append(this.resource). //
				toHashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
