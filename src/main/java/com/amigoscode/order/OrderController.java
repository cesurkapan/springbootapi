package com.amigoscode.order;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// TODO: 9 - Add @RequestMapping("/api/v1/orders") at the class level
//  Then simplify all endpoint paths below (remove "/api/v1/orders" prefix)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    // TODO: 13 - Inject OrderService via constructor injection
    //  (replace direct data access with service calls)

    // TODO: 1 - Create a GET endpoint mapped to "/api/v1/orders/welcome"
    //  that returns the string "Welcome to the Orders API"

    @GetMapping("/api/v1/orders/welcome")
    public String getWelcome(){
        return "Welcome to the Orders API";
    }

    // TODO: 2 - Create a GET endpoint mapped to "/api/v1/orders/sample"
    //  that returns a single hardcoded Order object
    //  Hint: new Order(1L, "Laptop", "PENDING", 999.99, "john@mail.com", LocalDate.now(), "rush")

    @GetMapping("/api/v1/orders/sample")
    public Order getSampleOrder(){
        Order order = new Order(1L, "Laptop", "PENDING", 999.99, "john@mail.com", LocalDate.now(), "rush");

        return order;

    }

    // TODO: 3 - Create a GET endpoint mapped to "/api/v1/orders"
    //  that returns a hardcoded List of Order objects
    //  Hint: use List.of(...)

    @GetMapping("/api/v1/orders")
    public List<Order> getOrders(){
        return List.of(new Order(
                1L, "Laptop", "PENDING", 999.99, "john@mail.com", LocalDate.now(), "rush"
        ), new Order(2L, "Laptop", "PENDING", 55.99, "ces@mail.com", LocalDate.now(), "rush"));
    }

    // TODO: 4 - Create a GET endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and returns an Order
    //  For now, return a hardcoded Order with the given id


    @GetMapping("/api/v1/orders/{id}")
    public Order getOrdersById(@PathVariable("id") Long id){

        return new Order(id, "Laptop", "PENDING", 999.99,
                "john@mail.com", LocalDate.now(), "rush" );

    }

    // TODO: 5 - Create a GET endpoint mapped to "/api/v1/orders/filter"
    //  that takes a @RequestParam(required = false) String status
    //  Return a filtered list if status is provided, otherwise return all

    @GetMapping("/api/v1/orders/filter")
    public List<Order> filterOrder(@RequestParam(required = false) String status){

        List<Order> orders = getOrders();

        if (status==null){
            return orders;
        }
        else return orders.stream().filter(order ->
                order.getStatus().equalsIgnoreCase(status)).toList();

    }

    // TODO: 6 - Create a POST endpoint mapped to "/api/v1/orders"
    //  that takes an Order @RequestBody and returns the saved order


    /*
    @PostMapping("/api/v1/orders")
    @RequestBody
    public Order createOrder(){

    }

     */



    // TODO: 7 - Create a PUT endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and @RequestBody Order
    //  Set the id on the order and update it

    // TODO: 8 - Create a DELETE endpoint mapped to "/api/v1/orders/{id}"
    //  that takes a @PathVariable Long id and deletes the order

    // TODO: 10 - Refactor all endpoints to return ResponseEntity<> with proper status codes:
    //  - GET returns 200 (OK)
    //  - POST returns 201 (Created)
    //  - PUT returns 204 (No Content)
    //  - DELETE returns 204 or 404
    //  Hint: look at CustomerController for reference

}
