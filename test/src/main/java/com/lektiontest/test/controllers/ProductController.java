package com.lektiontest.test.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import com.lektiontest.test.entities.Customers;
import com.lektiontest.test.entities.OrderDetails;
import com.lektiontest.test.entities.Orders;
import com.lektiontest.test.entities.Products;
import com.lektiontest.test.repositories.CustomersRepository;
import com.lektiontest.test.repositories.OrderDetailsRepository;
import com.lektiontest.test.repositories.OrderRepository;
import com.lektiontest.test.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    List<Products> cartList = new ArrayList<>();
    private int thisOrderNumber;
    private int orderLineNumber = 0;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // för vårt homepage
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Products> products = productRepository.findAll(); //productRepository kopplingen till databasen
        model.addAttribute("viewAllProducts", products);
        return "products";
    }



    @PostMapping(value = "/add")
    public String addProduct(Model model, @ModelAttribute("product") Products product) {
        cartList.add(product);
     
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Model model, Products product) {
        float total = floatTotal();
        model.addAttribute("cartList", cartList);
        model.addAttribute("total", total);
        return "cart";
    }
    

    public float floatTotal(){
        float total = 0;
        for(int i = 0; i < cartList.size(); i++){
            total += cartList.get(i).getMSRP();
            
        }
        return total;
    }



    @GetMapping(value = "/checkout")
    public String checkoutPage(Model model, Customers customer, Orders order) {
        model.addAttribute("cartList", cartList);
        model.addAttribute("orders", order);
        return "checkout";
    }

    @GetMapping(value = "/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

 
    // här visar vi productinfo baserat på vilken product CodeID vi väljer
    @GetMapping("/view-product/{productCodeId}")
    public ModelAndView showEditProductPage(@PathVariable(name = "productCodeId") String productCodeId) {
        ModelAndView modelAndView = new ModelAndView("view-product");
        Products products = productRepository.findById(productCodeId).get();
        modelAndView.addObject("products", products);
        return modelAndView;
    }



    @GetMapping("/removeProduct/{productCodeId}")
    public String removeProduct(@PathVariable("productCodeId") String productCodeId, Model model) {
        int index = this.exists(productCodeId, cartList);
        cartList.remove(index);
        model.addAttribute("cartList", cartList);
        return "redirect:/cart";
    }
    // Här kollar vi om producten finns i cartList
    private int exists(String productCodeId, List<Products> cartList) {
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getProductCodeId().equalsIgnoreCase(productCodeId)) {
                return i;
            }
        }
        return -1;
    }



    /******* ORDER ********/

    @GetMapping("/confirm")
    public String confirm(Model model, Products product) {
        OrderDetails orderDetails = saveOrderDetails();
        model.addAttribute("cartList", cartList);
        model.addAttribute("orderDetails", orderDetails);
        System.out.println(orderDetails.getOrderNumber());

        List<OrderDetails> orderDetailsListFindAll = orderDetailsRepository.findAll();
        List<OrderDetails> theseOrderDetails = new ArrayList<>();

        System.out.println(orderDetailsRepository.count());
        System.out.println(orderDetailsListFindAll.size());

        // Går igenom allt i orderDetails i databasen.
        for(int i = 0; i < orderDetailsListFindAll.size(); i++){
            if(orderDetails.getOrderNumber() == orderDetailsListFindAll.get(i).getOrderNumber()){
                theseOrderDetails.add(orderDetailsListFindAll.get(i));
            }
        }
        model.addAttribute("viewOrderDetails", theseOrderDetails);
        return "confirm";
    }
    public OrderDetails saveOrderDetails(){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderNumber(getTheOrder());
        orderDetails.setProductCode(thisProductCodeId());
        orderDetails.setQuantityOrdered(getQty()); 
        orderDetails.setPriceEach(cartList.get(0).getMSRP());
        cartList.remove(0);
        orderDetails.setOrderLineNumber(getOrderLineNumber() + 1); 
        orderDetailsRepository.save(orderDetails);
        setQty(1);
        setOrderLineNumber(getOrderLineNumber()+1);
        if(!cartList.isEmpty() ){
            saveOrderDetails();
        }
        setOrderLineNumber(0);
        return orderDetails;
    }
    // Andra ger oss kvantitet.
    public String thisProductCodeId() {
        for (int i = 0; i < cartList.size();) {
            for (int j = 1; j < cartList.size(); j++) {
                if (cartList.get(i).getProductCodeId().equals(cartList.get(j).getProductCodeId())) {
                    setQty(getQty()+1);
                    cartList.remove(cartList.get(j));
                }
            }

            return cartList.get(i).getProductCodeId();

        }
        return cartList.get(0).getProductCodeId();
    }

    private int qty = 1;

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @PostMapping(value = "/createOrder")
    public ModelAndView saveOrder(@ModelAttribute ("orders") @Valid Orders order, BindingResult bindingResult,
            @ModelAttribute("customer") Customers customer) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("checkout");
            return modelAndView;
        }
        if (customersRepository.findById(customer.getCustomerNumber()) == null
                || customersRepository.findById(customer.getCustomerNumber()).isEmpty()) {
            throw new NoSuchElementException(
                    "CustomerNumber with number: " + customer.getCustomerNumber() + " does not exist.");
        }
       
       
        order.setOrderDate(orderDate());
        order.setRequiredDate(orderDate());
        order.setStatus("Recieved");
        orderRepository.save(order);
        setThisOrderNumber(order.getOrderNumber());
        modelAndView.setViewName("redirect:/confirm");
        return modelAndView;
    }

    @ExceptionHandler({ Exception.class })
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", ex.getMessage());
        return modelAndView;
    }

    public int getTheOrder() {
        Optional<Orders> orders = orderRepository.findById(getThisOrderNumber());
        int orderNumber = orders.get().getOrderNumber();
        return orderNumber;
    }

    public Date orderDate() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return date;

    }

    public int getThisOrderNumber() {
        return this.thisOrderNumber;
    }

    public void setThisOrderNumber(int thisOrderNumber) {
        this.thisOrderNumber = thisOrderNumber;
    }

    public int getOrderLineNumber() {
        return this.orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }


}
