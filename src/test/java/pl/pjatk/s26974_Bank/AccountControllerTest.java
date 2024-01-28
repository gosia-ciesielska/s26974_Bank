package pl.pjatk.s26974_Bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.pjatk.s26974_Bank.controller.AccountController;
import pl.pjatk.s26974_Bank.exception.AccountExceptionHandler;
import pl.pjatk.s26974_Bank.model.Account;
import pl.pjatk.s26974_Bank.model.Currency;
import pl.pjatk.s26974_Bank.repository.AccountRepository;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountController accountController;

    @BeforeAll
    public static void initialize() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new AccountExceptionHandler()).build();
    }

    @Test
    public void canCreateAccount() throws Exception {
        Account account = new Account("12345678912", 13123, Currency.PLN, "Malgorzata Ciesielska");
        String body = objectMapper.writeValueAsString(account);
        MockHttpServletResponse response = mockMvc.perform(
                post("/account/new").contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }
}
