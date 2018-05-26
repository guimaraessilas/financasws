/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceTest;

import Model.Receita;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProtocolException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author silas
 */
public class ReceitaTest {

    private static final HTTPHelper HELPER = new HTTPHelper();
    public static final String URL = HTTPHelper.URL_BASE + "/receita";

    public static void main(String[] args) throws IOException, ParseException {

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        Gson gson = new Gson();
        Receita receita = new Receita();
        
        receita.setUsuarioId(4L);
        receita.setPago(true);
        receita.setFixo(true);
        receita.setDescricao("Teste 123");
        receita.setTitulo("Teste");
        
        receita.setVencimento("2018-05-21");

        System.out.println("Requisiçao: \n" + gson.toJson(receita, Receita.class));

        ReceitaTest test = new ReceitaTest();
        System.out.println("Tentando Cadastrar receita");
        test.createReceitaTest(gson.toJson(receita, Receita.class), header);

    }

    private void createReceitaTest(String request, Map<String, String> header) throws ProtocolException, IOException {
        String endpoint = URL + "/create";
        System.out.println(HELPER.sendPost(endpoint, request, header));
    }

}
