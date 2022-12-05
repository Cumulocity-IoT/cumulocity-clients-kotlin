// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.Operation
import com.cumulocity.client.model.OperationCollection

/**
 * API methods to create, retrieve, update and delete operations in Cumulocity IoT.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface OperationsApi {

	companion object Factory {
		fun create(baseUrl: String): OperationsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): OperationsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(OperationsApi::class.java)
		}
	}

	/**
	 * Retrieve a list of operations </br>
	 * Retrieve a list of operations.  Notes about operation collections:  * The embedded operation object contains `deviceExternalIDs` only when queried with an `agentId` parameter. * The embedded operation object is filled with `deviceName`, but only when requesting resource: Get a collection of operations. * Operations are returned in the order of their ascending IDs.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of operations is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param agentId An agent ID that may be part of the operation. If this parameter is set, the operation response objects contain the `deviceExternalIDs` object.
	 * @param bulkOperationId The bulk operation ID that this operation belongs to.
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the operation.
	 * @param dateTo End date or date and time of the operation.
	 * @param deviceId The ID of the device the operation is performed for.
	 * @param fragmentType The type of fragment that must be part of the operation.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param revert If you are using a range query (that is, at least one of the `dateFrom` or `dateTo` parameters is included in the request), then setting `revert=true` will sort the results by the newest operations first. By default, the results are sorted by the oldest operations first. 
	 * @param status Status of the operation.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operationcollection+json")
	@GET("/devicecontrol/operations")
	fun getOperations(
		@Query("agentId") agentId: String? = null, 
		@Query("bulkOperationId") bulkOperationId: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("deviceId") deviceId: String? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("revert") revert: Boolean? = null, 
		@Query("status") status: String? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<OperationCollection>
	
	/**
	 * Create an operation </br>
	 * Create an operation.  It is possible to add custom fragments to operations, for example `com_cumulocity_model_WebCamDevice` is a custom object of the webcam operation.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the device <b>OR</b> ADMIN permissions on the device </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An operation was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.operation+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json"]) 
	@POST("/devicecontrol/operations")
	@ReadOnlyProperties("creationTime", "self", "bulkOperationId", "failureReason", "self", "id", "status")
	fun createOperation(
		@Body body: Operation, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Operation>
	
	/**
	 * Delete a list of operations </br>
	 * Delete a list of operations.  The DELETE method allows for deletion of operation collections.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A list of operations was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 *
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param agentId An agent ID that may be part of the operation.
	 * @param dateFrom Start date or date and time of the operation.
	 * @param dateTo End date or date and time of the operation.
	 * @param deviceId The ID of the device the operation is performed for.
	 * @param status Status of the operation.
	 */
	@Headers("Accept:application/json")
	@DELETE("/devicecontrol/operations")
	fun deleteOperations(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("agentId") agentId: String? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("deviceId") deviceId: String? = null, 
		@Query("status") status: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific operation </br>
	 * Retrieve a specific operation (by a given ID).  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_READ <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the operation is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Operation not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the operation.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
	@GET("/devicecontrol/operations/{id}")
	fun getOperation(
		@Path("id") id: String
	): Call<Operation>
	
	/**
	 * Update a specific operation status </br>
	 * Update a specific operation (by a given ID). You can only update its status.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An operation was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Operation not found.</li>
	 * <li>422 Validation error.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param id Unique identifier of the operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.operation+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json"]) 
	@PUT("/devicecontrol/operations/{id}")
	@ReadOnlyProperties("creationTime", "self", "bulkOperationId", "failureReason", "self", "id", "deviceId")
	fun updateOperation(
		@Body body: Operation, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Operation>
}
