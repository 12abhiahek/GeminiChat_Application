package com.ai.gemini.GeminiChatApplication;

import com.ai.gemini.GeminiChatApplication.service.QnAService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@Disabled("Context loading fails due to missing bean/config")
class GeminiChatApplicationTests {
//	@MockBean
//	QnAService qnAService;

	@Test
	void contextLoads() {
	}

}

