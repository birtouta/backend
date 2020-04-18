package com.birtouta.controllers;

import com.birtouta.dao.OrderRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.StoreRepository;
import com.birtouta.dto.OrderDTO;
import com.birtouta.entities.*;
import com.birtouta.services.Metier;
import com.birtouta.services.OrderProductService;
import com.birtouta.services.OrderService;
import com.birtouta.services.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/order",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderProductService orderProductService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewOrder(@RequestParam Long id_store, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
        } else {
            User user = session.getUser();
            Order order = new Order();
            Store store = storeRepository.findStoreById(id_store);
            order.setStore(store);
            order.setUser(user);
			return new ResponseEntity<Response>(new Response("Store successfully created !", orderRepository.save(order),  200, true), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/update")
    public  ResponseEntity<?> updateOrder(Order order, @RequestHeader("Token") String token ) {
        Session session = sessionRepository.findByToken(token);
    	if (session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
        } else {
        	Order ordersrc  = orderRepository.getById(order.getId());
        	Metier.copyNonNullProperties(order, ordersrc);
			return new ResponseEntity<Response>(new Response("Order successfully updated ! ", orderRepository.save(order),  200, true), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/all", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAllUsers(@RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
        } else {
			return new ResponseEntity<Response>(new Response("Orders successfully retreived !", session.getUser().getOrders(),  200, true), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/getOrderProducts/{id}")
    public ResponseEntity<?> getOrderProducts(@PathVariable("id") Long id_order,@RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            List<OrderProduct> orderProducts=orderProductService.getAllProductsByOrder(id_order);
            return new ResponseEntity<List<OrderProduct>>(orderProducts, HttpStatus.OK);
        }
    }
}
