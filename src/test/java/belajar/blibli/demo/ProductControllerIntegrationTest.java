package belajar.blibli.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import static org.junit.jupiter.api.Assertions.*;
public class ProductControllerIntegrationTest {

  @Autowired
  RedisTemplate<String,String> redisTemplate;

  @Test
  public void checkValueOfRedis(){
    assertTrue(true);
  }

}
