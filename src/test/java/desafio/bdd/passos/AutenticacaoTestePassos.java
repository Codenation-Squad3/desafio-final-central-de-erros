package desafio.bdd.passos;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.util.List;

public class AutenticacaoTestePassos {

    @Dado("^que ainda não foi feito nenhum cadastro$")
    public void que_ainda_nao_foi_feito_nenhum_cadastro(){

    }

    @Dado("que já foi feito um cadastro para um usuário de email \"(.*?)\"$")
    public void que_ja_foi_feito_um_cadastro_para_um_usuario_de_email(String email){

    }

    @Quando("^insiro um email \"(.*?)\"$")
    public void insiro_um_email(String email){

    }

    @E("^insiro uma senha \"(.*?)\"$")
    public void insiro_uma_senha(String senha){

    }

    @Quando("^o usuário realiza a solicitação de cadastro$")
    public void o_usuario_realiza_a_solicitacao_de_cadastro()
    {

    }

    @Entao("^ele deve ser cadastrado com sucesso$")
    public void deve_ser_cadastrado_com_sucesso(){

    }

    @Entao("^ele não deve ser cadastrado$")
    public void eleNaoDeveSerCadastrado(){

    }
}
