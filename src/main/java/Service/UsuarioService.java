/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.UsuarioDAO;
import Model.Usuario;
import Util.Json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author guima
 */
@Path("/usuario")
public class UsuarioService {

    @Context
    private UriInfo context;

    public UsuarioService() {
    }

    @POST
    @Produces("application/json")
    @Path("/signup")
    public Response signup(String pConteudo) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

        Usuario usuario = (Usuario) gson.fromJson(pConteudo, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0) {
            dao.insert(usuario);
        } else {
            dao.update(usuario);
        }

        Json json = new Json();
        json.setJson(usuario);
        json.setMsg("OK");
        json.setResult(true);

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces("application/json")
    @Path("/login")
    public Response login(String pConteudo) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

        Usuario usuario = (Usuario) gson.fromJson(pConteudo, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        Json json = new Json();
        usuario = dao.login(usuario);
        
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0) {
            json.setMsg("Acesso negado.");
            json.setResult(false);

            return Response.status(401).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();
        }

        json.setJson(usuario);
        json.setMsg("OK");
        json.setResult(true);

        return Response.status(200).entity(gson.toJson(json)).header("Access-Control-Allow-Origin", "*").build();

    }

    @GET
    @Produces("application/json")
    public void getJson() {
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
