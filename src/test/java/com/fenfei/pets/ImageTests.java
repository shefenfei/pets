package com.fenfei.pets;

import com.fenfei.pets.models.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ImageTests {

//    @Autowired
//    private IBookService iBookService;

    @Test
    public void imageManageByLombok() {
        Image image = new Image();
        image.setId("id");
        image.setName("这是图片");

        assertThat(image.getId()).isEqualTo("id");
        assertThat(image.getName()).isEqualTo("这是图片");
//        List<Book> books = iBookService.bookList("shefefei");
//        Assert.assertNotNull(books);
    }
}
