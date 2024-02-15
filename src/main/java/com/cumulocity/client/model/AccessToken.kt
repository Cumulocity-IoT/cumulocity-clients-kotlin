// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * OAI-Secure access token.
 */
class AccessToken {

	/**
	 * Access token generated by the Cumulocity IoT platform.
	 */
	@SerializedName(value = "access_token")
	var accessToken: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
