// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * A tuple storing tenant configuration.
 */
class Option {

	/**
	 * Name of the option category.
	 */
	var category: String? = null

	/**
	 * A unique identifier for this option.
	 */
	var key: String? = null

	/**
	 * Value of this option.
	 */
	var value: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
