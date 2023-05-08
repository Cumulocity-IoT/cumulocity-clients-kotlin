// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The signed verification code to prove the user's possession of the certificate.
 */
class UploadedTrustedCertSignedVerificationCode {

	/**
	 * A signed verification code that proves the right to use the certificate.
	 */
	var proofOfPossessionSignedVerificationCode: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
