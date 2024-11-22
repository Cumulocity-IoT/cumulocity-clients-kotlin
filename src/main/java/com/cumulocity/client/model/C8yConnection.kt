// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The connection information computed by Cumulocity IoT is stored in fragments `c8y_Connection` of the device.
 */
class C8yConnection {

	/**
	 * The current status of connection, one of `CONNECTED`, `DISCONNECTED`, `MAINTENANCE`.
	 */
	var status: C8yAvailabilityConnectionStatus? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
