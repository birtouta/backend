package com.birtouta.controllers;

import com.birtouta.dao.OrderRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.StoreRepository;
import com.birtouta.dto.OrderDTO;
import com.birtouta.entities.*;
import com.birtouta.services.OrderProductService;
import com.birtouta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/order")
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

    @PostMapping(path = "/addOrder")
    public ResponseEntity<?> addNewOrder(@RequestParam Long id_store, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            User user = session.getUser();
            Order order = new Order();
            Store store = storeRepository.findStoreById(id_store);
            order.setStore(store);
            order.setUser(user);
            return new ResponseEntity<Order>(orderRepository.save(order), HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/updateOrder")
    public @ResponseBody
    ResponseEntity<?> updateOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order users = orderService.saveOrUpdateOrder(orderDTO);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            User user = session.getUser();
            List<Order> orders=user.getOrders();
            return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getOrderProducts/{id}")
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
