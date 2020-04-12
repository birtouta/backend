package com.birtouta.controllers;

import com.birtouta.dao.OrderRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.StoreRepository;
import com.birtouta.dto.OrderDTO;
import com.birtouta.entities.Order;
import com.birtouta.entities.Session;
import com.birtouta.entities.Store;
import com.birtouta.entities.User;
import com.birtouta.mapper.ObjectMapperUtils;
import com.birtouta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
//            Order order = orderService.saveOrUpdateOrder(orderDTO);
//            OrderDTO orderDTO1 = ObjectMapperUtils.map(order, OrderDTO.class);
            return new ResponseEntity<Order>(orderRepository.save(order), HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/updateOrder")
    public @ResponseBody
    ResponseEntity<?> updateUser(@Valid @RequestBody OrderDTO orderDTO) {
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
//            List<OrderDTO> dtos = ObjectMapperUtils.mapAll(orders, OrderDTO.class);
            return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
        }
    }
}
