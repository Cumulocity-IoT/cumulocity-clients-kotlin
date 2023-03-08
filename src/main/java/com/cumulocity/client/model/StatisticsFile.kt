// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Statistics file metadata.
 */
class StatisticsFile {

	/**
	 * Unique identifier of the file.
	 */
	var id: String? = null

	/**
	 * Domain name where the statistics file come from.
	 */
	var instanceName: String? = null

	/**
	 * File generation date.
	 */
	var generationDate: String? = null

	/**
	 * Start date or date and time of the statistics attached in the file.
	 */
	var dateFrom: String? = null

	/**
	 * End date or date and time of the statistics attached in the file.
	 */
	var dateTo: String? = null

	/**
	 * The type of statistics:
	 * 
	 * * REAL - generated by the system on the first day of the month and including statistics from the previous month.
	 * * TEST - generated by the user with a time range specified in the query parameters (`dateFrom`, `dateTo`).
	 */
	var type: Type? = null

	/**
	 * The type of statistics:
	 * 
	 * * REAL - generated by the system on the first day of the month and including statistics from the previous month.
	 * * TEST - generated by the user with a time range specified in the query parameters (`dateFrom`, `dateTo`).
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "REAL")
		REAL("REAL"),
		@SerializedName(value = "TEST")
		TEST("TEST")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
