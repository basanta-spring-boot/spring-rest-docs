package com.javatechie.spring.docs;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringRestDocsApplication.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets/asciidoc")
class SpringRestDocsApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;


   /* @Test
    public void shouldReturnMessage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to java techie")))
                .andDo(document("home"));
        ;
    }*/

    @Test
    public void shouldReturnBooks() throws Exception {
        String response = "[{\"id\":101,\"name\":\"core java\",\"price\":500.0},{\"id\":102,\"name\":\"spring boot\",\"price\":800.0}]";
        this.mockMvc
                .perform(RestDocumentationRequestBuilders.get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response))
                .andExpect(content().contentType("application/json"))
                .andDo(document("books",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("").optional().
                                description(response))));
    }
}
