// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class FeatureToggle {

	/**
	 * A unique key of the feature toggle.
	 */
	var key: String? = null

	/**
	 * Current phase of feature toggle rollout.
	 */
	var phase: Phase? = null

	/**
	 * Current value of the feature toggle marking whether the feature is active or not.
	 */
	var active: Boolean? = null

	/**
	 * The source of the feature toggle value - either it's feature toggle definition provided default, or per tenant provided override.
	 */
	var strategy: Strategy? = null

	/**
	 * Current phase of feature toggle rollout.
	 */
	enum class Phase(val value: String) {
		@SerializedName(value = "IN_DEVELOPMENT")
		INDEVELOPMENT("IN_DEVELOPMENT"),
		@SerializedName(value = "PRIVATE_PREVIEW")
		PRIVATEPREVIEW("PRIVATE_PREVIEW"),
		@SerializedName(value = "PUBLIC_PREVIEW")
		PUBLICPREVIEW("PUBLIC_PREVIEW"),
		@SerializedName(value = "GENERALLY_AVAILABLE")
		GENERALLYAVAILABLE("GENERALLY_AVAILABLE")
	}

	/**
	 * The source of the feature toggle value - either it's feature toggle definition provided default, or per tenant provided override.
	 */
	enum class Strategy(val value: String) {
		@SerializedName(value = "DEFAULT")
		DEFAULT("DEFAULT"),
		@SerializedName(value = "TENANT")
		TENANT("TENANT")
	}



	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
