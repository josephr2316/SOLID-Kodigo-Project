package domain.demo;

import domain.asistencia.HorasExtra;
import domain.asistencia.Asistencia;
import domain.asistencia.AsistenciaLaboral;
import domain.deduccion_salarial.DeduccionSalarial;
import domain.deduccion_salarial.NominaSalarial;
import domain.empleado.Empleado;
import domain.horario.HorarioDiario;
import domain.horario.HorarioSemanal;
import domain.salario.Salario;
import domain.salario.SalarioMensual;
import domain.salario.SalarioQuincenal;
import domain.utils.Dias;

import java.time.LocalDate;
import java.time.LocalTime;

public class Demo {

    public static void main(String[] args) {
        // Fake Empleado
        Empleado empleado = new Empleado();
        empleado.setNombres("Jairo");
        empleado.setApellidos("Mercury");
        empleado.setIdenticicacion("234252-2");
        empleado.setFechaNacimiento(LocalDate.of(2000, 1, 1));

        // Fake Horarios
        HorarioSemanal horarioTecnicoSemanal = new HorarioSemanal(
                "Horario tecnico",
                "Horario de los tecnicos en mantenimiento"
        );
        HorarioDiario horarioMantenimiento = new HorarioDiario(
                "Mantenimiento",
                "Horario de mantenimiento de equipos",
                LocalTime.of(7, 0),
                LocalTime.of(12, 0)
        );
        horarioTecnicoSemanal.agregarOSustituirHorario(Dias.Lunes, horarioMantenimiento);

        // Fake Asistencias
        Asistencia asistencia = new Asistencia(LocalTime.of(7, 0), LocalTime.of(4, 0));
        AsistenciaLaboral asistenciaLaboral = new AsistenciaLaboral(
                horarioTecnicoSemanal,
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 31)
        );
        asistenciaLaboral.registrarAsistencia(LocalDate.of(2023, 10, 9), asistencia);

        // Fake Salarios
        Salario salario = new SalarioMensual(1200.00);
        HorasExtra horasExtras = new HorasExtra(asistenciaLaboral);

        // Fake Nomina
        NominaSalarial nomina = new NominaSalarial();
        nomina.setAsistenciaLaboral(asistenciaLaboral);
        nomina.setEmpleado(empleado);
        nomina.setVigencia("From yesterday");
        nomina.setDeduccionSalarial(new DeduccionSalarial(salario, horasExtras));

        // Resultados
        System.out.println("Empleado: " + nomina.getEmpleado().getNombres() + " " + nomina.getEmpleado().getApellidos());
        System.out.println("Salario bruto: " + nomina.getDeduccionSalarial().getSalarioBruto());
        System.out.println("Afp: " + nomina.getDeduccionSalarial().getAfp().getAfpEmpleado());
        System.out.println("Isss: " + nomina.getDeduccionSalarial().getIsss().getIsssEmpleado());
        System.out.println("Renta: " + nomina.getDeduccionSalarial().getRenta().getRetencion());
        System.out.println("Salario líquido: " + nomina.getDeduccionSalarial().getSalarioLiquido());
    }
}
