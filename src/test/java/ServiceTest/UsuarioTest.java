/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceTest;

import Model.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author silas
 */
public class UsuarioTest {

    private static final HTTPHelper HELPER = new HTTPHelper();
    public static final String URL = HTTPHelper.URL_BASE + "/usuario";

    public static void main(String[] args) throws IOException, ParseException {

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        Gson gson = new Gson();
        Usuario usuario = new Usuario();
        //usuario.setNome("Mirella Queiroz");
        usuario.setEmail("queiroz.av@gmail.com");
        usuario.setSenha("teste1");
        //usuario.setNascimento("1996-01-07");
        
        System.out.println("Requisiçao: \n" + gson.toJson(usuario, Usuario.class));

        UsuarioTest test = new UsuarioTest();
        //System.out.println("Tentando Login de usuario inexistente");
        //test.loginTest(gson.toJson(usuario, Usuario.class), header);

//        System.out.println("Cadastrando...");
  //      test.signUpTest(gson.toJson(usuario, Usuario.class), header);

    //    System.out.println("Tentando Login de usuario recem cadastrado");
        test.loginTest(gson.toJson(usuario, Usuario.class), header);
        //System.out.println(sendGet("", header));

    }

    public void loginTest(String request, Map<String, String> header) throws ProtocolException, IOException {
        String endpoint = URL + "/login";
        System.out.println(HELPER.sendPost(endpoint, request, header));
    }

    public void signUpTest(String request, Map<String, String> header) throws ProtocolException, IOException {
        String endpoint = URL + "/signup";
        System.out.println(HELPER.sendPost(endpoint, request, header));
    }
}
