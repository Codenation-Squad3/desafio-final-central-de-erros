package br.com.codenation.desafio.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthTestController {

    @GetMapping("/oauthtest")
    public String oauthtest(){
        return "Somente mostrado se usuário estiver logado.";
    }

}
