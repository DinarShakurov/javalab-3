package ru.shakurov.hateoas.hateoas_example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.shakurov.hateoas.hateoas_example.domain.Account;
import ru.shakurov.hateoas.hateoas_example.services.AccountService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AccountTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        when(accountService.fillMoney(1L, 1L)).thenReturn(targetAccount());
    }

    @Test
    public void updateAccountBalance() throws Exception {
        String newMoney = new ObjectMapper().writeValueAsString(1L);

        mockMvc.perform(put("/accounts/1/fillMoney").content(newMoney).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.money").value(targetAccount().getMoney()))
                .andExpect(jsonPath("$.login").value(targetAccount().getLogin()))
                .andDo(document("update_account_balance", responseFields(
                        fieldWithPath("money").description("Текущий баланс аккаунта"),
                        fieldWithPath("login").description("Логин аккаунта")
                )));
    }

    private Account targetAccount() {
        return Account.builder()
                .login("dinar")
                .money(1_000_001L)
                .build();
    }
}
