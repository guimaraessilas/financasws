/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ReceitaDAO;
import Model.Receita;
import Util.Json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author guima
 */
@Path("/receita")
public class ReceitaService {

    @Context
    private UriInfo context;
    private static final ReceitaDAO DAO = new ReceitaDAO();

    public ReceitaService() {
    }

    @POST
    @Produces("application/json")
    @Path("/create")
    public Response createReceita(String pConteudo) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        Json json = new Json();

        Receita receita = (Receita) gson.fromJson(pConteudo, Receita.class);
        if (receita.getReceitaId() == null || receita.getReceitaId() == 0) {
            json.setResult(DAO.insert(receita));
        } else {
            json.setResult(DAO.update(receita));
        }

        json.setJson(receita);
        json.setMsg("OK");

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Produces("application/json")
    @Path("/update")
    public Response updateReceita(String pConteudo) throws IOException, Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        Json json = new Json();

        Receita receita = (Receita) gson.fromJson(pConteudo, Receita.class);
        boolean status = DAO.update(receita);
        json.setJson(receita);
        json.setMsg("Erro ao atualizar a receita.");

        if (status) {
            json.setMsg("OK");
        }

        json.setResult(status);

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @GET
    @Produces("application/json")
    @Path("/list/{pIdUsuario}")
    public Response listReceita(@PathParam("pIdUsuario") String pIdUsuario) throws IOException, Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

        List<Receita> listaReceita = DAO.list(Long.getLong(pIdUsuario));

        Json json = new Json();
        json.setJson(listaReceita);
        json.setMsg("OK");
        json.setResult(true);

        if (listaReceita.isEmpty()) {
            json.setMsg("Nenhuma receita encontrada.");
            json.setResult(false);
        }

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{pIdReceita}")
    public Response deleteReceita(@PathParam("pIdReceita") String pIdReceita) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        
        Json json = new Json();
        json.setJson("");
        boolean status = DAO.delete(Long.getLong(pIdReceita));
        json.setMsg("OK");
        json.setResult(status);

        if (status)
            json.setMsg("Erro ao deletar Receita");     
        
        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @GET
    @Produces("application/json")
    public String getJson() {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @POST
    @Consumes("application/json")
    public void postJson(String content) {
    }

    @DELETE
    @Consumes("application/json")
    public void deleteJson(String content) {
    }
}
