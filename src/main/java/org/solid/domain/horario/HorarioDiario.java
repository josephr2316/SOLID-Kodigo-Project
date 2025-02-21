package org.solid.domain.horario;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioDiario extends Horario {
  private LocalTime entrada;
  private LocalTime salida;

  public HorarioDiario(String nombre, String descripcion, LocalTime entrada, LocalTime salida) {
    super(nombre, descripcion);
    this.entrada = entrada;
    this.salida = salida;
  }
}
