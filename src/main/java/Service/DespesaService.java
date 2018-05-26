/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DespesaDAO;
import Model.Despesa;
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
@Path("/despesa")
public class DespesaService {

    @Context
    private UriInfo context;
    private static final DespesaDAO DAO = new DespesaDAO();

    public DespesaService() {
    }

    @POST
    @Produces("application/json")
    @Path("/create")
    public Response createDespesa(String pConteudo) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        Json json = new Json();

        Despesa despesa = (Despesa) gson.fromJson(pConteudo, Despesa.class);
        if (despesa.getDespesaId() == null || despesa.getDespesaId() == 0) {
            json.setResult(DAO.insert(despesa));
        } else {
            json.setResult(DAO.update(despesa));
        }

        json.setJson(despesa);
        json.setMsg("OK");

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Produces("application/json")
    @Path("/update")
    public Response updateDespesa(String pConteudo) throws IOException, Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        Json json = new Json();

        Despesa despesa = (Despesa) gson.fromJson(pConteudo, Despesa.class);
        boolean status = DAO.update(despesa);
        json.setJson(despesa);
        json.setMsg("Erro ao atualizar a despesa.");

        if (status) {
            json.setMsg("OK");
        }

        json.setResult(status);

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @GET
    @Produces("application/json")
    @Path("/list/{pIdUsuario}")
    public Response listDespesa(@PathParam("pIdUsuario") String pIdUsuario) throws IOException, Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

        List<Despesa> listaDespesa = DAO.list(Long.getLong(pIdUsuario));

        Json json = new Json();
        json.setJson(listaDespesa);
        json.setMsg("OK");
        json.setResult(true);

        if (listaDespesa.isEmpty()) {
            json.setMsg("Nenhuma despesa encontrada.");
            json.setResult(false);
        }

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{pIdDespesa}")
    public Response deleteDespesa(@PathParam("pIdDespesa") String pIdDespesa) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        
        Json json = new Json();
        json.setJson("");
        boolean status = DAO.delete(Long.getLong(pIdDespesa));
        json.setMsg("OK");
        json.setResult(status);

        if (status)
            json.setMsg("Erro ao deletar Despesa");     
        
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
