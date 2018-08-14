package com.fenfei.pets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PetsApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}


	@Test
	public void contextLoads() {
	}


	@Test
	public void test() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pets/getAll")).andDo(print()).andReturn();
		System.out.println(result);
	}


//	@Test
//	public void testJson() {
//		String json = "{\n" +
//				"  \"labelId\" : 1,\n" +
//				"  \"memberIds\" : [1,2,3,4,5,6,7]\n" +
//				"}\n" +
//				"\n";
//		try {
//			JSONObject jsonObject = new JSONObject(json);
//			//标签id
//			Integer labelId = jsonObject.optInt("labelId");
//			JSONArray array = jsonObject.optJSONArray("memberIds");
//			List<Memeber> members = new ArrayList<>();
//			for (int i=0; i< array.length() ; i++) {
//				//会员id
//				long memberId = array.getLong(i);
//				Member member = memberService.getUserById(memberId);
//				member.setLabelId(labelId);
//				((ArrayList) members).add(member);
//			}
//
//			memberService.updateBatch(members);
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}

}
