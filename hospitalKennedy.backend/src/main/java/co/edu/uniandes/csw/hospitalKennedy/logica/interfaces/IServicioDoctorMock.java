/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.interfaces;

import co.edu.uniandes.csw.hospitalKennedy.dto.DoctorDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author estudiante
 */
public interface IServicioDoctorMock {
    
    
    public void setPacientes(ArrayList<Paciente> pacientes);
    public EntityManager getPersistencia();
    public List<Paciente> getPacientes();
    public PacienteDTO agregarPaciente(PacienteDTO paciente);
    public Paciente removerPaciente(Long idPaciente);
    public DoctorDTO agregarDoctor(DoctorDTO doctor);
    
}
