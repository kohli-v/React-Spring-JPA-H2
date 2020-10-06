package com.wcf.client.Backend.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.wcf.client.Backend.Entities.Feature;
import com.wcf.client.Backend.Entities.FeatureId;
import com.wcf.client.Backend.Exceptions.FailedToAddException;
import com.wcf.client.Backend.Exceptions.FailedToDeleteException;
import com.wcf.client.Backend.Exceptions.FailedToUpdateException;
import com.wcf.client.Backend.Resources.AddFeatureResource;
import com.wcf.client.Backend.Resources.DeleteFeatureResponseResource;
import com.wcf.client.Backend.Resources.EnableFeatureResponseResource;
import com.wcf.client.Backend.Services.ManageFeatureService;


/**
 I have tried to return responses in different ways 
 in each method to showcase some different ways of returning responses.
**/


@RestController
public class ManageFeatureController {
	
	@Autowired ManageFeatureService addDeleteFeatureService;

	
	/**
	 API: Will add feature to db.
	 ---
	 SUCCESS scenario: Returns 200 response with entity that is added in db.
	 ---
	 FAILURE scenario: This API uses controller advice exception handling.
	 Forcefully throws particular exception to showcase controller advice.
	 **/
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addFeature")
	public ResponseEntity<Feature> addFeature( @RequestBody AddFeatureResource addFeatureResource) {
		
		try {
			return ResponseEntity.ok(addDeleteFeatureService.addFeature(addFeatureResource));
		}
		catch (Exception ex) {
			throw new FailedToAddException(ex.getMessage());
		}
	}
	
	/**
	 API: Will delete feature from db.
	 Handles extreme case scenario of empty incoming request body.
	 ----
	 SUCCESS scenario: Returns POJO WITHOUT using ResponseEntity.
	 Returns main success/failure response along with list of failed-to-action features.
	 ----
	 FAILURE scenario: This API uses controller advice for exception handling.
	 Forcefully throws particular exception to showcase controller advice.
	 **/
		
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deleteFeature")
	public DeleteFeatureResponseResource deleteFeature(@RequestBody List<FeatureId> featureIdList){
	
		try {
			if (featureIdList.size()==0) {
				throw new  FailedToDeleteException("Invalid request body");
			}
			return addDeleteFeatureService.deleteFeature(featureIdList);
		}
		catch (Exception ex) {
			throw new FailedToDeleteException(ex.getMessage());
		}	
	}
	
	
	
	/** 
	 API: Will enable previously added feature.
	 Handles extreme case scenario of empty incoming request body.
	 ----
	 SUCCESS scenario: Returns 200 success code along with "success" string.
	 Failed-to-action list should be empty.
	 ----
	 FAILURE scenario: 
	 1. Returns non 200 response if actioning of any feature has failed. Failed-to-action list will NOT be empty.
	 2. This API uses controller advice exception handling.
	 Forcefully throws particular exception to showcase controller advice.
	 **/
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PatchMapping("/enableFeature")
	public ResponseEntity<EnableFeatureResponseResource> enableFeature(@RequestBody List<FeatureId> featureIdList ) {
		
		// extreme case
		if (featureIdList.size()==0) {
			throw new  FailedToDeleteException("Invalid request body");
		}
		
		try {
			EnableFeatureResponseResource result = addDeleteFeatureService.enableFeature(featureIdList);
			if (!result.getResult().equals("success")) {
				return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			return ResponseEntity.ok(addDeleteFeatureService.enableFeature(featureIdList));
			
		}
		catch (Exception ex) {
			throw new FailedToUpdateException(ex.getMessage());
		}
	}
	
	/** 
	 API: Will retrieve list of all features.
	 **/
		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/retrieveFeatureList")
		public List<Feature> retrieveFeatureList() {
			return addDeleteFeatureService.retrieveFeatureList();
		}
}