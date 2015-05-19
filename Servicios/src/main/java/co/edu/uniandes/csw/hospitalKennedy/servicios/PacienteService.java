/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Catalizador;
import co.edu.uniandes.csw.hospitalKennedy.dto.CatalizadorDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.dto.ReporteDTO;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioPacienteMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPacienteMock;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Path("/Pacientes")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteService {

    //@EJB    
    //private IServicioPacienteMock pacienteEjb;
    //public PacienteService()
    //{
    //    pacienteEjb = new ServicioPacienteMock();
    //}
    ServicioPacienteMock servicioPaciente;

    //@PostConstruct
    //public void init() {
    //}
    public PacienteService() {
        servicioPaciente = new ServicioPacienteMock();
    }
    
    

    @POST
    @Path("{id}/agregarReportes/")
    public Response agregarReporte(@PathParam("id") Long idPaciente, ReporteDTO reporte) {

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + id + " - " + lista.get(0).getActividadFisica());
        //for(Reporte reporte: lista){
        //  pacienteEjb.agregarReporte(id, reporte);
        //}
        //return lista;
        ReporteDTO r = servicioPaciente.agregarReporte(idPaciente, reporte);
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity(r).build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(r).build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(r).build();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(r).build();

    }

    @GET
    @Path("Servicio/")
    public Response algo() {
        Paciente paciente = new Paciente(Long.parseLong("" + 123), "Johnathan", 12, 123, null);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();

    }

    @DELETE
    @Path("{id}/borrar/{idReporte}")
    public Response eliminarReporte(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) throws Exception {
        //for(Reporte reporte: lista){
        //    pacienteEjb.removerReporte(id, reporte);
        //}
        Reporte r = servicioPaciente.removerReporte(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(r).build();

    }

    @GET
    @Path("/paciente/{idPaciente}")
    public Response darPaciente(@PathParam("idPaciente") Long idPaciente) {
        //System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        //return pacienteEjb.darPacientes();
        Paciente p = servicioPaciente.darPaciente(idPaciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(p).build();
    }

    @GET
    @Path("{id}/reportes/")
    public Response darReportes(@PathParam("id") Long idPaciente) {
        //return pacienteEjb.getReportes(id);
        System.out.println("Se busvcan los reportes del paciente " + idPaciente);
        List<Reporte> reportes = servicioPaciente.getReportes(idPaciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reportes).build();

    }

    @GET
    @Path("{id}/reportes/{idReporte}") //URL de ejemplo http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1L/reportes/1L

    public Response darReportePorPaciente(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        //System.out.println("YAAAAAAAAAAAAAA id paciente "+ id +" id reporte "+ idReporte );
        //Reporte rep = pacienteEjb.getReportePorPaciente(id, idReporte); 
        //ArrayList res = new ArrayList<Reporte>();
        //res.add(rep);
        //System.out.println(res);
        //return res;
        Reporte reporte = servicioPaciente.getReportePorPaciente(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reporte).build();
    }

    @GET
    @Path("/{id}/reportes/{fecha1}/{fecha2}") //Ejemplo de este metodo: http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1/reportes/0/6424221442709
    public Response getReportesEntreFechas(@PathParam("id") Long idPaciente, @PathParam("fecha1") String codFecha1, @PathParam("fecha2") String codFecha2) {

        //return pacienteEjb.getReportesEntreFechas(id, codFecha1, codFecha2);
        List<Reporte> reporte = servicioPaciente.getReportesEntreFechas(idPaciente, codFecha1, codFecha2);

        Reporte[] a = new Reporte[reporte.size()];

        for (int i = 0; i < reporte.size(); i++) {
            a[i] = reporte.get(i);
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(a).build();

    }

    @GET
    @Path("{id}/reportes/{idReporte}/catalizadoresFisicas")

    public Response darCatalizadoresActividadFisica(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        String actividadesFisicas = servicioPaciente.darCatalizadoresActividadFisica(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(actividadesFisicas).build();
    }

    @GET
    @Path("{id}/reportes/{idReporte}/catalizadoresSuenio")

    public Response darCatalizadorespatronSuenio(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        String patrones = servicioPaciente.darCatalizadoresPatronSuenio(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(patrones).build();
    }

    @GET
    @Path("{id}/reportes/{idReporte}/catalizadoresMedicamentos")

    public Response darCatalizadoresMedicamentosRecientes(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        String medicamentos = servicioPaciente.darCatalizadoresMedicamentosRecientes(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(medicamentos).build();
    }

    @GET
    @Path("{id}/reportes/{idReporte}/catalizadoresAlimentacion")

    public Response darCatalizadoresAlimentacion(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        String alimentacion = servicioPaciente.darCatalizadoresAlimentacion(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(alimentacion).build();
    }

    @GET
    @Path("{id}/reportes/{idReporte}/catalizadores")
    public Response darCatalizadores(@PathParam("id") Long idPaciente, @PathParam("idReporte") Long idReporte) {

        CatalizadorDTO catalizadores = servicioPaciente.darCatalizadores(idPaciente, idReporte);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(catalizadores).build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}/catalizadores")
    public Response aa() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}/catalizadoresAlimentacion")
    public Response bb() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}/catalizadoresMedicamentos")
    public Response cc() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}/catalizadoresSuenio")
    public Response dd() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}/catalizadoresFisicas")
    public Response ee() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("/{id}/reportes/{fecha1}/{fecha2}")
    public Response ff() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/{idReporte}")
    public Response gg() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("{id}/reportes/")
    public Response hh() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("/paciente/{idPaciente}")
    public Response ii() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }
    
    @OPTIONS
    @Path("{id}/agregarReportes/")
    public Response jj() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }
    
    @OPTIONS
    @Path("Servicio/")
    public Response kk() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }
    
    @OPTIONS
    @Path("{id}/borrar/{idReporte}")
    public Response mm() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }
    
    //--------------------------------------------------
    // Método para la app
    //--------------------------------------------------
    
    @GET
    @Path("/darId")
    /**
     * Recibe el nombre por parámetro y retorna un PacienteDTO con el valor de la cédula del paciente
     */
    public Response darId(PacienteDTO paciente){
        
        PacienteDTO r = servicioPaciente.darPacientePorNombre(paciente);
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity(r).build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(r).build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(r).build();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(r).build();
        
    }
    
    @GET
    @Path("/darReportesPredeterminado")
    public Response darReportesD(){
        
        System.out.println("adadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd");
        
        List<ReporteDTO> lista = new ArrayList<ReporteDTO>();
        
        for(int i = 0; i < 100; i++){
            ReporteDTO reporte = new ReporteDTO();
            
            String txtLocalizacion = "";
            String txtGravedad = "";
            String txtActividad = "";
            String txtPatron = "";
            String txtAlimentacion = "";
            String txtMedicamento = "";
            
            if(i%3 == 0){
                txtLocalizacion = "Toda la cabeza";
                txtGravedad = "Alta";
                txtActividad = "Estudio";
                txtPatron = "Normal";
                txtAlimentacion = "Sana";
                txtMedicamento = "Ninguno";
            }
            if(i%3 == 1){
                txtLocalizacion = "Parte inferior de la cabeza";
                txtGravedad = "Media";
                txtActividad = "Nada";
                txtPatron = "Normal";
                txtAlimentacion = "Comida chatarra";
                txtMedicamento = "Nozidril 500gr";
            }
            if(i%3 == 2){
                txtLocalizacion = "Las cienes";
                txtGravedad = "Media";
                txtActividad = "Nada";
                txtPatron = "Normal";
                txtAlimentacion = "Comida chatarra";
                txtMedicamento = "Nozidril 500gr";
            }
            
            reporte.setLocalizacionDolor(txtLocalizacion);
            reporte.setGravedad(txtGravedad);
            reporte.setAlimentacion(txtAlimentacion);
            reporte.setPatronSuenio(txtPatron);
            reporte.setActividadFisica(txtActividad);
            reporte.setMedicamentosRecientes(txtMedicamento);
            
            reporte.setId(Long.parseLong(""+i));
            
            reporte.setCatalizador(new Catalizador(Long.parseLong(""+i), txtActividad, txtAlimentacion, txtMedicamento, txtMedicamento));
            
            reporte.setFechaCreacion((new Date(System.currentTimeMillis()-10000000*i)).toString());
            
            lista.add(reporte);
        }
        
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity(lista).build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(lista).build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(lista).build();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(lista).build();
        
    }


}
