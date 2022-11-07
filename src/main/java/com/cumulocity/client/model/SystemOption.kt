// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * A tuple storing tenant configuration.
 */
class SystemOption {

	/**
	 * Name of the system option category.
	 */
	var category: String? = null

	/**
	 * A unique identifier for this system option.
	 */
	var key: String? = null

	/**
	 * Value of this system option.
	 */
	var value: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
