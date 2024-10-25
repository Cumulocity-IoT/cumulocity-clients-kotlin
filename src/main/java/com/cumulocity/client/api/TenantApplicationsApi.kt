// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.SubscribedApplicationReference
import com.cumulocity.client.model.ApplicationReferenceCollection
import com.cumulocity.client.model.ApplicationReference

/**
 * References to the tenant subscribed applications.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface TenantApplicationsApi {

	companion object Factory {
		fun create(baseUrl: String): TenantApplicationsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): TenantApplicationsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(TenantApplicationsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve subscribed applications
	 * 
	 * Retrieve the tenant subscribed applications by a given tenant ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_READ *OR* ROLE_TENANT_ADMIN) *AND* (the current tenant is its parent *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the tenant applications are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * 
	 * **ⓘ Info:** To improve performance, the `totalElements` statistics are cached for 10 seconds.
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * 
	 * **ⓘ Info:** To improve performance, the `totalPages` statistics are cached for 10 seconds.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationreferencecollection+json")
	@GET("/tenant/tenants/{tenantId}/applications")
	fun getSubscribedApplications(
		@Path("tenantId") tenantId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ApplicationReferenceCollection>
	
	/**
	 * Subscribe to an application
	 * 
	 * Subscribe a tenant (by a given ID) to an application.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  1. the current tenant is application owner and has the role ROLE_APPLICATION_MANAGEMENT_ADMIN *OR*
	 *  2. for applications that are not microservices, the current tenant is the management tenant or the parent of the application owner tenant, and the user has one of the follwoing roles: ROLE_TENANT_MANAGEMENT_ADMIN, ROLE_TENANT_MANAGEMENT_UPDATE *OR*
	 *  3. for microservices, the current tenant is the management tenant or the parent of the application owner tenant, and the user has the role ROLE_TENANT_MANAGEMENT_ADMIN OR ROLE_TENANT_MANAGEMENT_UPDATE and one of following conditions is met:
	 *  * the microservice has no manifest
	 *  * the microservice version is supported
	 *  * the current tenant is subscribed to 'feature-privileged-microservice-hosting' 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A tenant was subscribed to an application.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Application not found.
	 * * HTTP 409 The application is already assigned to the tenant.
	 * * HTTP 422 Unprocessable Entity ��� invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.applicationreference+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationreference+json"]) 
	@POST("/tenant/tenants/{tenantId}/applications")
	fun subscribeApplication(
		@Body body: SubscribedApplicationReference, 
		@Path("tenantId") tenantId: String
	): Call<ApplicationReference>
	
	/**
	 * Unsubscribe from an application
	 * 
	 * Unsubscribe a tenant (by a given tenant ID) from an application (by a given application ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_APPLICATION_MANAGEMENT_ADMIN *AND* is the application owner *AND* is the current tenant) *OR*
	 *  ((ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_UPDATE) *AND* (the current tenant is its parent *OR* is the management tenant)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A tenant was unsubscribed from an application.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param applicationId
	 * Unique identifier of the application.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/tenants/{tenantId}/applications/{applicationId}")
	fun unsubscribeApplication(
		@Path("tenantId") tenantId: String, 
		@Path("applicationId") applicationId: String
	): Call<ResponseBody>
}
