package com.capgemini.order;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.order.controller.OrderController;
import com.capgemini.order.entity.Order;
import com.capgemini.order.service.OrderService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {
@Mock
private OrderService orderService;

@InjectMocks
 private OrderController orderController;

private MockMvc mockMvc;

@Before
public void setUp() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
}


	@Test
	public void testAddOrder() throws Exception {
		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(new Order(123, 200, 5));
		mockMvc.perform(post("/order").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(
						" { \"OrderId\": 123,\r\n" + 
						"  \"OrderPrice\": 200,\r\n" + 
						"  \"OrderQuantity\": 5 \r\n" + 
						"}").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
		.andDo(print());
	}


/*@Test
public void testAddOrder() throws Exception {
	Order order = new Order(221700, 12345, 12345);
	when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(order);
	String content = "{\r\n" + "  \"orderId\": 221700,\r\n" + "  \"products\": 12345,\r\n"
			+ "  \"customerId\": 12345,\r\n" + "}";
	mockMvc.perform(post("/v1/order").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)
			.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$.orderId").value(221700));
}*/

}
