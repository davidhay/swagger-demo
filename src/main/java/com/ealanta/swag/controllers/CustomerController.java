package com.ealanta.swag.controllers;

import com.ealanta.swag.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private Customer bob =  new Customer();
    {
        bob.setId("2112");
        bob.setFirst("Bob");
        bob.setLast("Builder");
        bob.setDob(LocalDate.of(1969, Month.JANUARY, 31));
    }

    private Customer sam =  new Customer();
    {
        sam.setId("123");
        sam.setFirst("Sam");
        sam.setLast("Fireman");
        sam.setDob(LocalDate.of(1950, Month.FEBRUARY, 1));
    }

    private ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(bob,sam));

    private Optional<Customer> findCustomerById(String id){
        Optional<Customer> optCust = customers.stream().filter(pers -> pers.getId().equals(id)).findFirst();
        return optCust;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id){
            Optional<Customer> optCust = findCustomerById(id);
            System.out.printf("ID is [%s] -> [%s]\n",id,optCust);
            return ResponseEntity.of(optCust);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customers);
    }

   @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> putCustomers(@PathVariable("id") String id, @RequestBody Customer pCustomer){
        Optional<Customer> optCust = findCustomerById(id);
        if(optCust.isPresent()){
            Customer cust = optCust.get();
            cust.setFirst(pCustomer.getFirst());
            cust.setDob(pCustomer.getDob());
            cust.setLast(pCustomer.getLast());
            return ResponseEntity.ok(cust);
        }else{
            pCustomer.setId(id);
            customers.add(pCustomer);
            return ResponseEntity.ok(pCustomer);
        }
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        Optional<Customer> optCust = findCustomerById(id);
        final ResponseEntity<Void> result;
        if(optCust.isPresent()) {
            customers.remove(optCust.get());
            result = ResponseEntity.noContent().build();
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @PatchMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> patchCustomer(@PathVariable("id") String id, @RequestBody Customer patched){
        Optional<Customer> optCust = findCustomerById(id);
        if(optCust.isPresent()){
            Customer cust = optCust.get();
            if(patched.getFirst() != null){
                cust.setFirst(patched.getFirst());
            }
            if(patched.getLast() != null){
                cust.setLast(patched.getLast());
            }
            if(patched.getDob() != null){
                cust.setDob(patched.getDob());
            }
            return ResponseEntity.ok(cust);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
