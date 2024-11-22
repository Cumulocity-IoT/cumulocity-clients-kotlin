// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class ManagedObjectAvailability {

	/**
	 * Identifier of the target device.
	 */
	var deviceId: String? = null

	/**
	 * The identifier used in the external system that Cumulocity IoT interfaces with.
	 */
	var externalId: String? = null

	/**
	 * The time when the device sent the last message to Cumulocity IoT.
	 */
	var lastMessage: String? = null

	/**
	 * Required interval of monitored device
	 */
	var interval: Int? = null

	/**
	 * The current status of availability, one of `AVAILABLE`, `UNAVAILABLE`, `MAINTENANCE`.
	 */
	var dataStatus: C8yAvailabilityDataStatus? = null

	/**
	 * The current status of connection, one of `CONNECTED`, `DISCONNECTED`, `MAINTENANCE`.
	 */
	var connectionStatus: C8yAvailabilityConnectionStatus? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
