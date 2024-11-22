// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.annotations.SerializedName

/**
 * The current status of connection, one of `CONNECTED`, `DISCONNECTED`, `MAINTENANCE`.
 */
enum class C8yAvailabilityConnectionStatus(val value: String) {
	@SerializedName(value = "CONNECTED")
	CONNECTED("CONNECTED"),
	@SerializedName(value = "DISCONNECTED")
	DISCONNECTED("DISCONNECTED"),
	@SerializedName(value = "MAINTENANCE")
	MAINTENANCE("MAINTENANCE")
}
