/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioPersistenciaMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.hospitalKennedy.persistencia.mock;


import co.edu.uniandes.csw.hospitalKennedy.dto.Doctor;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockRemote;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;

/**
 * Implementación de los servicios de persistencia
 * @author Juan Sebastián Urrego
 */

//public class ServicioPersistenciaMock implements IServicioPersistenciaMockRemote, IServicioPersistenciaMockLocal {

//    //-----------------------------------------------------------
//    // Atributos
//    //-----------------------------------------------------------
//
//    /**
//     * Lista con los vendedores del sistema
//     */
//    private static ArrayList<Doctor> doctores;
//
//
//    /**
//     * Lista con los usuarios del sistema
//     */
//    private static ArrayList<Paciente> pacientes;
//
//    /**
//     * Lista con los registros de ventas
//     */
//    private static ArrayList<Reporte> reportes;
//
//    //-----------------------------------------------------------
//    // Constructor
//    //-----------------------------------------------------------
//
//    /**
//     * Constructor de la clase. Inicializa los atributos.
//     */
//    public ServicioPersistenciaMock()
//    {
//        if (doctores == null)
//        {
//            doctores = new ArrayList<Doctor>();
//
//            doctores.add(new Doctor(1L, "Carlos Mendieta", "lolita45", "ca.mendieta45"));
//            doctores.add(new Doctor(5L, "Carlos Carrillo", "lolita45", "ca.carrillo"));
//
//            pacientes = new ArrayList<Paciente>();
//            ArrayList<Reporte> reportes = new ArrayList<Reporte>();
//            pacientes.add(new Paciente(1L, "Juana la loca", 25, 12365987, 200, reportes));
//            ArrayList<Reporte> reportes2 = new ArrayList<Reporte>();
//            pacientes.add(new Paciente(2L, "Juana la sana", 15, 55689865, 185, reportes2));
//
//            
//           // pacientes.get(0).agregarReporte(new Reporte("1L", "Jugar bascketball", "Sana", "1", (new Date(System.currentTimeMillis())).toString(), "Espalda baja", "Normal", 1, "Ninguno"));
//           // pacientes.get(0).agregarReporte(new Reporte("2L", "Jugar football", "Sana", "1", (new Date(System.currentTimeMillis())).toString(), "Espalda baja", "Normal", 2, "Ninguno"));
//            
//           // pacientes.get(1).agregarReporte(new Reporte("3L", "Jugar bascketball", "Sana", "1", (new Date(System.currentTimeMillis())).toString(), "Espalda baja", "Normal", 1, "Ninguno"));
//           // pacientes.get(1).agregarReporte(new Reporte("4L", "Jugar football", "Sana", "1", (new Date(System.currentTimeMillis())).toString(), "Espalda baja", "Normal", 2, "Ninguno"));
//                        
//        }
//    }
//
//    //-----------------------------------------------------------
//    // Métodos
//    //-----------------------------------------------------------
//    
//    /**
//     * Permite crear un objeto dentro de la persistencia del sistema.
//     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
//     */
//    @Override
//    public void create(Object obj) throws OperacionInvalidaException
//    {
//        if (obj instanceof Doctor)
//        {
//            Doctor v = (Doctor) obj;
//            for(Doctor doc: doctores)
//            {
//                if(doc.getLogin().equals(v.getLogin()))
//                    throw new OperacionInvalidaException("El doctor con el login " + v.getLogin() + " ya existe.");
//            }
//            v.setId(Long.parseLong("" + (doctores.size() + 1)));
//            doctores.add(v);
//        }
//        else if (obj instanceof Paciente)
//        {
//            Paciente m = (Paciente) obj;
//            for(Paciente paciente: pacientes)
////            {
////                if(paciente.getCedulaCiudadania() == m.getCedulaCiudadania())
////                    throw new OperacionInvalidaException("El paciente con la cedula de ciudadania " + m.getCedulaCiudadania() + " ya se encuentar registrado en el sistema");
////            }
//            m.setId(Long.parseLong(""+pacientes.size()) + 1);
//            pacientes.add(m);
//        }
//    }
//    
//    @Override
//    public void createReporte(String idPaciente, Reporte reporte) throws Exception
//    {
//        Paciente pac = (Paciente) findById(Paciente.class, idPaciente);
//        
//        if(pac == null)
//            throw new Exception("El paciente con el id " + idPaciente + " no existe.");
//        
//        reporte.setId(Long.parseLong(idPaciente + "" + (pac.getReportes().size()+1)));
//        reporte.setFechaCreacion((new Date(System.currentTimeMillis())).toString());
//        pac.getReportes().add(reporte);
//        
//    }
//
//    /**
//     * Permite modificar un objeto dentro de la persistencia del sistema.
//     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
//     */
//    @Override
//    public void update(Object obj)
//    {
//        if (obj instanceof Doctor)
//        {
//            Doctor editar = (Doctor) obj;
//            Doctor doc;
//            for (int i = 0; i < doctores.size(); i++)
//            {
//                doc = doctores.get(i);
//                if (doc.getId().equals(editar.getId()))
//                {
//                    doctores.set(i, editar);
//                    break;
//                }
//
//            }
//
//        }
//        else if (obj instanceof Paciente)
//        {
//            Paciente editar = (Paciente) obj;
//            Paciente mueble;
//            for (int i = 0; i < pacientes.size(); i++)
//            {
//                mueble = pacientes.get(i);
////                if (mueble.getCedulaCiudadania()== editar.getCedulaCiudadania())
////                {
////                    pacientes.set(i, editar);
////                    break;
////                }
//            }
//        }        
//    }    
//    
//    
//    @Override
//    public void updateReporte(String idPaciente, Reporte reporte) throws Exception
//    {
//        Paciente pac = (Paciente) findById(Paciente.class, idPaciente);
//        
//        if(pac == null)
//            throw new Exception("El paciente con el id " + idPaciente + " no existe.");
//        int i = 0;
//        for(Reporte rep: pac.getReportes())
//        {
//            
//            if(rep.getId().equals(reporte.getId()))
//            {
//                pac.getReportes().set(i, reporte);
//                
//            }
//            i++;
//        }
//    }
//
//    /**
//     * Permite borrar un objeto dentro de la persistencia del sistema.
//     * @param obj Objeto que representa la instancia de la entidad que se quiere borrar.
//     */
//    @Override
//    public void delete(Object obj) throws OperacionInvalidaException
//    {
//        if (obj instanceof Doctor)
//        {
//            Doctor vendedorABorrar = (Doctor) obj;
//
//            for (int e = 0; e < doctores.size(); e++)
//            {
//                Doctor ven = (Doctor) doctores.get(e);
//                if (ven.getId().equals(vendedorABorrar.getId()))
//                {
//                    doctores.remove(e);
//                    break;
//                }
//            }
//
//        } 
//        else if (obj instanceof Paciente)
//        {
//            Paciente mueble;
//            Paciente eliminar = (Paciente) obj;
//            for (int i = 0; i < pacientes.size(); i++)
//            {
////                mueble = pacientes.get(i);
////                if (eliminar.getCedulaCiudadania()== mueble.getCedulaCiudadania())
////                {
////                    pacientes.remove(i);
////                    break;
////                }
//
//            }
//
//        } 
//        
//    }
//    
//    @Override
//    public void deleteReporte(String idPaciente, Reporte reporte)
//    {
//        Paciente pac = (Paciente) findById(Paciente.class, idPaciente);
//        
//        int i = 0;
//        for(Reporte rep: pac.getReportes())
//        {
//            
//            if(rep.getId().equals( reporte.getId()))
//            {
//                pac.getReportes().remove(i);
//            }
//            i++;
//        }
//    }
//
//    /**
//     * Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
//     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
//     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
//     */
//    @Override
//    public List findAll(Class c)
//    {
//        System.out.println("Iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
//        
//        if (c.equals(Doctor.class))
//        {
//            return doctores;
//        } 
//        else 
//        {
//            return pacientes;
//        }
//    }
//    
//    @Override
//    public List findReportes(String idPaciente)
//    {
//        Paciente encontrado = (Paciente) findById(Paciente.class, idPaciente );
//        
//        return encontrado.getReportes();
//        
//    }
//
//    /**
//     * Retorna la instancia de una entidad dado un identificador y la clase de la entidadi.
//     * @param c Clase de la instancia que se quiere buscar.
//     * @param id Identificador unico del objeto.
//     * @return obj Resultado de la consulta.
//     */
//    @Override
//    public Object findById(Class c, Object id)
//    {
//        Object resp = null;
//        for (Object v : findAll(c))
//            {
//                Paciente mue = (Paciente) v;
//                if (id.toString().equals(mue.getId()))
//                {
//                    resp = mue;
//                }
//            }
//        
//        return resp;
//        
//    }
//}
