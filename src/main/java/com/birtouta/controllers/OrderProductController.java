package com.birtouta.controllers;

import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.Order;
import com.birtouta.entities.OrderProduct;
import com.birtouta.entities.Session;
import com.birtouta.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orderProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderProductController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private OrderProductService orderProductService;

    @PostMapping(path = "/addOrderProduct")
    public ResponseEntity<?> addNewOrderProduct(@RequestBody OrderProduct orderProduct, @RequestParam Long id_order, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            OrderProduct orderProduct1 = orderProductService.saveOrUpdateOrderProduct(orderProduct, id_order);
            return new ResponseEntity<OrderProduct>(orderProduct1, HttpStatus.OK);
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
