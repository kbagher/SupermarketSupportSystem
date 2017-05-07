package au.edu.rmit.chaos;

import au.edu.rmit.chaos.report.Report;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by kassem on 7/5/17.
 */
public class OrderTest {

    static Customer customer;

    @org.junit.BeforeClass
    public static void init(){
        customer = new Customer("kassem","kassem123",true);
        assertTrue(customer.isLoggedIn());
        assertTrue(customer.increaseBalance(200));
    }

    @org.junit.Test
    public void placeOrderOneProduct() throws Exception {
        Order or = new Order(customer);
        assertNotNull(or);
        assertTrue(or.createNewOrder());
        ArrayList<Product> products =Product.fetchProductsFromServer();
        assertNotNull(products);
        assertNotEquals(products.size(),0);
        assertTrue(or.addProduct(products.get(0),1));
        assertEquals(or.placeOrder(),OrderStatus.placed);
    }

    @org.junit.Test
    public void placeOrderAllProducts() throws Exception {
        Order or = new Order(customer);
        assertNotNull(or);
        assertTrue(or.createNewOrder());
        ArrayList<Product> products =Product.fetchProductsFromServer();
        assertNotNull(products);
        assertNotEquals(products.size(),0);
        for (int x=0;x<products.size();x++){
            assertTrue(or.addProduct(products.get(x),1));
        }
        assertEquals(or.placeOrder(),OrderStatus.placed);
    }

    @org.junit.Test
    public void placeOrderBigQuantityProduct() throws Exception {
        Order or = new Order(customer);
        assertNotNull(or);
        assertTrue(or.createNewOrder());
        ArrayList<Product> products =Product.fetchProductsFromServer();
        assertNotNull(products);
        assertNotEquals(products.size(),0);
        assertFalse(or.addProduct(products.get(0),1000000));
        assertEquals(or.placeOrder(),OrderStatus.unknown);
    }
}