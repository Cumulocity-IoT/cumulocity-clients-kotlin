// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class TenantTfaStrategy(var strategy: Strategy?) {
	constructor() : this(strategy = null)

	/**
	 * Two-factor authentication strategy.
	 */
	enum class Strategy(val value: String) {
		@SerializedName(value = "SMS")
		SMS("SMS"),
		@SerializedName(value = "TOTP")
		TOTP("TOTP")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
