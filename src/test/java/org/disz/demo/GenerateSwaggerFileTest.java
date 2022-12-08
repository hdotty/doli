package org.disz.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateSwaggerFileTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void generateSwagger() throws Exception {
        final MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/v2/api-docs").accept(MediaType.APPLICATION_JSON);
        final MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(request).andDo(response -> saveToFile(response.getResponse().getContentAsString()));
    }

    private void saveToFile(final String content) throws IOException {
        Files.writeString(Path.of("swagger/swagger.json"), content);
    }
}
