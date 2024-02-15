// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class RealtimeNotification(var channel: Channel?) {
	constructor() : this(channel = null)

	/**
	 * Configuration parameters for the current connect message.
	 */
	var advice: Advice? = null

	/**
	 * Unique client ID generated by the server during handshake. Required for all other operations.
	 */
	var clientId: String? = null

	/**
	 * Selected connection type.
	 */
	var connectionType: String? = null

	/**
	 * List of notifications from the channel.
	 */
	var data: Data? = null

	/**
	 * Operation failure reason (only present if the operation was not successful).
	 */
	var error: String? = null

	/**
	 * Authentication object passed to handshake (only over WebSockets).
	 */
	var ext: Ext? = null

	/**
	 * ID of the message passed in a request. Required to match the response message.
	 */
	var id: String? = null

	/**
	 * Minimum server-side Bayeux protocol version required by the client (in a request) or minimum client-side Bayeux protocol version required by the server (in a response).
	 */
	var minimumVersion: String? = null

	/**
	 * Name of the channel to subscribe to. Subscription channels are available for [Alarms](#tag/Alarm-notification-API), [Device control](#tag/Device-control-notification-API), [Events](#tag/Event-notification-API), [Inventory](#tag/Inventory-notification-API) and [Measurements](#tag/Measurement-notification-API).
	 */
	var subscription: String? = null

	/**
	 * Indicates if the operation was successful.
	 */
	var successful: Boolean? = null

	/**
	 * Connection types supported by both client and server, that is, intersection between client and server options.
	 */
	var supportedConnectionTypes: Array<String>? = null

	/**
	 * [Bayeux protocol](https://docs.cometd.org/current/reference/#_concepts_bayeux_protocol) version used by the client (in a request) or server (in a response).
	 */
	var version: String? = null

	/**
	 * The channel name as a URI.
	 */
	enum class Channel(val value: String) {
		@SerializedName(value = "/meta/handshake")
		METAHANDSHAKE("/meta/handshake"),
		@SerializedName(value = "/meta/subscribe")
		METASUBSCRIBE("/meta/subscribe"),
		@SerializedName(value = "/meta/unsubscribe")
		METAUNSUBSCRIBE("/meta/unsubscribe"),
		@SerializedName(value = "/meta/connect")
		METACONNECT("/meta/connect"),
		@SerializedName(value = "/meta/disconnect")
		METADISCONNECT("/meta/disconnect")
	}

	/**
	 * Configuration parameters for the current connect message.
	 */
	class Advice {
	
		/**
		 * Period (milliseconds) after which the server will close the session, if it doesn't received the next connect message from the client. Overrides server default settings for current request-response conversation.
		 */
		var interval: Int? = null
	
		/**
		 * Interval (milliseconds) between the sending of the connect message and the response from the server. Overrides server default settings for the current request-response conversation.
		 */
		var timeout: Int? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * List of notifications from the channel.
	 */
	class Data {
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Authentication object passed to handshake (only over WebSockets).
	 */
	class Ext {
	
		@SerializedName(value = "com.cumulocity.authn")
		var comcumulocityauthn: Comcumulocityauthn? = null
	
		/**
		 * The system of units to use.
		 */
		var systemOfUnits: SystemOfUnits? = null
	
		/**
		 * The system of units to use.
		 */
		enum class SystemOfUnits(val value: String) {
			@SerializedName(value = "imperial")
			IMPERIAL("imperial"),
			@SerializedName(value = "metric")
			METRIC("metric")
		}
	
		class Comcumulocityauthn {
		
			/**
			 * Base64 encoded credentials.
			 */
			var token: String? = null
		
			/**
			 * Optional two factor authentication token.
			 */
			var tfa: String? = null
		
			/**
			 * Required for OAuth authentication.
			 */
			var xsrfToken: String? = null
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
