package com.birtouta.controllers;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dto.OrderDTO;
import com.birtouta.entities.Order;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.mapper.ObjectMapperUtils;
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

    @PostMapping(path = "/addOrder")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody OrderDTO orderDTO, @RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            User user = session.getUser();
            orderDTO.setUser(user);
            Order order = orderService.saveOrUpdateOrder(orderDTO);
            OrderDTO orderDTO1 = ObjectMapperUtils.map(order, OrderDTO.class);
            return new ResponseEntity<OrderDTO>(orderDTO1, HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/updateOrder")
    public @ResponseBody
    ResponseEntity<?> updateUser(@Valid @RequestBody OrderDTO orderDTO) {
        Order users = orderService.saveOrUpdateOrder(orderDTO);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<?> getAllUsers(@RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            User user = session.getUser();
            List<Order> orders=user.getOrders();
            List<OrderDTO> dtos = ObjectMapperUtils.mapAll(orders, OrderDTO.class);
            return new ResponseEntity<OrderDTO>((OrderDTO) dtos, HttpStatus.OK);
        }
    }
}
