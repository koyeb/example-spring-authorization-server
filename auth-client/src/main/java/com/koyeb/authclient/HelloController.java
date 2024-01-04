// auth-koyeb/auth-client/src/main/java/com/koyeb/authclient/HelloController.java
package com.koyeb.authclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
  @GetMapping("/")
  @ResponseBody
  String hello() {
    return "Hello World";
  }

}

