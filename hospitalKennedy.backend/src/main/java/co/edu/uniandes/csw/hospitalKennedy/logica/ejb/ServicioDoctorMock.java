/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;
import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Catalizador;
import co.edu.uniandes.csw.hospitalKennedy.dto.Doctor;
import co.edu.uniandes.csw.hospitalKennedy.dto.DoctorDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import javax.ejb.Stateless;

import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
//import co.edu.uniandes.csw.hospitalKennedy.persistencia.mock.ServicioPersistenciaMock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.core.Response;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.tenant.*;
import com.stormpath.sdk.application.*;
import com.stormpath.sdk.account.*;
import com.stormpath.sdk.application.*;
import com.stormpath.sdk.directory.*;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.group.Groups;
import java.util.Iterator;



/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioDoctorMock implements IServicioDoctorMock {

    //@EJB
    //public static IServicioPersistenciaMockLocal persistencia;
    @PersistenceUnit(unitName = "HospitalKennedyPU")
    EntityManager entityManager;
    
    Client client;
    Application application;

    private ArrayList<Paciente> pacientes;

    public ServicioDoctorMock() {

        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if(ServicioPacienteMock.persistencia == null)
        //{
        //    persistencia=new ServicioPersistenciaMock();
        //}
        //else
        //    persistencia = ServicioPacienteMock.persistencia;
        //Path currentRelativePath = Paths.get("");
	//String s = currentRelativePath.toAbsolutePath().toString();
        
        //Implementación STORMPATH
        String s="src\\main\\webapp\\WEB-INF\\apiKey-4Q4FXMVW3LPNYYFXEX4A7J3S7.properties";
        ApiKey apiKey = ApiKeys.builder().setFileLocation(s).build();
        client = Clients.builder().setApiKey(apiKey).build();
        Tenant tenant = client.getCurrentTenant();
        ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("HospitalKennedy")));
        application = applications.iterator().next();
    }

    @Override
    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public EntityManager getPersistencia() {
        return entityManager;
    }

    @Override
    public List<Paciente> getPacientes() {

        System.out.println("asdasdasdasdasdasdasdasdasdasdasdasdasd");
        //return (ArrayList<Paciente>)entityManager.find(Paciente.class, pacientes);
        Query q = entityManager.createQuery("SELECT u FROM Paciente u");
        List<Paciente> paciente = q.getResultList();
        return paciente;
    }

    /**
     *
     */
    @Override
    public PacienteDTO agregarPaciente(PacienteDTO paciente) {
               
        System.out.println("ENTRO AL METODO");
        
        Paciente p = new Paciente();

        p.setAltura(paciente.getAltura());
        p.setId(paciente.getCedulaCiudadania());
        p.setEdad(paciente.getEdad());
        p.setNombre(paciente.getNombre());
        
        //Crea una cuenta para el paciente y la agrega a Stormpath
        Account account = client.instantiate(Account.class);
        account.setGivenName(paciente.getNombre());
        account.setSurname(paciente.getNombre());
        account.setEmail(paciente.getNombre()+paciente.getCedulaCiudadania());
        account.setPassword(String.valueOf(paciente.getCedulaCiudadania()));
        GroupList grupos = application.getGroups(Groups.where(Groups.name().eqIgnoreCase("Paciente")));
        Iterator iterGrupo = grupos.iterator();
        account.addGroup((Group)iterGrupo.next());
        application.createAccount(account);

        System.out.println("La altura es: " + paciente.getAltura());
        System.out.println("La cédula es: " + paciente.getCedulaCiudadania());
        System.out.println("La edad es: " + paciente.getEdad());
        System.out.println("El nombre es: " + paciente.getNombre());
        System.out.println("La altura es: " + paciente.getAltura());

        p.setReportes(paciente.getReportes());

        System.out.println("Los reportes son los siguiente [  " + paciente.getReportes()+"  ]");

        try {
            entityManager.getTransaction().begin();
            System.out.println("´:::: empezó");
            entityManager.persist(p);
                        System.out.println("´:::: PERSISTE!!!");

            entityManager.getTransaction().commit();
                        System.out.println("´:::: COMMIT!!!");

            entityManager.refresh(p);
                        System.out.println("´:::: REFRESCÓ");


        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            p = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }

        return paciente;

    }

    public DoctorDTO agregarDoctor(DoctorDTO doctor) {

        Doctor d = new Doctor(doctor.getId(), doctor.getNombre(), doctor.getPassword(), doctor.getLogin());
        //Crea una cuenta para el doctor y la agrega a Stormpath
        Account account = client.instantiate(Account.class);
        account.setGivenName(doctor.getNombre());
        account.setSurname(doctor.getNombre());
        account.setEmail(doctor.getLogin());
        account.setPassword(doctor.getPassword());
        GroupList grupos = application.getGroups(Groups.where(Groups.name().eqIgnoreCase("Doctor")));
        Iterator iterGrupo = grupos.iterator();
        account.addGroup((Group)iterGrupo.next());
        application.createAccount(account);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(d);
            entityManager.getTransaction().commit();
            entityManager.refresh(d);

        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            doctor = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }

        return doctor;
    }

    /**
     * Remueve un mueble del carrito de compra
     */
    @Override
    public Paciente removerPaciente(Long idPaciente) {

        Paciente p = entityManager.find(Paciente.class, idPaciente);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(p);
            entityManager.getTransaction().commit();
            //entityManager.refresh(p);

        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            p = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }

        return p;

    }

}
