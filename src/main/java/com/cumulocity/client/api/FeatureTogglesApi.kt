// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.FeatureToggleValue
import com.cumulocity.client.model.FeatureToggle
import com.cumulocity.client.model.TenantFeatureToggleValue

interface FeatureTogglesApi {

	companion object Factory {
		fun create(baseUrl: String): FeatureTogglesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): FeatureTogglesApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(FeatureTogglesApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve list of feature toggles with values for current tenant.
	 * 
	 * Retrieve a list of all defined feature toggles with values calculated for a tenant of authenticated user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  none, any authenticated user can call this endpoint 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggles are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/features")
	fun listCurrentTenantFeatures(
	): Call<Array<FeatureToggle>>
	
	/**
	 * Retrieve a specific feature toggle with value for current tenant.
	 * 
	 * Retrieve a specific feature toggles defined under given key, with value calculated for a tenant of authenticated user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  none, any authenticated user can call this endpoint 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggle is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param featureKey
	 * A unique key of the feature toggle.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/features/{featureKey}")
	fun getCurrentTenantFeature(
		@Path("featureKey") featureKey: String
	): Call<FeatureToggle>
	
	/**
	 * Retrieve list of feature toggles values overrides of all tenants.
	 * 
	 * Retrieve a list of all existing feature toggle value overrides for all tenants.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* current tenant is management 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggles are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param featureKey
	 * A unique key of the feature toggle.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/features/{featureKey}/by-tenant")
	fun listTenantFeatureToggleValues(
		@Path("featureKey") featureKey: String
	): Call<Array<TenantFeatureToggleValue>>
	
	/**
	 * Sets the value of feature toggle override for the current tenant.
	 * 
	 * Sets the value of feature toggle override for a tenant of authenticated user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* (current tenant is management *OR* the feature toggle phase is PUBLIC_PREVIEW or GENERALLY_AVAILABLE) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggle value override was set.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param featureKey
	 * A unique key of the feature toggle.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/features/{featureKey}/by-tenant")
	fun setCurrentTenantFeatureToggleValue(
		@Body body: FeatureToggleValue, 
		@Path("featureKey") featureKey: String
	): Call<ResponseBody>
	
	/**
	 * Removes the feature toggle override for the current tenant.
	 * 
	 * Removes the feature toggle override for a tenant of authenticated user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* (current tenant is management *OR* the feature toggle phase is PUBLIC_PREVIEW or GENERALLY_AVAILABLE) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggle value override was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param featureKey
	 * A unique key of the feature toggle.
	 */
	@Headers("Accept:application/json")
	@DELETE("/features/{featureKey}/by-tenant")
	fun unsetCurrentTenantFeatureToggleValue(
		@Path("featureKey") featureKey: String
	): Call<ResponseBody>
	
	/**
	 * Sets the value of feature toggle override for a given tenant.
	 * 
	 * Sets the value of feature toggle override for a given tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* current tenant is management. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggle value override was set.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param featureKey
	 * A unique key of the feature toggle.
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/features/{featureKey}/by-tenant/{tenantId}")
	fun setGivenTenantFeatureToggleValue(
		@Body body: FeatureToggleValue, 
		@Path("featureKey") featureKey: String, 
		@Path("tenantId") tenantId: String
	): Call<ResponseBody>
	
	/**
	 * Removes the feature toggle override for a given tenant.
	 * 
	 * Removes the feature toggle override for a given tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* current tenant is management. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the feature toggle value override was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param featureKey
	 * A unique key of the feature toggle.
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/json")
	@DELETE("/features/{featureKey}/by-tenant/{tenantId}")
	fun unsetGivenTenantFeatureToggleValue(
		@Path("featureKey") featureKey: String, 
		@Path("tenantId") tenantId: String
	): Call<ResponseBody>
}
