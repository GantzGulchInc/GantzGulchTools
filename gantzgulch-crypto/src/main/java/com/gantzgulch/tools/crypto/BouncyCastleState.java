package com.gantzgulch.tools.crypto;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class BouncyCastleState {

	private static volatile boolean isInitialized = false;
	
	private BouncyCastleState() {
		throw new UnsupportedOperationException();
	}
	
	public synchronized static void init() {
	
		
		if( isInitialized ){
			return;
		}
		
		Security.addProvider( new BouncyCastleProvider() );
		
		isInitialized = true;
		
		return;
	}
}
