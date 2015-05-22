/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Catalizador;
import co.edu.uniandes.csw.hospitalKennedy.dto.DoctorDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.dto.ReporteDTO;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioDoctorMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import co.edu.uniandes.hospitalkennedy.security.logic.dto.PathInfo;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.OPTIONS;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Path("/Doctor")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorService {

    //@EJB
    //private IServicioDoctorMock doctorEjb;
    private ServicioDoctorMock servicioDoctor;

    public DoctorService() {
        servicioDoctor = new ServicioDoctorMock();
    }

    @POST
    @Path("/agregarDoctor")
    public Response agregarDoctor(DoctorDTO doctor) {

        DoctorDTO rta = servicioDoctor.agregarDoctor(doctor);
//        Response.status(200).header("Access-Control-Allow-Methods", "*").entity(rta).build();
//        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(rta).build();
//        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(rta).build();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();

    }

    @POST
    @Path("/agregarPaciente")
    public Response agregarPaciente(PacienteDTO paciente) {

        PacienteDTO p = servicioDoctor.agregarPaciente(paciente);

        System.out.println("HIZO LA MONDAAA Y AGREGÓ ");
//Response.status(200).header("Access-Control-Allow-Methods", "*").entity(p).build();
//        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(p).build();
//        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(p).build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(p).build();

    }

    /**
     *
     * @param idPaciente
     */
    @DELETE
    @Path("borrar/{idPaciente}")
    public Response eliminarPaciente(@PathParam("idPaciente") Long idPaciente) {
        //for(Paciente paciente: lista){
        //    doctorEjb.removerPaciente(paciente);
        //}
        Paciente p = servicioDoctor.removerPaciente(idPaciente);

        return Response.status(200).header("Access-Control-allow-Origin", "*").entity(p).build();

    }

    //@GET
    //@Path("/paciente/{idPaciente}")
    //public Response darPaciente(@PathParam("idPaciente") String idPaciente){
    //   return doctorEjb.getPacientes();
    //    return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();           
    //}
    /**
     *
     * @return
     */
    @GET
    @Path("/paciente/")
    public Response darPacientes() {
        //   return doctorEjb.getPacientes();
        System.out.println("::::::::: entro a darPacientes");
        List<Paciente> p = servicioDoctor.getPacientes();

        PacienteDTO[] a = new PacienteDTO[p.size()];

        for (int i = 0; i < p.size(); i++) {
            System.out.println("------------------------------------- " + p.get(i));
            Paciente actual = p.get(i);

            PacienteDTO n = new PacienteDTO(actual.getId(), actual.getNombre(), actual.getEdad(), actual.getEdad(), actual.getReportes());
            a[i] = n;
        }
//        ArrayList<Paciente> p = new ArrayList<Paciente>(ap);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(a).build();
    }

    @OPTIONS
    @Path("/paciente/")
    public Response aa() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("/agregarDoctor")
    public Response bb() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("/agregarPaciente")
    public Response cc() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }

    @OPTIONS
    @Path("borrar/{idPaciente}")
    public Response dd() {
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity("").build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity("").build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("").build();
    }
    
    @GET
    @Path("/pacientesPredeterminado")
    public Response darPacientesPredeterminado(){
        
        List<Reporte> lista = new ArrayList<Reporte>();
        
        for(int i = 0; i < 100; i++){
            Reporte reporte = new Reporte();
            
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
            
            reporte.setCatalizadores(new Catalizador(Long.parseLong(""+i), txtActividad, txtAlimentacion, txtMedicamento, txtMedicamento));
            
            reporte.setFechaCreacion((new Date(System.currentTimeMillis()-10000000*i)).toString());
            
            lista.add(reporte);
            
        }
        
        List<Paciente> pacientes = new ArrayList<Paciente>();
        
        for(int i = 0; i < 100; i++){
            
            Paciente nuevo = new Paciente();
            
            nuevo.setId(Long.parseLong("" + i));
            nuevo.setGrupo(PathInfo.PACIENTE);
            nuevo.setNombre(i%5==0?"Laura":i%5==1?"Miguel":i%5==2?"Manuel":i%5==3?"Paola":"Mariana");
            nuevo.reportes = new ArrayList<Reporte>();
            nuevo.reportes.addAll(lista);
            
            pacientes.add(nuevo);
            
        }
        
        Response.status(200).header("Access-Control-Allow-Methods", "*").entity(pacientes).build();
        Response.status(200).header("Access-Control-Allow-Headers", "*").entity(pacientes).build();
        Response.status(200).header("Access-Control-Allow-Credentials", "true").entity(pacientes).build();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacientes).build();
        
    }

}
