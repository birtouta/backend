package com.birtouta.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/storeworker",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class StoreWorkerRestController {
//	@PostMapping("/addStoreWorker")
//	public ResponseEntity<?> addStoreWorkerToStore(@RequestParam(required=true) Long id_store, @RequestHeader("Token") String token ){
//		
//	}
}
