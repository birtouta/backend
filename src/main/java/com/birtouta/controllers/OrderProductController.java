package com.birtouta.controllers;

import com.birtouta.dao.*;
import com.birtouta.entities.*;
import com.birtouta.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orderProduct")
public class OrderProductController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private OrderProductService orderProductService;
    
    @Autowired
    private OrderRepository orderRepository; 
    
    @Autowired
    private OrderProductRepository orderProductRepository ;

    @PostMapping(path = "/addOrderProduct")
    public ResponseEntity<?> addNewOrderProduct(@RequestBody OrderProduct orderProduct, @RequestParam("id_order") Long id_order, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            OrderProduct orderProduct1 = orderProductService.saveOrUpdateOrderProduct(orderProduct, id_order);
            
            if (orderProduct1 != null) return new ResponseEntity<OrderProduct>(orderProduct1, HttpStatus.OK) ;
            
            return new ResponseEntity<String>("Order doe not exist !", HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping(path = "/addAllOrderProducts")
    public ResponseEntity<?> addAllOrderProducts(@RequestBody Order order, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            orderProductService.saveAllOrderProducts(order);
            return new ResponseEntity<String>("Products saved with success", HttpStatus.OK);
        }
    }
}
