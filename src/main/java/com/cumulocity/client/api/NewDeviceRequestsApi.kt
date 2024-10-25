// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.NewDeviceRequest
import com.cumulocity.client.model.NewDeviceRequestCollection

/**
 * API methods to create, retrieve, update and delete new device requests in Cumulocity IoT.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface NewDeviceRequestsApi {

	companion object Factory {
		fun create(baseUrl: String): NewDeviceRequestsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): NewDeviceRequestsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(NewDeviceRequestsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve a list of new device requests
	 * 
	 * Retrieve a list of new device requests.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_CONTROL_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the list of new device requests sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
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
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.newdevicerequestcollection+json")
	@GET("/devicecontrol/newDeviceRequests")
	fun getNewDeviceRequests(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<NewDeviceRequestCollection>
	
	/**
	 * Create a new device request
	 * 
	 * Create a new device request.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_CONTROL_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A new device request was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 422 Unprocessable Entity ��� invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.newdevicerequest+json", "Accept:application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json"]) 
	@POST("/devicecontrol/newDeviceRequests")
	@ReadOnlyProperties("owner", "securityToken", "creationTime", "tenantId", "self", "status")
	fun createNewDeviceRequest(
		@Body body: NewDeviceRequest, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<NewDeviceRequest>
	
	/**
	 * Retrieve a specific new device request
	 * 
	 * Retrieve a specific new device request (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_CONTROL_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the new device request is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 New device request not found.
	 * 
	 * @param requestId
	 * Unique identifier of the new device request.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/devicecontrol/newDeviceRequests/{requestId}")
	fun getNewDeviceRequest(
		@Path("requestId") requestId: String
	): Call<NewDeviceRequest>
	
	/**
	 * Update a specific new device request status
	 * 
	 * Update a specific new device request (by a given ID).You can only update its status.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_CONTROL_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 A new device request was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 New device request not found.
	 * 
	 * @param body
	 * @param requestId
	 * Unique identifier of the new device request.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.newdevicerequest+json", "Accept:application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json"]) 
	@PUT("/devicecontrol/newDeviceRequests/{requestId}")
	@ReadOnlyProperties("owner", "creationTime", "groupId", "tenantId", "self", "id", "type")
	fun updateNewDeviceRequest(
		@Body body: NewDeviceRequest, 
		@Path("requestId") requestId: String
	): Call<NewDeviceRequest>
	
	/**
	 * Delete a specific new device request
	 * 
	 * Delete a specific new device request (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A new device request was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 New device request not found.
	 * 
	 * @param requestId
	 * Unique identifier of the new device request.
	 */
	@Headers("Accept:application/json")
	@DELETE("/devicecontrol/newDeviceRequests/{requestId}")
	fun deleteNewDeviceRequest(
		@Path("requestId") requestId: String
	): Call<ResponseBody>
}
